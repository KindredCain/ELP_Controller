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
public interface CourseRepository extends JpaRepository<Course,String> {

    @Query("from Course course where course.delTime is null")
    List<Course> findAll();

    @Query("from Course course where course.objectId = :objectId and course.delTime is null")
    Course findById(@Param("objectId") String objectId);

    //根据课程名模糊查询课程
    @Query(value = "SELECT tb_course.* "+
            "FROM tb_course, tb_user, tb_course_office "+
            "where tb_user.object_id = ?1 and tb_user.office_num = tb_course_office.office_num and tb_course_office.course_num = tb_course.object_id "+
            "and tb_course.course_name like ?2 ")
    Course findByCourseNameLike(String userId,String courseName);

    //
    @Query(value = "")
    Object[] findByCourseIdANDUserId();

    @Query(value = "")
    List<Object[]> findByUserIdOrderByLastViewTime();

    @Query(value = "")
    List<Course> findByOfficeIdAndCourseId();
}
