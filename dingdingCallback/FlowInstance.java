package com.whzhdc.ding.api.dingCallback;

public class FlowInstance {

    private long id;

    private long createdBy;

    private String createdName;

    private long createdTs;

    private long updatedBy;

    private String updatedName;

    private long updatedTs;

    private long projectId;

    /**
     * 资源id
     */
    private long resourceId;

    /**
     * 流程ID
     */
    private long flowId;

    /**
     * 当前流程实例最新步骤的ID
     */
    private long lastStateId;

    /**
     * 流程当前所处节点
     */
    private int step;

    private int state;

    private String remark;

    /**
     * 摘要
     */
    private String summary;

    /**
     * 审批时间
     */
    private long auditTs;

    private  String dingdingInstanceId;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(long createdBy) {
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

    public long getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(long updatedBy) {
        this.updatedBy = updatedBy;
    }

    public String getUpdatedName() {
        return updatedName;
    }

    public void setUpdatedName(String updatedName) {
        this.updatedName = updatedName;
    }

    public long getUpdatedTs() {
        return updatedTs;
    }

    public void setUpdatedTs(long updatedTs) {
        this.updatedTs = updatedTs;
    }

    public long getResourceId() {
        return resourceId;
    }

    public void setResourceId(long resourceId) {
        this.resourceId = resourceId;
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

    public long getProjectId() {
        return projectId;
    }

    public void setProjectId(long projectId) {
        this.projectId = projectId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public long getLastStateId() {
        return lastStateId;
    }

    public void setLastStateId(long lastStateId) {
        this.lastStateId = lastStateId;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
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
        return "FlowInstance{" +
                "id=" + id +
                ", createdBy=" + createdBy +
                ", createdName='" + createdName + '\'' +
                ", createdTs=" + createdTs +
                ", updatedBy=" + updatedBy +
                ", updatedName='" + updatedName + '\'' +
                ", updatedTs=" + updatedTs +
                ", projectId=" + projectId +
                ", resourceId=" + resourceId +
                ", flowId=" + flowId +
                ", lastStateId=" + lastStateId +
                ", step=" + step +
                ", state=" + state +
                ", remark='" + remark + '\'' +
                ", summary='" + summary + '\'' +
                ", auditTs=" + auditTs +
                ", dingdingInstanceId='" + dingdingInstanceId + '\'' +
                '}';
    }
}
