package com.whoiszxl.strategy;

import cn.hutool.core.lang.Dict;
import cn.hutool.extra.template.Template;
import cn.hutool.extra.template.TemplateConfig;
import cn.hutool.extra.template.TemplateEngine;
import cn.hutool.extra.template.TemplateUtil;
import cn.hutool.json.JSONUtil;
import com.google.gson.Gson;
import com.jcraft.jsch.Session;
import com.whoiszxl.constants.ConfigConstants;
import com.whoiszxl.constants.ScriptConstants;
import com.whoiszxl.constants.SoftwareConfigNameConstants;
import com.whoiszxl.constants.SoftwareConstants;
import com.whoiszxl.entity.Script;
import com.whoiszxl.entity.Server;
import com.whoiszxl.entity.Software;
import com.whoiszxl.entity.SoftwareConfig;
import com.whoiszxl.entity.template.ZookeeperTemplateParamsEntity;
import com.whoiszxl.service.*;
import com.whoiszxl.utils.CommandUtil;
import com.whoiszxl.utils.MyTemplateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class Centos7InstallStrategy implements InstallStrategy{

    @Autowired
    private ConfigService configService;

    @Autowired
    private ServerService serverService;

    @Autowired
    private ScriptService scriptService;

    @Autowired
    private SoftwareService softwareService;

    @Autowired
    private SoftwareConfigService softwareConfigService;

    @Override
    public boolean configHosts() {
        //连接多台机器进行hosts配置
        String hostsPath = configService.getByKey(ConfigConstants.HOSTS_PATH);

        List<Server> serverList = serverService.list(null);
        String execCommand = buildConfigHostsCommand(hostsPath, serverList);
        for (Server server : serverList) {
            Session session = CommandUtil.getSession(server.getServerOuterIp(), Integer.parseInt(server.getServerPort()), server.getServerUsername(), server.getServerPassword());
            CommandUtil.exec(session, execCommand);
        }
        return true;
    }

    @Override
    public boolean configSshNoPasswordLogin() {
        //获取ssh脚本路径
        Script script = scriptService.getByScriptName(ScriptConstants.SSH_NO_PASS_LOGIN);
        String absolutePath = script.getScriptPath() + script.getScriptName();

        List<Server> serverList = serverService.list(null);
        for (Server server : serverList) {
            Session session = CommandUtil.getSession(server.getServerOuterIp(), Integer.parseInt(server.getServerPort()), server.getServerUsername(), server.getServerPassword());
            //执行脚本
            CommandUtil.exec(session, buildSshNoPasswordLoginCommand(absolutePath, serverList) + " " + server.getServerPassword());
        }
        return true;
    }



    @Override
    public boolean syncScript() {
        List<Server> serverList = serverService.list(null);
        for (Server server : serverList) {
            Session session = CommandUtil.getSession(server.getServerOuterIp(), Integer.parseInt(server.getServerPort()), server.getServerUsername(), server.getServerPassword());

            //同步脚本
            List<Script> scriptList = scriptService.list(null);
            for (Script script : scriptList) {
                //创建脚本目录
                CommandUtil.exec(session, "mkdir -p " + script.getScriptPath());
                CommandUtil.exec(session, "echo '" + script.getScriptContent() + "' > " + script.getScriptPath() + script.getScriptName());
                CommandUtil.exec(session, "chmod +x " + script.getScriptPath() + script.getScriptName());
            }
        }
        return true;
    }


    @Override
    public boolean syncSofware() {
        Script script = scriptService.getByScriptName(ScriptConstants.XSYNC);
        Server server = serverService.getById(1);
        Session session = CommandUtil.getSession(server.getServerOuterIp(), Integer.parseInt(server.getServerPort()), server.getServerUsername(), server.getServerPassword());
        String softwarePath = configService.getByKey(ConfigConstants.SOFTWARE_PATH);

        List<Server> serverList = serverService.list(null);
        String execCommand = buildSyncSoftwareCommand(server, serverList, script, softwarePath);

        String execResult = CommandUtil.exec(session, execCommand);
        log.info("同步组件结果", execResult);
        return true;
    }

    private String buildSyncSoftwareCommand(Server server, List<Server> serverList, Script script, String softwarePath) {
        StringBuilder sb = new StringBuilder(script.getScriptPath() + script.getScriptName() + " '");

        for (Server serv : serverList) {
            sb.append(serv.getServerName() + " ");
        }
        sb.append("' ");
        sb.append(softwarePath);
        return sb.toString();
    }

    @Override
    public String viewFile(String absolutePath, Integer serverId) {
        Server server = serverService.getById(serverId);
        if(server == null) {
            return null;
        }
        Session session = CommandUtil.getSession(server.getServerOuterIp(), Integer.parseInt(server.getServerPort()), server.getServerUsername(), server.getServerPassword());
        return CommandUtil.exec(session, "cat " + absolutePath);
    }

    @Override
    public boolean installJDK(List<Integer> serverIds) {
        Collection<Server> servers = serverService.listByIds(serverIds);
        Software software = softwareService.getBySoftwareName(SoftwareConstants.JDK);
        for (Server server : servers) {
            Session session = CommandUtil.getSession(server.getServerOuterIp(), Integer.parseInt(server.getServerPort()), server.getServerUsername(), server.getServerPassword());
            //1. 创建安装目录
            CommandUtil.exec(session, "mkdir -p " + software.getInstallPath());
            //2. 解压到安装目录
            CommandUtil.exec(session, "tar -zxvf " + software.getSoftwarePath() + software.getSoftwareFilename() + " -C " + software.getInstallPath());
            //3. 输出环境变量
            CommandUtil.exec(session, "echo '" + software.getEnvContent() + "' >> " + software.getEnvPath());
            //4. 刷新环境变量
            CommandUtil.exec(session, "source " + software.getEnvPath());
        }
        return true;
    }


    @Override
    public boolean installZookeeper(List<Integer> serverIds) {
        Collection<Server> servers = serverService.listByIds(serverIds);
        Software software = softwareService.getBySoftwareName(SoftwareConstants.ZOOKEEPER);

        String clusterConfig = buildZkClusterConfig(servers);

        int myid = 1;
        for (Server server : servers) {
            //获取需要安装zk的服务器列表并连接
            Session session = CommandUtil.getSession(server.getServerOuterIp(), Integer.parseInt(server.getServerPort()), server.getServerUsername(), server.getServerPassword());

            //1. 创建安装目录
            CommandUtil.exec(session, "mkdir -p " + software.getInstallPath());

            //2. 解压到安装目录
            CommandUtil.exec(session, "tar -zxvf " + software.getSoftwarePath() + software.getSoftwareFilename() + " -C " + software.getInstallPath());

            //3. 输出环境变量
            CommandUtil.exec(session, "echo '" + MyTemplateUtil.replaceGanR(software.getEnvContent()) + "' >> " + software.getEnvPath());

            //4. 解析zoo.cfg配置
            SoftwareConfig softwareConfig = softwareConfigService.getBySoftwareConfigName(SoftwareConfigNameConstants.ZOO_CFG);
            //4.1 解析模板参数
            ZookeeperTemplateParamsEntity templateParams = JSONUtil.toBean(softwareConfig.getConfigTemplateParams(), ZookeeperTemplateParamsEntity.class);
            //4.2 通过模板参数配置
            String zooCfg = MyTemplateUtil.convertTemplate(softwareConfig.getConfigTemplate(), softwareConfig.getConfigTemplateParams());
            zooCfg = zooCfg + "\n" + clusterConfig;
            zooCfg = MyTemplateUtil.replaceGanR(zooCfg);
            //4.3 将模板参数写入
            CommandUtil.exec(session, "echo '" + zooCfg + "' > " + softwareConfig.getConfigPath() + softwareConfig.getConfigName());

            //5. 创建zk数据文件目录
            CommandUtil.exec(session, "mkdir " + templateParams.getDataDir());

            //4. 输出myid文件
            CommandUtil.exec(session, "echo " + myid + " > " + templateParams.getDataDir() + "/myid");

            myid++;
        }


        return true;
    }

    /**
     * 构建zk集群配置
     * @param servers
     * @return
     */
    private String buildZkClusterConfig(Collection<Server> servers) {
        int myid = 1;
        StringBuilder sb = new StringBuilder("#cluster\n");
        for (Server server : servers) {
            sb.append("server.");
            sb.append(myid);
            sb.append("=");
            sb.append(server.getServerName());
            sb.append(":2888:3888\n");
            myid++;
        }
        return sb.toString();
    }

    @Override
    public boolean installKafka(List<Integer> serverIds) {
        Collection<Server> servers = serverService.listByIds(serverIds);
        Software software = softwareService.getBySoftwareName(SoftwareConstants.KAFKA);

        int brokerId = 1;
        for (Server server : servers) {
            //获取需要安装zk的服务器列表并连接
            Session session = CommandUtil.getSession(server.getServerOuterIp(), Integer.parseInt(server.getServerPort()), server.getServerUsername(), server.getServerPassword());

            CommandUtil.exec(session, "mkdir -p " + software.getInstallPath());
            CommandUtil.exec(session, "tar -zxvf " + software.getSoftwarePath() + software.getSoftwareFilename() + " -C " + software.getInstallPath());
            CommandUtil.exec(session, "echo '" + MyTemplateUtil.replaceGanR(software.getEnvContent()) + "' >> " + software.getEnvPath());

            SoftwareConfig softwareConfig = softwareConfigService.getBySoftwareConfigName(SoftwareConfigNameConstants.KAFKA_SERVER_PROPERTIES);
            String kafkaConfig = MyTemplateUtil.convertTemplate(softwareConfig.getConfigTemplate(), softwareConfig.getConfigTemplateParams());
            kafkaConfig = kafkaConfig + "\nbroker.id=" + brokerId;
            kafkaConfig = MyTemplateUtil.replaceGanR(kafkaConfig);
            CommandUtil.exec(session, "echo '" + kafkaConfig + "' > " + softwareConfig.getConfigPath() + softwareConfig.getConfigName());
            brokerId++;
        }

        return false;
    }

    /**
     * <p>构建ssh免密登录执行的命令</p>
     *
     *  例：/opt/zxl-hadoop/script/ssh_no_pass_login.sh 'hadoop101 hadoop102 hadoop103'
     * @param absolutePath 脚本的绝对路径
     * @param serverList 所有服务器
     * @return
     */
    private String buildSshNoPasswordLoginCommand(String absolutePath, List<Server> serverList) {
        StringBuilder sb = new StringBuilder(absolutePath);
        sb.append(" '");
        for (Server server : serverList) {
            sb.append(server.getServerName() + " ");
        }
        sb.append("'");
        return sb.toString();
    }

    /**
     * <p>通过配置中的服务器列表构建出/etc/hosts文件中的记录</p>
     *
     * 例：echo -e 'hadoop1 127.0.0.1\nhadoop2 127.0.0.2\nhadoop3 127.0.0.3' >> /etc/hosts
     * @param hostsPath hosts文件绝对路径地址
     * @param serverList 服务器列表
     * @return 配置hosts的命令
     */
    private String buildConfigHostsCommand(String hostsPath, List<Server> serverList) {
        StringBuilder sb = new StringBuilder("echo -e '");

        for (Server server : serverList) {
            sb.append(server.getServerInnerIp());
            sb.append(" ");
            sb.append(server.getServerName());
            sb.append("\n");
        }
        sb.append("' >> ");
        sb.append(hostsPath);
        return sb.toString();
    }


}
