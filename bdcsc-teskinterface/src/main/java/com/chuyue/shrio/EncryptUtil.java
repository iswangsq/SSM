package com.chuyue.shrio;

import org.apache.shiro.crypto.hash.Md5Hash;

public class EncryptUtil {

   //加密
    public static String getMd5Hash(String password,String username){
        Md5Hash md5Hash = new Md5Hash(password, username,3);
        return md5Hash.toString();
    }
    public static void main(String[] args) {
        //source:明文 salt 盐 hashIterations 哈希的次数
        Md5Hash md5Hash = new Md5Hash("admin", "123123", 3);
        System.out.println(md5Hash);
    }
}