package com.whoiszxl.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import com.jcraft.jsch.Session;
import com.whoiszxl.entity.Server;
import com.whoiszxl.entity.common.ResponseResult;
import com.whoiszxl.service.ServerService;
import com.whoiszxl.utils.CommandUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 安装服务控制器
 *
 * @author whoiszxl
 * @date 2021/8/13
 */
@CrossOrigin
@Slf4j
@RestController
@RequestMapping("/install")
@Api(tags = "安装服务相关接口")
public class InstallController {

    @Autowired
    private ServerService serverService;

    @SaCheckLogin
    @PostMapping("/install/jdk/{serverId}")
    @ApiOperation(value = "安装JDK", notes = "安装JDK", response = Object.class)
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

    @SaCheckLogin
    @PostMapping("/test/{serverId}")
    @ApiOperation(value = "测试", notes = "测试", response = Object.class)
    public ResponseResult<Object> test(@PathVariable Integer serverId) {
        Server server = serverService.getById(serverId);
        Session session1 = CommandUtil.getSession(server.getServerOuterIp(), Integer.parseInt(server.getServerPort()), server.getServerUsername(), server.getServerPassword());
        Session session2 = CommandUtil.getSession(server.getServerOuterIp(), Integer.parseInt(server.getServerPort()), server.getServerUsername(), server.getServerPassword());
        System.out.println(session1);
        System.out.println(session2);
        return ResponseResult.buildSuccess();
    }
}
