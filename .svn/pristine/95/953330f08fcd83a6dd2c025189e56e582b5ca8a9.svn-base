package com.wanma.ims.util;

import com.google.common.base.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

/**
 * Created by xyc on 2015/8/14.
 * ip工具
 */
public final class NetUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(NetUtils.class);

    private static String ip;

    public static String getHostIp() {
        if (Strings.isNullOrEmpty(ip)) {
            ip = doGetHostIp();
        }
        return ip;
    }

    private static String doGetHostIp() {
        // 本地IP，如果没有配置外网IP则返回它
        String localIp = null;
        // 外网IP
        String netIp = null;
        try {
            Enumeration<NetworkInterface> netInterfaces = NetworkInterface
                    .getNetworkInterfaces();
            InetAddress ip;
            // 是否找到外网IP
            boolean find = false;
            while (netInterfaces.hasMoreElements() && !find) {
                NetworkInterface ni = netInterfaces.nextElement();
                Enumeration<InetAddress> address = ni.getInetAddresses();
                while (address.hasMoreElements()) {
                    ip = address.nextElement();
                    if (!ip.isSiteLocalAddress() && !ip.isLoopbackAddress() && !ip.getHostAddress().contains(":")) {
                        // 外网IP
                        netIp = ip.getHostAddress();
                        find = true;
                        break;
                    } else if (ip.isSiteLocalAddress() && !ip.isLoopbackAddress() && !ip.getHostAddress().contains(":")) {
                        // 内网IP
                        localIp = ip.getHostAddress();
                    }
                }
            }
        } catch (SocketException e) {
            LOGGER.error("get host ip error", e);
        }
        if (netIp != null && !"".equals(netIp)) {
            return netIp;
        } else {
            if (Strings.isNullOrEmpty(localIp)) {
                return "0.0.0.0";
            }
            return localIp;
        }
    }

    public static String getRequestRealIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (!Strings.isNullOrEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)) {
            //多次反向代理后会有多个ip值，第一个ip才是真实ip
            int index = ip.indexOf(",");
            if (index != -1) {
                return ip.substring(0, index);
            } else {
                return ip;
            }
        }
        ip = request.getHeader("Real-IP");
        if (!Strings.isNullOrEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)) {
            return ip;
        }
        ip = request.getRemoteAddr();
        return ip == null ? "10.10.10.1" : ip;
    }
}
