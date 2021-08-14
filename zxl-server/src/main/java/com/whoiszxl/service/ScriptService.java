package com.whoiszxl.service;

import com.whoiszxl.entity.Script;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * SH脚本表 服务类
 * </p>
 *
 * @author whoiszxl
 * @since 2021-08-14
 */
public interface ScriptService extends IService<Script> {

    /**
     * 通过脚本名称获取脚本
     * @param scriptName 脚本名称
     * @return 脚本信息
     */
    Script getByScriptName(String scriptName);
}
