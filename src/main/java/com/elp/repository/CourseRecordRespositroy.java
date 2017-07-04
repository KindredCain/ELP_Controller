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
public interface CourseRecordRespositroy extends JpaRepository<CourseRecord,String> {

    @Query("from CourseRecord courseRecord where courseRecord.delTime is null")
    List<CourseRecord> findAll();

    @Query("from CourseRecord courseRecord where courseRecord.objectId = :objectId")
    List<CourseRecord> findById(@Param("objectId") String objectId);
}
