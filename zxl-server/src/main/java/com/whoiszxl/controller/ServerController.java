package com.whoiszxl.controller;


import com.jcraft.jsch.Session;
import com.whoiszxl.entity.Server;
import com.whoiszxl.entity.common.ResponseResult;
import com.whoiszxl.service.ServerService;
import com.whoiszxl.utils.CommandUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 服务器表 前端控制器
 * </p>
 *
 * @author whoiszxl
 * @since 2021-08-12
 */
@RestController
@RequestMapping("/server")
public class ServerController {

    @Autowired
    private ServerService serverService;

    @PostMapping("/install/jdk/{serverId}")
    public ResponseResult<Object> installJDK(@PathVariable Integer serverId) {
        Server server = serverService.getById(serverId);
        Session session = CommandUtil.getSession(server.getServerOuterIp(), Integer.parseInt(server.getServerPort()), server.getServerUsername(), server.getServerPassword());
        CommandUtil.exec(session, "tar -zxvf /opt/software/jdk-8u212-linux-x64.tar.gz -C /opt/module");
        CommandUtil.exec(session, "mv /opt/module/jdk1.8.0_212 /opt/module/jdk");
        CommandUtil.exec(session, "echo -e '#JAVA_HOME\nexport JAVA_HOME=/opt/module/jdk\nexport PATH=$PATH:$JAVA_HOME/bin' >> /etc/profile.d/zxl_hadoop_env.sh");
        CommandUtil.exec(session, "source /etc/profile.d/zxl_hadoop_env.sh");
        String jdkValidate = CommandUtil.exec(session, "java -version");
        System.out.println(jdkValidate);
        session.disconnect();
        return ResponseResult.buildSuccess(jdkValidate);
    }

    @PostMapping("/test/{serverId}")
    public ResponseResult<Object> test(@PathVariable Integer serverId) {
        Server server = serverService.getById(serverId);
        Session session = CommandUtil.getSession(server.getServerOuterIp(), Integer.parseInt(server.getServerPort()), server.getServerUsername(), server.getServerPassword());
        String jdkValidate = CommandUtil.exec(session, "jps");
        System.out.println(jdkValidate);
        return ResponseResult.buildSuccess(jdkValidate);
    }


}

