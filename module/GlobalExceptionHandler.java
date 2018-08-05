package com.whzhdc.zhenhe.passport;

import com.whzhdc.zhenhe.passport.model.AuthInfo;
import com.whzhdc.zhenhe.passport.model.ErrLog;
import com.whzhdc.zhenhe.passport.module.Result;
import com.whzhdc.zhenhe.passport.service.ErrLogService;
import com.whzhdc.zhenhe.passport.service.InstantService;
import com.whzhdc.zhenhe.passport.service.JwtTokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpRequest;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.HashMap;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @Autowired
    private InstantService instantService;

    @Autowired
    private JwtTokenService jwtTokenService;

    @Autowired
    private ErrLogService errLogService;

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    private String getDeveloper() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        return request.getHeader("developer");
    }

    /**
     * 无效访问操作
     *
     * @return 错误字典
     */
    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    public HashMap<String, Object> HttpRequestMethodNotSupportedException() {
        saveErrLog(-1,"无效访问操作");
        instantService.Send("error", getDeveloper(), "无效访问操作");
        return Result.failure(-1, "无效访问操作").map();
    }

    /**
     * 数据库访问错误
     *
     * @return 信息集合ø
     */
    @ExceptionHandler({SQLException.class, DataAccessException.class})
    public HashMap<String, Object> databaseError(Exception e) {
        saveErrLog(-1,"数据访问异常" + e);
        LOGGER.error("数据访问异常: " + e.getMessage(), e);
        instantService.Send("error", getDeveloper(), "数据访问异常" + e.getMessage());
        return Result.failure(-1, "数据访问异常").map();
    }


    /**
     * 全局访问异常
     *
     * @param req 请求
     * @param ex  异常
     * @return 信息集合
     */
    @ExceptionHandler(Exception.class)
    public HashMap<String, Object> handleError(HttpServletRequest req, Exception ex) {
        saveErrLog(-1,"遭遇未知错误：" + ex);
        LOGGER.error("访问: " + req.getRequestURL() + " 遭遇未知异常 " + ex, ex);
        instantService.Send("error", getDeveloper(), "未知异常" + ex.getMessage());
        return Result.failure(-1, "遭遇未知错误：" + ex.getMessage()).map();
    }

    private void saveErrLog(int i,String str){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        int errCode=i;
        String errMsg=str;
        String url = request.getRequestURL().toString();
        Result<AuthInfo> authInfo = jwtTokenService.getAuthInfo();
        long accountId = authInfo.getErrCode() == 0 ? authInfo.getModel().getId() : 0;
        String ip = request.getRemoteAddr();
        String appVer = request.getHeader("AppVer");
        String dsi = request.getHeader("DSI");
        save(url, accountId, ip, appVer, dsi,errCode,errMsg);
    }

    private void save(String url, long accountId, String ip, String appVer, String dsi,int errCode,String errMsg){
        ErrLog errLog=new ErrLog();
        errLog.setErrUrl(url);
        errLog.setIp(ip);
        errLog.setAccountId(accountId);
        errLog.setAppVer(appVer);
        errLog.setDsi(dsi);
        errLog.setErrCode(errCode);
        errLog.setErrMsg(errMsg);
        errLogService.save(errLog);
    }
}

