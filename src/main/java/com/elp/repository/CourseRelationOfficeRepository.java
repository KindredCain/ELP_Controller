package com.elp.repository;

import com.elp.model.Course;
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

    @Query("from CourseRelationOffice courseRelationOffice where courseRelationOffice.delTime is null group by courseRelationOffice.subjectName")
    List<CourseRelationOffice> findAllGroupBySubjectName();

    @Query("from CourseRelationOffice courseRelationOffice " +
            "where courseRelationOffice.objectId = ?1 " +
            "and courseRelationOffice.delTime is null")
    CourseRelationOffice findById(String objectId);

    //根据用户id查找对应的方向
    @Query( "FROM CourseRelationOffice courseRelationOffice, User user "+
            "where user.objectId = ?1 and courseRelationOffice.officeNum = user.officeNum  and courseRelationOffice.delTime is NULL "+
            "group by courseRelationOffice.subjectName ")
    List<Object[]> findByUserIdGroupBySubjectName(String userId);




}
