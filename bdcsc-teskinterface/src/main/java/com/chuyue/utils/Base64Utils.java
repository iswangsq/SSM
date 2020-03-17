package com.chuyue.utils;

import java.lang.reflect.Method;

/**
 * 字符加密类
 */
public class Base64Utils {
    public static void main(String[] args) throws Exception {
        String str = "{\"taskId\":\"ID0006\",\"taskType\":\"pop-analysis\"," +
                "\"taskInfo\":{\"start_time\":\"20180830\",\"end_time\":\"20180830\",\"city_ids\":\"84309,84305\",\"od_type\":\"1\",\"zip_pswd\":\"12345678\"},\"priority\":\"1\",\"userId\":\"xxxxxxx\"}";
        String s = encodeBase64((str.getBytes()));
        System.out.println(s);
        String result = new String(decodeBase64(s));
        System.out.println(result);

    }
    public static String encodeBase64(byte[]input) throws Exception{
        Class clazz=Class.forName("com.sun.org.apache.xerces.internal.impl.dv.util.Base64");
        Method mainMethod= clazz.getMethod("encode", byte[].class);
        mainMethod.setAccessible(true);
        Object retObj=mainMethod.invoke(null, new Object[]{input});
        return (String)retObj;
    }
    /***
     * decode by Base64
     */
    public static byte[] decodeBase64(String input) throws Exception{
        Class clazz=Class.forName("com.sun.org.apache.xerces.internal.impl.dv.util.Base64");
        Method mainMethod= clazz.getMethod("decode", String.class);
        mainMethod.setAccessible(true);
        Object retObj=mainMethod.invoke(null, input);
        return (byte[])retObj;
    }
}
