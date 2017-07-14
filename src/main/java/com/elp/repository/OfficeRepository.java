package com.elp.repository;

import com.elp.model.Office;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by ASUS on 2017/7/3.
 */
public interface OfficeRepository extends JpaRepository<Office,String> {

    @Query("from Office office where office.delTime is null")
    List<Office> findAll();

    @Query("from Office office where office.objectId = :objectId and office.delTime is null")
    Office findById(@Param("object") String object);

    @Query("from Office office, Department department where office.departmentNum = department.objectId and office.delTime is null ")
    List<Object[]>  findAllWithDepartment();
}
