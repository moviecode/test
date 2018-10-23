package com.itaiti.tools;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * IP 瀹炵敤宸ュ叿銆�
 */
public final class IPUtil {
    private static List<String> ips;

    private IPUtil() {
        // 闃叉瀹炰緥鍖�
    }

    /**
     * 杩斿洖鏈満鍦板潃銆�
     * 璇存槑锛�
     * 浠呰繑鍥� IPv4
     * 涓嶄細杩斿洖鍥炵幆鍦板潃 127.*銆�
     * 濡傛灉澶氬紶缃戠粶鍗★紝闅忔満杩斿洖銆�
     *
     * @return
     */
    public static String getLocalIP() {
        safeLoadLocalIPList();
        return (ips.isEmpty()) ? "" : ips.get(0);
    }

    public static List<String> getLocalIPList() {
        safeLoadLocalIPList();
        return ips;
    }

    private static void safeLoadLocalIPList() {
        if (ips != null) {
            return;
        }

        synchronized (IPUtil.class) {
            if (ips != null) {
                return;
            }

            ips = loadLocalIPList();
        }
    }

    private static List<String> loadLocalIPList() {
        List<String> ips = new ArrayList<>(1);

        Enumeration<NetworkInterface> allNIC = null;
        try {
            allNIC = NetworkInterface.getNetworkInterfaces();
        } catch (Exception ex) {
            //LoggerManager.getLogger(IPUtil.class).error("鑾峰彇鏈満 ip 鍦板潃鍙戠敓閿欒", ex);
        }

        while (allNIC != null && allNIC.hasMoreElements()) {
            NetworkInterface nic = allNIC.nextElement();
            Enumeration<InetAddress> addresses = nic.getInetAddresses();
            while (addresses.hasMoreElements()) {
                InetAddress ip = addresses.nextElement();
                if (ip != null && (ip instanceof Inet4Address) && (!ip.isAnyLocalAddress() && !ip.isLoopbackAddress())) {
                    ips.add(ip.getHostAddress());
                }
            }
        }

        return ips;
    }
    
    public static void main(String[] args) {
    	System.out.println(getLocalIP());
    	try {
			InetAddress ipaddr = InetAddress.getLocalHost();
			System.out.println(ipaddr.getHostAddress());
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
