package com.elp.repository;

import com.elp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by ASUS on 2017/7/3.
 */
public interface UserRepository extends JpaRepository<User,String> {

    @Query("from User user where user.delTime is null")
    List<User> findAll();

    @Query("from User user where user.objectId = :objectId and user.delTime is null")
    User findById(@Param("objectId") String objectId);

    @Query("from User user where user.userType = :userType and user.delTime is null")
    List<User> findByUserType(@Param("userType") String userType);

    @Query("from User user where user.officeNum = :officeNum and user.delTime is null")
    List<User> findByOfficeNum(@Param("officeNum") String officeNum);

    //根据用户登录id查找用户
    @Query(value = "from User user where user.logId = ?1")
    User findByLogId(String logId);

}
