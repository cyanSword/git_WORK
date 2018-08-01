package com.whzhdc.zhenhe.passport.module;

import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.Random;

@Component
public class RandomUtil {

    private static final long MSB = 0x8000000000000000L;
    // Maxim: Copied from UUID implementation :)

    private static volatile SecureRandom numberGenerator = null;

    private static Random random = new Random();

    RandomUtil() {
        SecureRandom ng = numberGenerator;
        if (ng == null) {
            numberGenerator = ng = new SecureRandom();
        }

    }

    /**
     * 获取唯一字符串
     *
     * @return 字符串
     */
    public String unique() {
        SecureRandom ng = numberGenerator;
//        if (ng == null) {
//            numberGenerator = ng = new SecureRandom();
//        }

        return Long.toHexString(MSB | ng.nextLong()) + Long.toHexString(MSB | ng.nextLong());
    }

    /**
     * 获取随机6位数字
     *
     * @return 字符串
     */
    public String randomIntCode() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            int a = random.nextInt(10);
            builder.append(a);
        }
        return builder.toString();
    }

    /**
     * 获得1-1000之间的随机整数
     * @return
     */
    public int randomInt() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            int a = random.nextInt(10);
            builder.append(a);
        }
        return Integer.valueOf(builder.toString());
    }
}