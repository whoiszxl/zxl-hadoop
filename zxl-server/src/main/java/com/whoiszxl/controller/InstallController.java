package com.whoiszxl.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import com.jcraft.jsch.Session;
import com.whoiszxl.entity.Server;
import com.whoiszxl.entity.common.ResponseResult;
import com.whoiszxl.service.ServerService;
import com.whoiszxl.strategy.InstallStrategy;
import com.whoiszxl.utils.CommandUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @Autowired
    private InstallStrategy installStrategy;

    @SaCheckLogin
    @PostMapping("/jdk")
    @ApiOperation(value = "安装JDK", notes = "安装JDK", response = Object.class)
    public ResponseResult<Object> installJDK(@RequestBody List<Integer> serverIds) {
        boolean installFlag = installStrategy.installJDK(serverIds);
        return ResponseResult.buildByFlag(installFlag);
    }

}
