package com.whoiszxl.strategy;

import com.jcraft.jsch.Session;
import com.whoiszxl.constants.ConfigConstants;
import com.whoiszxl.entity.Script;
import com.whoiszxl.entity.Server;
import com.whoiszxl.service.ConfigService;
import com.whoiszxl.service.ScriptService;
import com.whoiszxl.service.ServerService;
import com.whoiszxl.utils.CommandUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Centos7InstallStrategy implements InstallStrategy{

    @Autowired
    private ConfigService configService;

    @Autowired
    private ServerService serverService;

    @Autowired
    private ScriptService scriptService;

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
        List<Server> serverList = serverService.list(null);
        for (Server server : serverList) {
            Session session = CommandUtil.getSession(server.getServerOuterIp(), Integer.parseInt(server.getServerPort()), server.getServerUsername(), server.getServerPassword());
            String exec = CommandUtil.exec(session, "ssh-keygen -t rsa");
            CommandUtil.exec(session, "<br>");
            CommandUtil.exec(session, "<br>");
            CommandUtil.exec(session, "<br>");
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
    public String viewFile(String absolutePath, Integer serverId) {
        Server server = serverService.getById(serverId);
        if(server == null) {
            return null;
        }
        Session session = CommandUtil.getSession(server.getServerOuterIp(), Integer.parseInt(server.getServerPort()), server.getServerUsername(), server.getServerPassword());
        return CommandUtil.exec(session, "cat " + absolutePath);
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