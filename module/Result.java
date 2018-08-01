package com.whzhdc.zhenhe.passport.module;

import java.io.Serializable;
import java.util.HashMap;

/**
 * the common result of service method
 *
 * @param <T> the DTO type
 */
public class Result<T> implements Serializable {

    /**
     * model instance
     */
    private T model = null;

    /**
     * error code, 0 means no error
     */
    private int errCode = 0;

    /**
     * error message
     */
    private String errMsg = "";

    public Result() {
    }

    public Result(int errCode, String errMsg) {
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    public Result(T model) {
        this.model = model;
    }

    public T getModel() {
        return model;
    }

    public void setModel(T model) {
        this.model = model;
    }

    public int getErrCode() {
        return errCode;
    }

    public void setErrCode(int errCode) {
        this.errCode = errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public static Result success() {
        return new Result();
    }

    /**
     * success
     *
     * @param model
     * @param <T>
     * @return
     */
    public static <T> Result<T> success(T model) {
        return new Result<>(model);
    }

    /**
     * failure
     *
     * @param errCode
     * @param errMsg
     * @param <T>
     * @return
     */
    public static <T> Result<T> failure(int errCode, String errMsg) {
        return new Result<>(errCode, errMsg);
    }

    /**
     * transform the Result to a map, often used to generate json object
     *
     * @return HashMap, contains a model if errCode equals zero
     */
    public HashMap<String, Object> map() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("errCode", errCode);
        map.put("errMsg", errMsg);
        if (errCode == 0 && model != null) {
            map.put("model", model);
        }
        return map;
    }

}
