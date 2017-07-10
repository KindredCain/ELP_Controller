package com.elp.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by ASUS on 2017/7/2.
 * 课程记录
 * -用户编号 userNum(String varchar 外键)
 * -课程编号 courseNum(String varchar 外键)
 * -课程完成度 courseComplete(double double )(课程统计相关)
 */
@Entity
@Table(name = "tb_courserecord")
public class CourseRecord extends BaseEntity{
    @Column(nullable = false,length = 32)
    private String userNum;
    @Column(nullable = false,length = 32)
    private String courseNum;
    @Column(precision = 5,scale = 2)
    private double courseComplete;

    public String getUserNum() {
        return userNum;
    }

    public void setUserNum(String userNum) {
        this.userNum = userNum;
    }

    public String getCourseNum() {
        return courseNum;
    }

    public void setCourseNum(String courseNum) {
        this.courseNum = courseNum;
    }

    public double getCourseComplete() {
        return courseComplete;
    }

    public void setCourseComplete(double courseComplete) {
        this.courseComplete = courseComplete;
    }
}
