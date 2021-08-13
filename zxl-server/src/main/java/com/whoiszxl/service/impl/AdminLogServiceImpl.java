package com.whoiszxl.service.impl;

import com.whoiszxl.entity.AdminLog;
import com.whoiszxl.mapper.AdminLogMapper;
import com.whoiszxl.service.AdminLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 管理员操作日志表 服务实现类
 * </p>
 *
 * @author whoiszxl
 * @since 2021-08-13
 */
@Service
public class AdminLogServiceImpl extends ServiceImpl<AdminLogMapper, AdminLog> implements AdminLogService {

}
