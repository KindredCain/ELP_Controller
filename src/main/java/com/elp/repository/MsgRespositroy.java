package com.elp.repository;

import com.elp.model.Msg;
import com.elp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by ASUS on 2017/7/3.
 */
public interface MsgRespositroy extends JpaRepository<Msg,String> {

    @Query("from Msg msg where msg.delTime is null")
    List<Msg> findAll();

    @Query("from Msg msg where msg.objectId = :objectId AND msg.delTime is null")
    Msg findOne(@Param("objectId") String objectId);

    @Query("from Msg msg where msg.objectId = :objectId AND msg.delTime is null")
    List<Msg> findById(@Param("objectId") String objectId);

    @Query("from Msg msg where msg.recUser = :userId AND msg.delTime is null")
    List<Msg> findByRecUser(@Param("userId") String userId);
}
