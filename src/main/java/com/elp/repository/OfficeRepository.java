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
    List<Office> findALL();

    @Query("from Office office where office.objectId = :objectId AND office.delTime is null")
    Office findOne(@Param("object") String object);

    @Query("from Office office where office.objectId = :objectId AND office.delTime is null")
    List<Office> findById(@Param("object") String object);
}
