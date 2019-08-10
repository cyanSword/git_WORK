package com.whzhdc.ding.api.module;

import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.*;
import com.dingtalk.api.response.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Component
public class DingDingUtil {


    final static String appkey = "xxxx";

    final static String appsecret = "xxxx";

    final static long agentID = 0l;

    public static String getToken() {

        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/gettoken");
        OapiGettokenRequest req = new OapiGettokenRequest();
        req.setAppkey(appkey);
        req.setAppsecret(appsecret);
        req.setHttpMethod("GET");
        String token = "";
        try {
            OapiGettokenResponse rsp = client.execute(req);
            token = rsp.getAccessToken();
        } catch (Exception e) {

        }
        return token;
    }

    public static void sendMock(String access_token) {


        DingTalkClient client = new DefaultDingTalkClient("https://eco.taobao.com/router/rest");
        CorpMessageCorpconversationSendmockRequest req = new CorpMessageCorpconversationSendmockRequest();
        req.setMicroappAgentId(agentID);
        req.setToUser("034313202624058235");
        req.setToParty("");
        req.setMessageType("TEXT");
        req.setMessageString("{'content':'测试消息'}");
        try {
            CorpMessageCorpconversationSendmockResponse rsp = client.execute(req, access_token);
            System.out.println(rsp.getBody());
            System.out.println(rsp.getResult());
        } catch (Exception e) {

        }
//        DingTalkClient client = new DefaultDingTalkClient("https://eco.taobao.com/router/rest");
//        SmartworkAttendsListscheduleRequest req = new SmartworkAttendsListscheduleRequest();
//        req.setWorkDate(StringUtils.parseDateTime("2019-01-24 19:00:00"));
//        req.setOffset(0L);
//        req.setSize(200L);
//
//        try {
//            SmartworkAttendsListscheduleResponse rsp = client.execute(req, access_token);
//            System.out.println(rsp.getBody());
//        }catch (Exception e){
//
//        }


    }

    public static void getRoleList(String access_token) {
        DingTalkClient client = new DefaultDingTalkClient("https://eco.taobao.com/router/rest");
        CorpRoleListRequest req = new CorpRoleListRequest();
        req.setSize(20L);
        req.setOffset(0L);
        try {
            CorpRoleListResponse rsp = client.execute(req, access_token);
            System.out.println(rsp.getBody());
        } catch (Exception e) {

        }
    }

    public static void getRoleUser(String access_token) {
        DingTalkClient client = new DefaultDingTalkClient("https://eco.taobao.com/router/rest");
        CorpRoleSimplelistRequest req = new CorpRoleSimplelistRequest();
        req.setRoleId(305798605l);
        req.setSize(20L);
        req.setOffset(0L);
        try {
            CorpRoleSimplelistResponse rsp = client.execute(req, access_token);
            System.out.println(rsp.getBody());
        } catch (Exception e) {

        }
    }

    public static void getUserGroup(String access_token) {
        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/topapi/attendance/getusergroup");
        OapiAttendanceGetusergroupRequest req = new OapiAttendanceGetusergroupRequest();
        req.setUserid("2001296408881447");
        try {
            OapiAttendanceGetusergroupResponse rsp = client.execute(req, access_token);
            System.out.println(rsp.getBody());
            System.out.println(rsp.getResult());
        } catch (Exception e) {

        }
    }

    public static void userget(String access_token) {
        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/user/get");
        OapiUserGetRequest req = new OapiUserGetRequest();
        req.setUserid("034313202624058235");
        req.setHttpMethod("GET");
        try {
            OapiUserGetResponse rsp = client.execute(req, access_token);
            System.out.println(rsp.getBody());
        } catch (Exception e) {

        }
    }

