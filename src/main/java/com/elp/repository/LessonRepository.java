package com.elp.repository;

import com.elp.model.Lesson;
import com.elp.model.LessonRecord;
import com.elp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;
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

    @Query(value = "from Lesson lesson, LessonRecord lessonRecord, Course course " +
            "where lesson.objectId = lessonRecord.lessonNum " +
            "and lesson.courseNum = course.objectId " +
            "and lesson.delTime is null " +
            "and lessonRecord.delTime is null " +
            "and lessonRecord.userNum = ?1 " +
            "order by lessonRecord.updateTime desc ")
    List<Object[]> findAllWithLessonRecord(String userNum);

    @Query(value = "SELECT tb_lesson.object_id lesid, " +
            "tb_lesson.creat_time lescretime, " +
            "tb_lesson.del_time lesdeltime, " +
            "tb_lesson.update_time lesuptime, " +
            "tb_lesson.course_num, " +
            "tb_lesson.lesson_name, " +
            "tb_lesson.lesson_info, " +
            "tb_lesson.expect_complete lesexp, " +
            "tb_lesson.lesson_type, " +
            "tb_lesson.lesson_order, " +
            "tb_lessonrecord.object_id lesrecid, " +
            "tb_lessonrecord.creat_time lesreccretime, " +
            "tb_lessonrecord.del_time lesrecdeltime, " +
            "tb_lessonrecord.update_time lesrecuptime, " +
            "tb_lessonrecord.user_num, " +
            "tb_lessonrecord.lesson_num, " +
            "tb_lessonrecord.lesson_record, " +
            "tb_course.object_id couid, " +
            "tb_course.creat_time coucretime, " +
            "tb_course.del_time coudeltime, " +
            "tb_course.update_time couuptime, " +
            "tb_course.admin_num, " +
            "tb_course.course_name, " +
            "tb_course.course_url, " +
            "tb_course.course_sum_lesson, " +
            "tb_course.expect_complete couexp, " +
            "tb_course.course_power, " +
            "tb_course.course_info, " +
            "tb_course.course_pic_url " +
            "FROM tb_lesson LEFT JOIN tb_lessonrecord " +
            "ON (tb_lesson.object_id = tb_lessonrecord.lesson_num " +
            "AND tb_lessonrecord.del_time IS NULL " +
            "AND tb_lessonrecord.user_num = ?2) " +
            "INNER JOIN tb_course " +
            "ON tb_course.object_id = tb_lesson.course_num " +
            "WHERE tb_lesson.del_time IS NULL " +
            "AND tb_lesson.object_id = ?1", nativeQuery = true)
    Object[] findByIdWithLessonRecord(String objectId, String userNum);

    @Query(value = "SELECT tb_lesson.object_id lesid, " +
            "tb_lesson.creat_time lescretime, " +
            "tb_lesson.del_time lesdeltime, " +
            "tb_lesson.update_time lesuptime, " +
            "tb_lesson.course_num, " +
            "tb_lesson.lesson_name, " +
            "tb_lesson.lesson_info, " +
            "tb_lesson.expect_complete lesexp, " +
            "tb_lesson.lesson_type, " +
            "tb_lesson.lesson_order, " +
            "tb_lessonrecord.object_id lesrecid, " +
            "tb_lessonrecord.creat_time lesreccretime, " +
            "tb_lessonrecord.del_time lesrecdeltime, " +
            "tb_lessonrecord.update_time lesrecuptime, " +
            "tb_lessonrecord.user_num, " +
            "tb_lessonrecord.lesson_num, " +
            "tb_lessonrecord.lesson_record, " +
            "tb_course.object_id couid, " +
            "tb_course.creat_time coucretime, " +
            "tb_course.del_time coudeltime, " +
            "tb_course.update_time couuptime, " +
            "tb_course.admin_num, " +
            "tb_course.course_name, " +
            "tb_course.course_url, " +
            "tb_course.course_sum_lesson, " +
            "tb_course.expect_complete couexp, " +
            "tb_course.course_power, " +
            "tb_course.course_info, " +
            "tb_course.course_pic_url " +
            "FROM tb_lesson LEFT JOIN tb_lessonrecord " +
            "ON (tb_lesson.object_id = tb_lessonrecord.lesson_num " +
            "AND tb_lessonrecord.del_time IS NULL " +
            "AND tb_lessonrecord.user_num = ?2) " +
            "INNER JOIN tb_course " +
            "ON tb_course.object_id = tb_lesson.course_num " +
            "WHERE tb_lesson.del_time IS NULL " +
            "AND tb_lesson.course_num = ?1 " +
            "ORDER BY tb_lesson.lesson_order", nativeQuery = true)
    List<Object[]> findByCourseNumWithLessonRecord(String courseNum, String userNum);

    @Modifying
    @Query(value = "update Lesson lesson " +
            "set lesson.delTime = ?2 " +
            "where lesson.courseNum = ?1")
    void deleteByCourseNum(String courseNum, Timestamp delTime);
}
