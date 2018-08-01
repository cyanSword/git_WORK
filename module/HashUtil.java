package com.whzhdc.zhenhe.passport.module;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.MessageDigest;

public class HashUtil {

    private static final Logger _log = LoggerFactory.getLogger(HashUtil.class);

    /**
     * md5摘要
     *
     * @param value 文本内容
     * @return 摘要信息
     */
    public static String md5(String value) {
        return getHashCode("MD5", value);
    }

    /**
     * md5摘要
     *
     * @param value 长整数
     * @return 摘要信息
     */
    public static String md5(long value) {
        return getHashCode("MD5", Long.toString(value));
    }

    /**
     * sha1摘要
     *
     * @param value 文本内容
     * @return 摘要信息
     */
    public static String sha1(String value) {
        return getHashCode("SHA1", value);
    }

    /**
     * sha1摘要
     *
     * @param value 长整数
     * @return 摘要信息
     */
    public static String sha1(long value) {
        return getHashCode("SHA1", Long.toString(value));
    }

    /**
     * 摘要计算函数
     *
     * @param alg 算法
     * @param str 文本内容
     * @return 摘要信息
     */
    private static String getHashCode(String alg, String str) {
        try {
            // 生成一个MD5加密计算摘要
            MessageDigest md = MessageDigest.getInstance(alg);
            // 计算md5函数
            md.update(str.getBytes());

            return byteArrayToHexString(md.digest());

            // digest()最后确定返回md5 hash值，返回值为8为字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
            // BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
            //return new BigInteger(1, md.digest()).toString(16);
        } catch (Exception e) {
            _log.error("生成摘要错误：" + e.getMessage());
            return null;
        }
    }

    public static String byteArrayToHexString(byte[] b) {
        String result = "";
        for (int i = 0; i < b.length; i++) {
            result += Integer.toString((b[i] & 0xff) + 0x100, 16).substring(1);
        }
        return result;
    }
}