    //发起申请流程
    public static String adapt(String access_token, long agentId, Map<String, String> map, String processCode) {
        DefaultDingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/topapi/processinstance/create");
        OapiProcessinstanceCreateRequest request = new OapiProcessinstanceCreateRequest();
        //目前写死
        request.setAgentId(agentId);
        List<OapiProcessinstanceCreateRequest.FormComponentValueVo> formComponentValues = new ArrayList<OapiProcessinstanceCreateRequest.FormComponentValueVo>();
        for (Map.Entry<String, String> pair : map.entrySet()) {
            OapiProcessinstanceCreateRequest.FormComponentValueVo obj = new OapiProcessinstanceCreateRequest.FormComponentValueVo();
            obj.setName(pair.getKey());
            obj.setValue(pair.getValue());
            formComponentValues.add(obj);
        }

        request.setProcessCode(processCode);
        request.setFormComponentValues(formComponentValues);

        //流程审批人id，加一个就多一个人审批，第一个先批，后面后批
        request.setApprovers("123456789");
        //流程发起人
        request.setOriginatorUserId("123456789");
        //部门id  目前没有明确部门这里-1是最高级
        request.setDeptId(-1L);
        //抄送人id
        //034313202624058235,2001296408881447
        request.setCcList("");
        //抄送响应事件  这里是流程发起就抄送
        request.setCcPosition("START_FINISH");

        try {
            OapiProcessinstanceCreateResponse response = client.execute(request, access_token);
            System.out.println(response.getBody());
            return response.getProcessInstanceId();
        } catch (Exception e) {
            return null;
        }
    }

    //发起请假申请流程
    public static void workAdd(String access_token) {
        DefaultDingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/topapi/processinstance/create");
        OapiProcessinstanceCreateRequest request = new OapiProcessinstanceCreateRequest();
        request.setAgentId(agentID);
        request.setProcessCode("PROC-6CB9BD33-572E-47FA-B05D-11DA8E80AE4B");
        List<OapiProcessinstanceCreateRequest.FormComponentValueVo> formComponentValues = new ArrayList<OapiProcessinstanceCreateRequest.FormComponentValueVo>();
        OapiProcessinstanceCreateRequest.FormComponentValueVo obj3 = new OapiProcessinstanceCreateRequest.FormComponentValueVo();
        obj3.setName("请假类型");
        obj3.setValue("事假");
        formComponentValues.add(obj3);
        OapiProcessinstanceCreateRequest.FormComponentValueVo obj4 = new OapiProcessinstanceCreateRequest.FormComponentValueVo();
        obj4.setName("开始时间");
        obj4.setValue("2019-01-24 17:00");
        formComponentValues.add(obj4);
        OapiProcessinstanceCreateRequest.FormComponentValueVo obj5 = new OapiProcessinstanceCreateRequest.FormComponentValueVo();
        obj5.setName("结束时间");
        obj5.setValue("2019-01-24 18:00");
        formComponentValues.add(obj5);
        OapiProcessinstanceCreateRequest.FormComponentValueVo obj6 = new OapiProcessinstanceCreateRequest.FormComponentValueVo();
        obj6.setName("时长(小时)");
        obj6.setValue("1");
        formComponentValues.add(obj6);
        request.setFormComponentValues(formComponentValues);
        request.setApprovers("034313202624058235,2001296408881447");
        request.setOriginatorUserId("2001296408881447");
        request.setDeptId(-1L);
        request.setCcList("034313202624058235,2001296408881447");
        request.setCcPosition("START_FINISH");

        try {
            OapiProcessinstanceCreateResponse response = client.execute(request, access_token);
            System.out.println(response.getBody());
        } catch (Exception e) {

        }
    }

    //查询已发起申请流程id
    public static void queryDingdingInstanceId(String access_token, String processCode) {
        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/topapi/processinstance/listids");
        OapiProcessinstanceListidsRequest req = new OapiProcessinstanceListidsRequest();
        req.setProcessCode(processCode);
        req.setUseridList("2001296408881447");
        req.setStartTime(1548669492177l);
        req.setEndTime(1568669492177l);
        req.setSize(10L);
        req.setCursor(0L);
        try {
            OapiProcessinstanceListidsResponse response = client.execute(req, access_token);
            System.out.println(response.getBody());
        } catch (Exception e) {

        }
    }


