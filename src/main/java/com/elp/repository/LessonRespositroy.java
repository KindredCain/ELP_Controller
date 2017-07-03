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
public interface LessonRespositroy extends JpaRepository<Lesson,String> {

    @Query("from Lesson lesson where lesson.delTime is null")
    List<Lesson> findAll();

    @Query("from Lesson lesson where lesson.objectId = :objectId")
    List<Lesson> findById(@Param("objectId") String objectId);


}
