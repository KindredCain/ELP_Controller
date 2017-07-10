package com.elp.repository;

import com.elp.model.CourseRecord;
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
    @Query("from CourseRecord courseRecord where courseRecord.courseNum = :courseNum and courseRecord.delTime is null")
    List<CourseRecord> findByCourseNum(@Param("courseNum") String courseNum);

    //根据学生id 统计学习情况
    @Query("from CourseRecord courseRecord where courseRecord.userNum = :userNum and courseRecord.delTime is null")
    List<CourseRecord> findByUserNum(@Param("userNum") String userNum);

    //根据用户id和课程id返回课程记录相关类
    @Query("from CourseRecord courseRecord where courseRecord.courseNum = ?1 and courseRecord.userNum = ?2 and  courseRecord.delTime is null")
    List<CourseRecord> findByCourseNumAndUserNum(String userNum,String CourseNum);
}
