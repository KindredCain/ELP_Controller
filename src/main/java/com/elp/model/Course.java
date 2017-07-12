package com.elp.model;

import javax.persistence.*;
/**
 * Created by ASUS on 2017/7/2.
 * 课程
 * -所属管理员用户编号 adminNum（String varchar 外键）
 * -课程名称 courseName(String varchar)
 * -课程目录url courseUrl( String varchar)
 * -课程总课时数 courseSumLesson(double double)(课程统计相关)
 * -课程预计完成时间 expectComplete(String varchar)
 * -课程权限 coursePower(int int)
 * -课程介绍 courseInfo(String varchar )
<<<<<<< HEAD
 * -图片地址 coursePic(String varchar)
=======
>>>>>>> refs/remotes/KindredCain/develop
 */
@Entity
@Table(name = "tb_course")
public class Course extends BaseEntity{

    @Column(nullable = false,length = 32)
    private String adminNum;
    @Column(nullable = false,length = 32)
    private String courseName;
    @Column(length = 255)
    private String courseUrl;
    //总长度 小数点后的位数
    @Column(precision = 5,scale = 2)
    private double courseSumLesson;
    @Column(length = 51)
    private String expectComplete;
    @Column(nullable = false)
    private int coursePower;
    @Column(length = 51)
    private String courseInfo;
    @Column(length = 255)
    private String coursePic;

    public String getCoursePic() {
        return coursePic;
    }

    public void setCoursePic(String coursePic) {
        this.coursePic = coursePic;
    }

    public String getCourseInfo() {
        return courseInfo;
    }

    public void setCourseInfo(String courseInfo) {
        this.courseInfo = courseInfo;
    }

    public String getAdminNum() {
        return adminNum;
    }

    public void setAdminNum(String adminNum) {
        this.adminNum = adminNum;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseUrl() {
        return courseUrl;
    }

    public void setCourseUrl(String courseUrl) {
        this.courseUrl = courseUrl;
    }

    public double getCourseSumLesson() {
        return courseSumLesson;
    }

    public void setCourseSumLesson(double courseSumLesson) {
        this.courseSumLesson = courseSumLesson;
    }

    public String getExpectComplete() {
        return expectComplete;
    }

    public void setExpectComplete(String expectComplete) {
        this.expectComplete = expectComplete;
    }

    public int getCoursePower() {
        return coursePower;
    }

    public void setCoursePower(int coursePower) {
        this.coursePower = coursePower;
    }
}
