package cn.edu.hust.logMonitor.bean;

import java.io.Serializable;
import java.sql.Timestamp;

public class RuleRecord implements Serializable{
    private Long id;
    private int appId;
    private int ruleId;
    private int isEmail=0;
    private int isPhone=0;
    private int isColse =0;
    private String noticeInfo;
    private Timestamp createDate;
    private Timestamp updateDate;

    public RuleRecord(Long id, int appId, int ruleId, int isEmail, int isPhone, int isClose, String noticeInfo, Timestamp createDate, Timestamp updateDate) {
        this.id = id;
        this.appId = appId;
        this.ruleId = ruleId;
        this.isEmail = isEmail;
        this.isPhone = isPhone;
        this.isColse = isClose;
        this.noticeInfo = noticeInfo;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }

    public RuleRecord() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getAppId() {
        return appId;
    }

    public void setAppId(int appId) {
        this.appId = appId;
    }

    public int getRuleId() {
        return ruleId;
    }

    public void setRuleId(int ruleId) {
        this.ruleId = ruleId;
    }

    public int getIsEmail() {
        return isEmail;
    }

    public void setIsEmail(int isEmail) {
        this.isEmail = isEmail;
    }

    public int getIsPhone() {
        return isPhone;
    }

    public void setIsPhone(int isPhone) {
        this.isPhone = isPhone;
    }

    public int getIsColse() {
        return isColse;
    }

    public void setIsColse(int isColse) {
        this.isColse = isColse;
    }

    public String getNoticeInfo() {
        return noticeInfo;
    }

    public void setNoticeInfo(String noticeInfo) {
        this.noticeInfo = noticeInfo;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public Timestamp getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Timestamp udpateate) {
        this.updateDate = udpateate;
    }

    @Override
    public String toString() {
        return "RuleRecord{" +
                "id=" + id +
                ", appId=" + appId +
                ", ruleId=" + ruleId +
                ", isEmail=" + isEmail +
                ", isPhone=" + isPhone +
                ", isColse=" + isColse +
                ", noticeInfo='" + noticeInfo + '\'' +
                ", createDate=" + createDate +
                ", updateDate=" + updateDate +
                '}';
    }
}
