package com.elp.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by ASUS on 2017/7/2.
 * 课时记录表
 * -所属用户编号userNum
 * -所属课时编号 lessonNum
 * -课时观看记录 lessonRecord
 */

@Entity
@Table(name = "tb_lessonrecord")
public class LessonRecord extends BaseEntity{
    @Column(nullable = false,length = 32)
    private String userNum;
    @Column(nullable = false,length = 32)
    private String lessonNum;
    @Column(length = 32)
    private String lessonRecord;

    public LessonRecord(){

    }
    public LessonRecord(String objectId, Date createTime, Date delTime, Date updateTime, String userNum, String lessonNum, String lessonRecord) {
        this.setObjectId(objectId);
        this.setCreatTime(createTime);
        this.setDelTime(delTime);
        this.setUpdateTime(updateTime);
        this.userNum = userNum;
        this.lessonNum = lessonNum;
        this.lessonRecord = lessonRecord;
    }

    public String getUserNum() {
        return userNum;
    }

    public void setUserNum(String userNum) {
        this.userNum = userNum;
    }

    public String getLessonNum() {
        return lessonNum;
    }

    public void setLessonNum(String lessonNum) {
        this.lessonNum = lessonNum;
    }

    public String getLessonRecord() {
        return lessonRecord;
    }

    public void setLessonRecord(String lessonRecord) {
        this.lessonRecord = lessonRecord;
    }
}
