package com.elp.repository;

import com.elp.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by ASUS on 2017/7/3.
 */
public interface UserRespositroy extends JpaRepository<User, String> {

    @Query("from User user where user.delTime is null")
    List<User> findAll();

    @Query("from User user where user.objectId = :objectId and user.delTime is null")
    User findOne(@Param("objectId") String objectId);

    @Query("from User user where user.objectId = :objectId and user.delTime is null")
    List<User> findById(@Param("objectId") String objectId);

    @Query("from User user where user.userType = :userType and user.delTime is null")
    List<User> findByUserType(@Param("userType") String userType);

    @Query("from User user where user.officeNum = :officeNum and user.delTime is null")
    List<User> findByOfficeNum(@Param("officeNum") String officeNum);

    @Query("from User user where user.logId = :logId and user.delTime is null")
    List<User> findByLogId(@Param("logId") String logId);

    @Query("from User user where user.delTime is null")
    Page<User> findPage(Pageable p);

}
