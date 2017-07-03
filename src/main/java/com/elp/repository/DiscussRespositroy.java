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
public interface DiscussRespositroy extends JpaRepository<Discuss,String> {

    @Query("from Discuss discuss where discuss.delTime is null")
    List<Discuss> findAll();

    @Query("from Discuss discuss where discuss.objectId = :objectId")
    List<Discuss> findById(@Param("objectId") String objectId);
}