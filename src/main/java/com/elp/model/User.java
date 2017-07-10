package com.elp.model;

import javax.persistence.*;
/**
 * Created by ASUS on 2017/7/2.
 * 用户
 * -所属职位编号 officeNum(String varchar 外键)
 * -用户登录id logId(String varchar)
 * -用户密码 pwd(String varchar)
 * -用户类型 userType(String varchar)
 * -用户名 userName(Stirng varchar)
 * -用户头像 userPicUrl(Stirng varchar)
 * -用户权限 userPower(int int)
 */
@Entity
@Table(name = "tb_user")
public class User extends BaseEntity{
    @Column(nullable = false,length = 32)
    private String officeNum;
    @Column(nullable = false,unique = true,length = 32)
    private String logId;
    @Column(nullable = false,length = 32)
    private String pwd;
    @Column(nullable = false,length = 32)
    private String userType;
    @Column(nullable = false,length = 32)
    private String userName;
    @Column(length = 200)
    private String userPicUrl;
    @Column(nullable = false)
    private int userPower;

    public String getOfficeNum() {
        return officeNum;
    }

    public void setOfficeNum(String officeNum) {
        this.officeNum = officeNum;
    }

    public String getLogId() {
        return logId;
    }

    public void setLogId(String logId) {
        this.logId = logId;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
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

    public int getUserPower() {
        return userPower;
    }

    public void setUserPower(int userPower) {
        this.userPower = userPower;
    }
}
