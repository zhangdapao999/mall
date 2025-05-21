package com.lin.mall.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {

    /**
     * MD5加密方法（带盐值）
     * @param plainText 明文
     * @param salt 盐值
     * @return 加密后的字符串（32位小写MD5）
     */
    public static String encrypt(String plainText, String salt) {
        try {
            // 合并明文和盐值
            String input = plainText + salt;

            // 获取MD5摘要实例
            MessageDigest md = MessageDigest.getInstance("MD5");

            // 计算哈希值
            byte[] digest = md.digest(input.getBytes());

            // 转换为16进制字符串
            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                sb.append(String.format("%02x", b));
            }

            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("MD5算法不可用", e);
        }
    }

    // 示例用法
    public static void main(String[] args) {
        String password = "Lin199368.";
        String salt = "mall";

        System.out.println("原始密码: " + password);
        System.out.println("盐值: " + salt);
        System.out.println("MD5加密结果: " + encrypt(password, salt));
    }

}
