package com.example.demo.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @author: liming522
 * @description:
 * @date: 2022/1/18 5:01 下午
 * @hope: The newly created file will not have a bug
 */

public class TraceIdUtil {

    public TraceIdUtil()
    {

    }

    public static String getTraceId()  {
        return UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
/*
        try
        {
            return "traceId:" +  TraceIdUtil.getSecurityIP(InetAddress.getLocalHost().getHostAddress()) + ", date:" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        }
        catch (UnknownHostException e) {
            return "traceId:" +  ", date:" + new Date().toString();
        }*/
    }

    private static String getSecurityIP(String ip)
    {
        String nullIP = "ip:***.***.***.***";
        if (StringUtils.isBlank(ip))
        {
            return nullIP;
        }
        String[] split = ip.split("\\.");

        if (split.length == 4)
        {
            return "ip:***.***." + split[2] + "." + split[3];
        }
        return nullIP;
    }
}