    //查询已发起请假申请流程id
    public static void reanameProcessList(String access_token) {
        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/topapi/processinstance/listids");
        OapiProcessinstanceListidsRequest req = new OapiProcessinstanceListidsRequest();
        req.setProcessCode("PROC-RFYJI8DV-TOZ2KXII1QQXUA36SBMS3-0WM22ASJ-E4");
        req.setUseridList("2001296408881447");
        req.setStartTime(1548669492177l);
        req.setEndTime(1568669492177l);
        req.setSize(10L);
        req.setCursor(0L);
        try {
            OapiProcessinstanceListidsResponse response = client.execute(req, access_token);
            System.out.println(response.getBody());
        } catch (Exception e) {

        }
    }

    //查询已发起更名申请流程id
    public static void renameProcessList(String access_token) {
        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/topapi/processinstance/listids");
        OapiProcessinstanceListidsRequest req = new OapiProcessinstanceListidsRequest();
        req.setProcessCode("PROC-RFYJI8DV-TOZ2KXII1QQXUA36SBMS3-0WM22ASJ-E4");
        req.setUseridList("2001296408881447");
        req.setStartTime(1548669492177l);
        req.setEndTime(1568669492177l);
        req.setSize(10L);
        req.setCursor(0L);
        try {
            OapiProcessinstanceListidsResponse response = client.execute(req, access_token);
            System.out.println(response.getBody());
        } catch (Exception e) {

        }
    }


    //查询已发起换房申请流程id
    public static void exchangeProcessList(String access_token) {
        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/topapi/processinstance/listids");
        OapiProcessinstanceListidsRequest req = new OapiProcessinstanceListidsRequest();
        req.setProcessCode("PROC-JFYJ1UPV-92038RC03VWFHD3844GM2-673A2ASJ-22");
        req.setUseridList("2001296408881447");
        req.setStartTime(1548669492177l);
        req.setEndTime(1568669492177l);
        req.setSize(10L);
        req.setCursor(0L);
        try {
            OapiProcessinstanceListidsResponse response = client.execute(req, access_token);
            System.out.println(response.getBody());
        } catch (Exception e) {

        }
    }

    //查询已发起退房申请流程id
    public static void refundProcessList(String access_token, String processCode) {

        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/topapi/processinstance/listids");
        OapiProcessinstanceListidsRequest req = new OapiProcessinstanceListidsRequest();
        req.setProcessCode("PROC-VFYJYF2V-XM03CSIX15UUD52S5YZN3-PFHH2ASJ-L3");
        req.setUseridList("2001296408881447");
        req.setStartTime(1548669492177l);
        req.setEndTime(1568669492177l);
        req.setSize(10L);
        req.setCursor(0L);
        try {
            OapiProcessinstanceListidsResponse response = client.execute(req, access_token);
            System.out.println(response.getBody());
        } catch (Exception e) {

        }
    }


    //查询已发起退费申请流程id
    public static void quitProcessList(String access_token) {
        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/topapi/processinstance/listids");
        OapiProcessinstanceListidsRequest req = new OapiProcessinstanceListidsRequest();
        req.setProcessCode("PROC-5FYJY38V-SJZ2DNVE5MU7X7CJUVEN3-SMMO2ASJ-53");
        req.setUseridList("2001296408881447");
        req.setStartTime(1548669492177l);
        req.setEndTime(1568669492177l);
        req.setSize(10L);
        req.setCursor(0L);
        try {
            OapiProcessinstanceListidsResponse response = client.execute(req, access_token);
            System.out.println(response.getBody());
        } catch (Exception e) {

        }
    }

    //根据流程id查询流程状态
    public static OapiProcessinstanceGetResponse.ProcessInstanceTopVo processOne(String access_token, String processInstanceId) {
        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/topapi/processinstance/get");
        OapiProcessinstanceGetRequest request = new OapiProcessinstanceGetRequest();
        request.setProcessInstanceId(processInstanceId);
        try {
            OapiProcessinstanceGetResponse response = client.execute(request, access_token);
            //return response.getBody();
            OapiProcessinstanceGetResponse.ProcessInstanceTopVo dto = response.getProcessInstance();
            System.out.println(dto.getStatus() + "\t" + dto.getResult() + "\t" + dto.getOriginatorUserid());
            return dto;
        } catch (Exception e) {
            return null;
        }
    }

