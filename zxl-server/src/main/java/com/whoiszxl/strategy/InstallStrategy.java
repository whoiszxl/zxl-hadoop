package com.whoiszxl.strategy;

import com.jcraft.jsch.Session;

import java.util.List;

/**
 * 安装策略接口
 */
public interface InstallStrategy {

    /**
     * 配置多机器的hosts配置
     * @return
     */
    boolean configHosts();

    /**
     * 配置多机器免密登录策略
     * @return
     */
    boolean configSshNoPasswordLogin();

    /**
     * 同步脚本
     * @return
     */
    boolean syncScript();


    /**
     * 同步组件包
     * @return
     */
    boolean syncSofware();

    /**
     * 浏览服务器上的文件
     * @param absolutePath 文件绝对路径
     * @param serverId 服务器ID
     * @return
     */
    String viewFile(String absolutePath, Integer serverId);

    /**
     * 对传入的服务器安装JDK
     * @param serverIds 服务器ID集合
     * @return 是否安装成功
     */
    boolean installJDK(List<Integer> serverIds);
}
