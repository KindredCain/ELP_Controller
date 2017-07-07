package com.elp.repository;

import com.elp.model.CourseRelationOffice;
import com.elp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by ASUS on 2017/7/3.
 */
public interface CourseRelationOfficeRepository extends JpaRepository<CourseRelationOffice,String> {

    @Query("from CourseRelationOffice courseRelationOffice where courseRelationOffice.delTime is null")
    List<CourseRelationOffice> findAll();

    @Query("from CourseRelationOffice courseRelationOffice " +
            "where courseRelationOffice.objectId = :objectId " +
            "and courseRelationOffice.delTime is null")
    CourseRelationOffice findById(@Param("objectId") String objectId);
}
