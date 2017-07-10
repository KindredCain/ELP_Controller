package com.elp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by ASUS on 2017/7/2.
 * 课程、职位中间表
 * -对应职位编号 officeNum(String varchar)
 * -对应课程编号 courseNum(String varchar)
 * -方向名称  subjectName(String varchar)
 */
@Entity
@Table(name = "tb_course_office")
public class CourseRelationOffice extends BaseEntity{
    @Column(nullable = false,length = 32)
    private String officeNum;
    @Column(nullable = false,length = 32)
    private String courseNum;
    @Column(length = 32)
    private String subjectName;

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getOfficeNum() {
        return officeNum;
    }

    public void setOfficeNum(String officeNum) {
        this.officeNum = officeNum;
    }

    public String getCourseNum() {
        return courseNum;
    }

    public void setCourseNum(String courseNum) {
        this.courseNum = courseNum;
    }
}
