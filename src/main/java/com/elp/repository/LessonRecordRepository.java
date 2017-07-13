package com.elp.repository;

import com.elp.model.LessonRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by ASUS on 2017/7/3.
 */
public interface LessonRecordRepository extends JpaRepository<LessonRecord,String> {

    @Query("from LessonRecord lessonRecord where lessonRecord.delTime is null")
    List<LessonRecord> findAll();

    @Query("from LessonRecord lessonRecord where lessonRecord.objectId = :objectId and lessonRecord.delTime is null")
    LessonRecord findById(@Param("objectId") String objectId);

    List<LessonRecord> findAllByLessonNumAndDelTimeIsNull(String lessonNum);

    @Modifying
    @Query(value = "update LessonRecord lessonRecord " +
            "set lessonRecord.delTime = ?2 " +
            "where lessonRecord.userNum = ?1")
    void deleteByUserNum(String userNum, Timestamp delTime);

    @Modifying
    @Query(value = "update LessonRecord lessonRecord " +
            "set lessonRecord.delTime = ?2 " +
            "where lessonRecord.lessonNum = ?1")
    void deleteByLessonNum(String lessonNum, Timestamp delTime);

    @Modifying
    @Query(value = "update tb_lessonrecord, tb_lesson " +
            "set tb_lessonrecord.del_time = ?2 " +
            "where tb_lesson.object_id = tb_lessonrecord.lesson_num " +
            "and tb_lesson.course_num = ?1", nativeQuery = true)
    void deleteByCourseNum(String courseNum, Timestamp delTime);
}
