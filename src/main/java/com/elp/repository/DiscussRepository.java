package com.elp.repository;

import com.elp.model.Discuss;
import com.elp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by ASUS on 2017/7/3.
 */
public interface DiscussRepository extends JpaRepository<Discuss,String> {

    @Query("from Discuss discuss where discuss.delTime is null")
    List<Discuss> findAll();

    @Query("from Discuss discuss where discuss.objectId = :objectId and discuss.delTime is null")
    Discuss findById(@Param("objectId") String objectId);

    //根据课时查找对应的讨论
    @Query("from Discuss discuss, User user where discuss.lessonNum = ?1 and user.objectId = discuss.talkUserNum and discuss.delTime is null")
    List<Object[]> findByLessonNum(String LessonNum);

    //根据讨论id找到讨论、用户、课时和课程
    @Query("from Discuss discuss, User user, Lesson lesson, Course course "+
            "where discuss.objectId = ?1 and lesson.objectId = discuss.lessonNum and course.objectId = lesson.courseNum "+
            "and user.objectId = discuss.talkUserNum and discuss.delTime is null")
    List<Object[]> findByIdWithLessonAndCourse(String discussId);
}
