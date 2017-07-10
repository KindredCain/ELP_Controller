package com.elp.model;

import javax.persistence.*;
/**
 * Created by ASUS on 2017/7/2.
 * 课时
 * -所属课程编号courseNum(String varchar 外键)
 * -课时名lessonName（String varchar ）
 * -课时介绍lessonInfo(String varchar )
 * -课时预计完成时间 expectComplete(String varchar)
 * -课时课件类型 lessonType(String varchar)
 */
@Entity
@Table(name = "tb_lesson")
public class Lesson extends BaseEntity{
    @Column(nullable = false,length = 32)
    private String courseNum;
    @Column(nullable = false,length = 32)
    private String lessonName;
    @Column(length = 51)
    private String lessonInfo;
    @Column(length = 51)
    private String expectComplete;
    @Column(nullable = false,length = 32)
    private String lessonType;

    public String getCourseNum() {
        return courseNum;
    }

    public void setCourseNum(String courseNum) {
        this.courseNum = courseNum;
    }

    public String getLessonName() {
        return lessonName;
    }

    public void setLessonName(String lessonName) {
        this.lessonName = lessonName;
    }

    public String getLessonInfo() {
        return lessonInfo;
    }

    public void setLessonInfo(String lessonInfo) {
        this.lessonInfo = lessonInfo;
    }

    public String getExpectComplete() {
        return expectComplete;
    }

    public void setExpectComplete(String expectComplete) {
        this.expectComplete = expectComplete;
    }

    public String getLessonType() {
        return lessonType;
    }

    public void setLessonType(String lessonType) {
        this.lessonType = lessonType;
    }
}
