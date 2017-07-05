package com.elp.repository;

import com.elp.model.RelationCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by ASUS on 2017/7/4.
 */
public interface RelationCourseRespositroy extends JpaRepository<RelationCourse,String>{
    @Query("from RelationCourse relationCourse where relationCourse.delTime is null")
    List<RelationCourse> findAll();
    @Query("from RelationCourse relationCourse where relationCourse.objectId = :objectId")
    List<RelationCourse> findById(@Param("objectId") String objectId);
}
