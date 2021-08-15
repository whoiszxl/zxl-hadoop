package com.whoiszxl.service;

import com.whoiszxl.entity.SoftwareConfig;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 基础组件配置表 服务类
 * </p>
 *
 * @author whoiszxl
 * @since 2021-08-13
 */
public interface SoftwareConfigService extends IService<SoftwareConfig> {

    /**
     * 通过组件配置文件名获取配置信息
     * @param zooCfg
     * @return
     */
    SoftwareConfig getBySoftwareConfigName(String zooCfg);
}
