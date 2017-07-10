package com.elp.model;

import javax.persistence.*;
import java.util.Date;
/**
 * Created by ASUS on 2017/7/2.
 * 消息
 * -接收用户编号 recUser(String varchar 外键)
 * -发送用户编号 sendUser(String varchar 外键)
 * -消息类型 msgType(String varchar)
 * -消息内容 msgContent(String varchar)
 * -是否被查看过 msgStats(String varchar )
 * -课程编号 courseNum(String varchar 外键)
 * -课时编号 lessonNum(String varchar 外键)
 */
@Entity
@Table(name = "tb_msg")
public class Msg extends BaseEntity{
    @Column(nullable = false,length = 32)
    private String recUser;
    @Column(length = 32)
    private String sendUser;
    @Column(nullable = false,length = 21)
    private String msgType;
    @Column(nullable = false,length = 51)
    private String msgContent;
    @Column(nullable = false,length = 21)
    private String msgStats;
    @Column(length = 32)
    private String courseNum;
    @Column(length = 32)
    private String  lessonNum;

    public String getCourseNum() {
        return courseNum;
    }

    public void setCourseNum(String courseNum) {
        this.courseNum = courseNum;
    }

    public String getLessonNum() {
        return lessonNum;
    }

    public void setLessonNum(String lessonNum) {
        this.lessonNum = lessonNum;
    }

    public String getRecUser() {
        return recUser;
    }

    public void setRecUser(String recUser) {
        this.recUser = recUser;
    }

    public String getSendUser() {
        return sendUser;
    }

    public void setSendUser(String sendUser) {
        this.sendUser = sendUser;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public String getMsgContent() {
        return msgContent;
    }

    public void setMsgContent(String msgContent) {
        this.msgContent = msgContent;
    }

    public String getMsgStats() {
        return msgStats;
    }

    public void setMsgStats(String msgStats) {
        this.msgStats = msgStats;
    }
}
