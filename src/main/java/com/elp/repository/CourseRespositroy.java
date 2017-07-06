package com.elp.repository;

import com.elp.model.Course;
import com.elp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by ASUS on 2017/7/3.
 */
public interface CourseRespositroy extends JpaRepository<Course,String> {

    @Query("from Course course where course.delTime is null")
    List<Course> findAll();

    @Query("from Course course where course.objectId = :objectId AND course.delTime is null")
    Course findOne(@Param("objectId") String objectId);

    @Query("from Course course where course.objectId = :objectId AND course.delTime is null")
    List<Course> findById(@Param("objectId") String objectId);

}
