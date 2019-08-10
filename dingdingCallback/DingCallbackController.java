package com.whzhdc.ding.api.dingCallback;

import com.alibaba.fastjson.JSONObject;
import com.dingtalk.oapi.lib.aes.DingTalkEncryptException;
import com.dingtalk.oapi.lib.aes.DingTalkEncryptor;
import com.whzhdc.ding.service.FlowInstanceCallbackService;
import com.whzhdc.ding.service.IdWorker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class DingCallbackController {

    @Autowired
    private FlowInstanceCallbackService flowInstanceCallbackService;

    private static final Logger LOGGER = LoggerFactory.getLogger(DingCallbackController.class);

    @RequestMapping(value = "/ding/api/ding_callback", method = RequestMethod.POST)
    public JSONObject index(
            @RequestParam(value = "signature", required = false) String msgSignature,
            @RequestParam(value = "timestamp", required = false) String timeStamp,
            @RequestParam(value = "nonce", required = false) String nonce,
            @RequestBody String postData) {

        // STEP 1.获得加密信息
        JSONObject postDataJson = JSONObject.parseObject(postData);
        String encrypt = postDataJson.getString("encrypt");

        // STEP 2.对encrypt进行解密
        DingTalkEncryptor dingTalkEncryptor = null;
        String plainText = null;
        try {
            dingTalkEncryptor = new DingTalkEncryptor(Env.TOKEN, Env.ENCODING_AES_KEY, Env.CORP_ID);
            plainText = dingTalkEncryptor.getDecryptMsg(msgSignature, timeStamp, nonce, encrypt);
        } catch (DingTalkEncryptException e) {
            // TODO Auto-generated catch block
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        LOGGER.info("plainText: {}", plainText);
        // STEP 3.对从encrypt解密出来的明文进行处理
        JSONObject plainTextJson = JSONObject.parseObject(plainText);
        String eventType = plainTextJson.getString("EventType");
        switch (eventType) {
            case "bpms_task_change": {
                LOGGER.info("bpms_task_change: {}", plainText);
            }
            case "bpms_instance_change": {
                LOGGER.info("bpms_instance_change: {}", plainText);
                flowInstanceCallbackService.index(plainText);
            }
            case "org_user_add"://do something
                break;
            case "org_user_modify"://do something
                break;
            case "org_user_leave":// do something
                break;
            case "check_url"://do something
            {
                // STEP 4.对返回信息进行加密
                long timeStampLong = Long.parseLong(timeStamp);
                Map<String, String> jsonMap = null;
                try {
                    jsonMap = dingTalkEncryptor.getEncryptedMap("success", timeStampLong, nonce);
                } catch (DingTalkEncryptException e) {
                    System.out.println(e.getMessage());
                    e.printStackTrace();
                }
                JSONObject json = new JSONObject();
                json.putAll(jsonMap);

                return json;
            }
            default: //do something
                break;
        }

        return new JSONObject();
    }
}
