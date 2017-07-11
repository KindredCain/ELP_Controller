package com.elp.repository;

import com.elp.model.Lesson;
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

    @Query("from Lesson lesson where lesson.courseNum = ?1")
    List<Lesson> findByCourseNum(String courseNum);

    //根据用户Id和课程编号查找对应课时和课时记录
    @Query(value = "SELECT tbl, tblr "+
                    "FROM tb_lesson as tbl, tb_lessonrecord as tblr "+
                    "where tbl.course_num = ?1 and tblr.lesson_num = tbl.object_id and tblr.user_num = ?2 "+
                    "and tbl.del_time is null and tblr.del_time is null ",nativeQuery = true)
    List<Object[]> findAllByUserNumAndCourseNum(String courseNum,String userNum);
}
