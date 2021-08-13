package com.whoiszxl.controller;


import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.whoiszxl.entity.Admin;
import com.whoiszxl.entity.common.LoginQuery;
import com.whoiszxl.entity.common.ResponseResult;
import com.whoiszxl.service.AdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 管理员表 前端控制器
 * </p>
 *
 * @author whoiszxl
 * @since 2021-08-13
 */
@CrossOrigin
@RestController
@RequestMapping("/admin")
@Api(tags = "管理員登录相关接口")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/login")
    @ApiOperation(value = "账号密码登录", notes = "账号密码登录", response = ResponseResult.class)
    public ResponseResult<String> adminLogin(@RequestBody LoginQuery loginQuery) {
        //1. 从数据库查询用户名和密码是否匹配
        Admin admin = adminService.getOne(new QueryWrapper<Admin>().eq("username", loginQuery.getUsername()));
        if (admin == null || !StringUtils.equals(admin.getPassword(), loginQuery.getPassword())) {
            return ResponseResult.buildError("账号或密码错误");
        }

        //2. 登录并获取token
        StpUtil.login(admin.getId());
        return ResponseResult.buildSuccess("登录成功", StpUtil.getTokenValue());
    }

    @PostMapping("/info")
    @ApiOperation(value = "查询当前管理员信息", notes = "查询当前管理员信息", response = Admin.class)
    public ResponseResult<Admin> info() {
        Integer loginId = StpUtil.getLoginIdAsInt();
        Admin admin = adminService.getById(loginId);
        return ResponseResult.buildSuccess(admin);
    }
}

