package com.whzhdc.zhenhe.passport.module;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.io.IOException;

public class JsonUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(JsonUtil.class);

    /**
     * 将对象转换为字典
     *
     * @param model
     * @param <T>
     * @return
     */
    public static <T> String asString(T model) {
        if (model == null) {
            return null;
        }
        ObjectMapper objMapper = new ObjectMapper();
        objMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        try {
            return objMapper.writeValueAsString(model);
        } catch (IOException e) {
            LOGGER.error("转换数据失败", e);
            return null;
        }
    }


    public static <T> T fromString(String value, Class<T> classType) {
        if (StringUtils.isEmpty(value)) {
            return null;
        }
        try {
            return new ObjectMapper()
                    .disable(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES)
                    .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
                    .readValue(value, classType);
        } catch (IOException e) {
            LOGGER.error("转换JSON对象失败:" + value, e);
            return null;
        }

    }
}
