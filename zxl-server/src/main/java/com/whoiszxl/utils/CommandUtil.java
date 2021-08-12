package com.whoiszxl.utils;

import cn.hutool.extra.ssh.JschUtil;
import com.jcraft.jsch.Session;

import java.nio.charset.StandardCharsets;

/**
 * ssh命令行工具类
 */
public class CommandUtil {



    public static String exec(Session session, String cmd) {
        return JschUtil.exec(session, cmd, StandardCharsets.UTF_8);
    }

    public static Session getSession(String sshHost, Integer sshPort, String sshUser, String sshPass) {
        return JschUtil.getSession(sshHost, sshPort, sshUser, sshPass);
    }

}
