package com.elp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by ASUS on 2017/7/4.
 * 关联课程(RelationCourse）
 *
 -关联课程Aid courseAId (String varchar)
 -关联课程Bid courseBId (String varchar)

 */
@Entity
@Table(name = "tb_relationcourse")
public class RelationCourse extends BaseEntity{
    @Column(nullable = false,length = 32)
    private String courseAId;
    @Column(nullable = false,length = 32)
    private String courseBId;

    public String getCourseAId() {
        return courseAId;
    }

    public void setCourseAId(String courseAId) {
        this.courseAId = courseAId;
    }

    public String getCourseBId() {
        return courseBId;
    }

    public void setCourseBId(String courseBId) {
        this.courseBId = courseBId;
    }
}
