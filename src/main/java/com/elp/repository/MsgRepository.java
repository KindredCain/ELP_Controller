package com.elp.repository;

import com.elp.model.Msg;
import com.elp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by ASUS on 2017/7/3.
 */
public interface MsgRepository extends JpaRepository<Msg,String> {

    @Query("from Msg msg where msg.delTime is null")
    List<Msg> findAll();

    @Query("from Msg msg where msg.objectId = :objectId and msg.delTime is null")
    Msg findById(@Param("objectId") String objectId);

    //根据用户id查找用户的消息
    @Query("from Msg msg where msg.recUser = ?1 and msg.delTime is null")
    List<Msg> findByRecUser(String userNum);

    @Modifying
    @Query(value = "update Msg msg " +
            "set msg.delTime = ?2 " +
            "where msg.recUser = ?1")
    void deleteByRecUser(String recUser, Timestamp delTime);
}
