package com.whoiszxl.service.impl;

import com.whoiszxl.entity.Config;
import com.whoiszxl.mapper.ConfigMapper;
import com.whoiszxl.service.ConfigService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 基础配置表 服务实现类
 * </p>
 *
 * @author whoiszxl
 * @since 2021-08-12
 */
@Service
public class ConfigServiceImpl extends ServiceImpl<ConfigMapper, Config> implements ConfigService {

}