    //获取根目录下的子部门列表

    /**
     * 64254218招商部
     * 64268497行政部
     * 99242130技术部
     */
    public static void getDeptList(String access_token) {
        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/department/list");
        OapiDepartmentListRequest request = new OapiDepartmentListRequest();
        request.setId("1");
        request.setHttpMethod("GET");
        try {
            OapiDepartmentListResponse response = client.execute(request, access_token);
            List<OapiDepartmentListResponse.Department> department = response.getDepartment();
            for (OapiDepartmentListResponse.Department dto : department) {
                System.out.println(dto.getId() + dto.getName());
            }

        } catch (Exception e) {

        }
    }


    public static void getDeptUser(String access_token) {
        //简单信息
        /**
         * "userlist":[{"name":"杜笔成","userid":"1111"}]
         */
//        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/user/simplelist");
//        OapiUserSimplelistRequest request = new OapiUserSimplelistRequest();
//        request.setDepartmentId(99242130l);
//        request.setOffset(0L);
//        request.setSize(10L);
//        request.setHttpMethod("GET");
//
//        try {
//            OapiUserSimplelistResponse response = client.execute(request, access_token);
//            System.out.println(response.getBody());
//        } catch (Exception e) {
//
//        }

        //详细信息    员工id由钉钉新加员工时手动生成未填写时由钉钉自动生成
        /**
         * "userlist":[{"position":"测试员工","remark":"","department":[1,99242130],
         * "tel":"1101","unionid":"I3xeiPeVCJs8FLPiiA4paZbAiEiE",
         * "userid":"1111","workPlace":"","isBoss":false,
         * "order":176369211510110512,"name":"杜笔成",
         * "isLeader":false,"avatar":"https://static.dingtalk.com/media/lADPDgQ9qaw4XmXNAb3NAb4_446_445.jpg",
         * "jobnumber":"1001","email":"","active":true,"isAdmin":false,"openId":"I3xeiPeVCJs8FLPiiA4paZbAiEiE","isHide":false}]
         */
        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/user/listbypage");
        OapiUserListbypageRequest request = new OapiUserListbypageRequest();
        request.setDepartmentId(99242130l);
        request.setOffset(0L);
        request.setSize(10L);
        request.setOrder("entry_desc");
        request.setHttpMethod("GET");
        try {
            OapiUserListbypageResponse execute = client.execute(request, access_token);
            System.out.println(execute.getBody());
        } catch (Exception e) {

        }
    }


    public static void callBack(String access_token) {
        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/call_back/register_call_back");
        OapiCallBackRegisterCallBackRequest request = new OapiCallBackRegisterCallBackRequest();
        request.setUrl("http://www.whzhdc.com");
        request.setAesKey("1234567890123456789012345678901234567890123");
        request.setToken("sword");
        request.setCallBackTag(Arrays.asList("bpms_task_change", "bpms_instance_change"));
        try {
            OapiCallBackRegisterCallBackResponse response = client.execute(request, access_token);
            System.out.println(response.getBody());
        } catch (Exception e) {

        }
    }

    public static void main(String[] args) {
        String access_token = getToken();
//        System.out.println(access_token);
        //sendMock(access_token);
        //getRoleList(access_token);
        //getRoleUser(access_token);
        //getUserGroup(access_token);
        //userget(access_token);
        //processList(access_token, "PROC-5FYJY38V-SJZ2DNVE5MU7X7CJUVEN3-SMMO2ASJ-53");
        OapiProcessinstanceGetResponse.ProcessInstanceTopVo dto = processOne(access_token, "3b7fbeb0-e8fc-4323-a029-e97b4e4b6cb5");
        System.out.println(dto);
        //workAdd(access_token);
        //getDeptList(access_token);
        //getDeptUser(access_token);
        //callBack(access_token);

    }
}
