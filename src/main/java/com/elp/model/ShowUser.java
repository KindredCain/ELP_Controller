package com.elp.model;

/**
 * Created by NWJ on 2017/7/10.
 * 查询返回个人资料类
 */
public class ShowUser {
    private String objectId;
    private String logId;
    private String userName;
    private String userPicUrl;
    private Integer studySum;

    public ShowUser() {
    }

    public ShowUser(String objectId, String logId, String userName, String userPicUrl) {
        this.objectId = objectId;
        this.logId = logId;
        this.userName = userName;
        this.userPicUrl = userPicUrl;
    }

    public ShowUser(String objectId, String logId, String userName, String userPicUrl, Long studySum) {
        this.objectId = objectId;
        this.logId = logId;
        this.userName = userName;
        this.userPicUrl = userPicUrl;
        this.studySum = studySum.intValue();
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getLogId() {
        return logId;
    }

    public void setLogId(String logId) {
        this.logId = logId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPicUrl() {
        return userPicUrl;
    }

    public void setUserPicUrl(String userPicUrl) {
        this.userPicUrl = userPicUrl;
    }

    public Integer getStudySum() {
        return studySum;
    }

    public void setStudySum(Integer studySum) {
        this.studySum = studySum;
    }
}
