package com.elp.repository;

import com.elp.model.Department;
import com.elp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by ASUS on 2017/7/3.
 */
public interface DepartmentRepository extends JpaRepository<Department,String> {

    @Query("from Department department where department.delTime is null")
    List<Department> findAll();

    @Query("from Department department where department.objectId = :objectId and department.delTime is null")
    Department findById(@Param("objectId") String objectId);
}
