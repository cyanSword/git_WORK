package com.whzhdc.ding.api.dingCallback;

public class FlowInstanceDTO {

    private long id;

    private String createdBy;

    private String createdName;

    private long createdTs;

    private long flowId;

    private int step;

    private int state;

    private String updatedBy;

    private String updatedName;

    private String remark;

    private long resourceId;

    private String summary;

    private long projectId;

    private long lastStateId;

    private long auditTs;

    private String dingdingInstanceId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getCreatedName() {
        return createdName;
    }

    public void setCreatedName(String createdName) {
        this.createdName = createdName;
    }

    public long getCreatedTs() {
        return createdTs;
    }

    public void setCreatedTs(long createdTs) {
        this.createdTs = createdTs;
    }

    public long getFlowId() {
        return flowId;
    }

    public void setFlowId(long flowId) {
        this.flowId = flowId;
    }

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public String getUpdatedName() {
        return updatedName;
    }

    public void setUpdatedName(String updatedName) {
        this.updatedName = updatedName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public long getResourceId() {
        return resourceId;
    }

    public void setResourceId(long resourceId) {
        this.resourceId = resourceId;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public long getProjectId() {
        return projectId;
    }

    public void setProjectId(long projectId) {
        this.projectId = projectId;
    }

    public long getLastStateId() {
        return lastStateId;
    }

    public void setLastStateId(long lastStateId) {
        this.lastStateId = lastStateId;
    }

    public long getAuditTs() {
        return auditTs;
    }

    public void setAuditTs(long auditTs) {
        this.auditTs = auditTs;
    }

    public String getDingdingInstanceId() {
        return dingdingInstanceId;
    }

    public void setDingdingInstanceId(String dingdingInstanceId) {
        this.dingdingInstanceId = dingdingInstanceId;
    }

    @Override
    public String toString() {
        return "FlowInstanceDTO{" +
                "id=" + id +
                ", createdBy='" + createdBy + '\'' +
                ", createdName='" + createdName + '\'' +
                ", createdTs=" + createdTs +
                ", flowId=" + flowId +
                ", step=" + step +
                ", state=" + state +
                ", updatedBy='" + updatedBy + '\'' +
                ", updatedName='" + updatedName + '\'' +
                ", remark='" + remark + '\'' +
                ", resourceId=" + resourceId +
                ", summary='" + summary + '\'' +
                ", projectId=" + projectId +
                ", lastStateId=" + lastStateId +
                ", auditTs=" + auditTs +
                ", dingdingInstanceId='" + dingdingInstanceId + '\'' +
                '}';
    }
}
