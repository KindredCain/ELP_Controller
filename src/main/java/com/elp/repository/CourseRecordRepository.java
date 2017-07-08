package com.elp.repository;

import com.elp.model.CourseRecord;
import com.elp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by ASUS on 2017/7/3.
 */
public interface CourseRecordRepository extends JpaRepository<CourseRecord,String> {

    @Query("from CourseRecord courseRecord where courseRecord.delTime is null")
    List<CourseRecord> findAll();

    @Query("from CourseRecord courseRecord where courseRecord.objectId = :objectId and courseRecord.delTime is null")
    CourseRecord findById(@Param("objectId") String objectId);

    //根据课程id 统计学习情况
    @Query("")
    List<Object> findByCourseNumCount(String courseId);

    //根据学生id 统计学习情况
    @Query("")
    List<Object> findByUserNumCount(String userId);
}
