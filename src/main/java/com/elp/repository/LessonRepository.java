package com.elp.repository;

import com.elp.model.Lesson;
import com.elp.model.LessonRecord;
import com.elp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by ASUS on 2017/7/3.
 */
public interface LessonRepository extends JpaRepository<Lesson,String> {

    @Query("from Lesson lesson where lesson.delTime is null")
    List<Lesson> findAll();

    @Query("from Lesson lesson where lesson.objectId = :objectId and lesson.delTime is null")
    Lesson findById(@Param("objectId") String objectId);

    @Query("from Lesson lesson where lesson.courseNum = ?1 and lesson.delTime is null")
    List<Lesson> findByCourseNum(String courseNum);

    @Query(value = "from Lesson lesson, LessonRecord lessonRecord " +
            "where  lesson.objectId = lessonRecord.lessonNum " +
            "and  lesson.delTime is null " +
            "and lessonRecord.delTime is null " +
            "and lessonRecord.userNum = ?1 " +
            "order by lessonRecord.updateTime desc ")
    List<Object[]> findAllWithLessonRecord(String userNum);

    @Query(value = "SELECT tb_lesson.*, tb_lessonrecord.* FROM elp.tb_lesson LEFT OUTER JOIN elp.tb_lessonrecord " +
            "ON (tb_lesson.object_id = tb_lessonrecord.lesson_num " +
            "AND tb_lessonrecord.del_time IS NULL " +
            "AND tb_lessonrecord.user_num = ?2) " +
            "WHERE tb_lesson.del_time IS NULL " +
            "AND tb_lesson.object_id = ?1 ", nativeQuery = true)
    Object[] findByIdWithLessonRecord(String objectId, String userNum);

    @Query(value = "SELECT tb_lesson.*, tb_lessonrecord.* FROM elp.tb_lesson LEFT OUTER JOIN elp.tb_lessonrecord " +
            "ON (tb_lesson.object_id = tb_lessonrecord.lesson_num " +
            "AND tb_lessonrecord.del_time IS NULL " +
            "AND tb_lessonrecord.user_num = ?2) " +
            "WHERE tb_lesson.del_time IS NULL " +
            "AND tb_lesson.course_num = ?1 " +
            "ORDER BY tb_lesson.lesson_order", nativeQuery = true)
    List<Object[]> findByCourseNumWithLessonRecord(String courseNum, String userNum);

}
