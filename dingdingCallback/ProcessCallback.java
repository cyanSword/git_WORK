package com.whzhdc.ding.api.dingCallback;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ProcessCallback {

    @Id
    private long id;

    private String createTime;

    private String staffId;

    private String bizCategoryId;

    private String eventType;

    private String type;

    private String url;

    private String title;

    private String processCode;

    private String processInstanceId;

    private long auditTs;

    private long projectId;

    private long instanceId;


    public long getAuditTs() {
        return auditTs;
    }

    public void setAuditTs(long auditTs) {
        this.auditTs = auditTs;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public String getBizCategoryId() {
        return bizCategoryId;
    }

    public void setBizCategoryId(String bizCategoryId) {
        this.bizCategoryId = bizCategoryId;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getProcessCode() {
        return processCode;
    }

    public void setProcessCode(String processCode) {
        this.processCode = processCode;
    }

    public String getProcessInstanceId() {
        return processInstanceId;
    }

    public void setProcessInstanceId(String processInstanceId) {
        this.processInstanceId = processInstanceId;
    }

    public long getProjectId() {
        return projectId;
    }

    public void setProjectId(long projectId) {
        this.projectId = projectId;
    }

    public long getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(long instanceId) {
        this.instanceId = instanceId;
    }

    @Override
    public String toString() {
        return "ProcessCallback{" +
                "id=" + id +
                ", createTime='" + createTime + '\'' +
                ", staffId='" + staffId + '\'' +
                ", bizCategoryId='" + bizCategoryId + '\'' +
                ", eventType='" + eventType + '\'' +
                ", type='" + type + '\'' +
                ", url='" + url + '\'' +
                ", title='" + title + '\'' +
                ", processCode='" + processCode + '\'' +
                ", processInstanceId='" + processInstanceId + '\'' +
                ", auditTs=" + auditTs +
                ", projectId=" + projectId +
                ", instanceId=" + instanceId +
                '}';
    }
}
