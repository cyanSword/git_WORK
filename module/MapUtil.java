package com.whzhdc.zhenhe.passport.module;

import java.util.HashMap;

public class MapUtil {

    /**
     * 错误信息
     *
     * @param code    错误代码
     * @param message 错误摘要
     * @return 字典
     */
    public static HashMap<String, Object> error(int code, String message) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("errCode", code);
        map.put("errMsg", message);
        return map;
    }

    /**
     * 成功信息
     *
     * @return 字典
     */
    public static HashMap<String, Object> success() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("errCode", 0);
        map.put("errMsg", "");
        return map;
    }

    /**
     * 成功信息
     *
     * @return 字典
     */
    public static HashMap<String, Object> success(Object model) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("errCode", 0);
        map.put("errMsg", "");
        map.put("model", model);
        return map;
    }
}
