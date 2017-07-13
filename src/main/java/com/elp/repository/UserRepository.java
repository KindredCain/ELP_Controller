package com.elp.repository;

import com.elp.model.ShowUser;
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

    @Query("from User user, Office office where user.objectId = :objectId and office.objectId = user.officeNum and user.delTime is null")
    List<Object[]> findByIdWithOffice(@Param("objectId") String objectId);

    @Query("from User user where user.userType = :userType and user.delTime is null")
    List<User> findByUserType(@Param("userType") String userType);

    @Query("from User user where user.officeNum = :officeNum and user.delTime is null")
    List<User> findByOfficeNum(@Param("officeNum") String officeNum);

    //根据用户登录id查询
    @Query(value = "from User user where user.logId = ?1 and user.delTime is null")
    User findByLogId(String logId);

    //登录查询
    User findByLogIdAndPwdAndDelTimeIsNull(String logId, String pwd);

    //最多课时用户查询
    @Query(value = "SELECT new com.elp.model.ShowUser(tb_user.objectId, " +
            "tb_user.logId, " +
            "tb_user.userName, " +
            "tb_user.userPicUrl, " +
            "count(tb_lessonrecord.objectId)) " +
            "FROM User tb_user, LessonRecord tb_lessonrecord " +
            "WHERE tb_user.objectId = tb_lessonrecord.userNum " +
            "AND tb_user.delTime IS NULL " +
            "AND tb_lessonrecord.delTime IS NULL " +
            "GROUP BY tb_user.objectId " +
            "ORDER BY count(tb_lessonrecord.objectId) DESC")
    List<ShowUser> findMax();

    //他人查询用户
    @Query(value = "SELECT new com.elp.model.ShowUser(tb_user.objectId, " +
            "tb_user.logId, " +
            "tb_user.userName, " +
            "tb_user.userPicUrl) " +
            "FROM User tb_user " +
            "WHERE tb_user.logId = ?1 " +
            "AND tb_user.delTime IS NULL ")
    ShowUser findByLogIdFromOther(String logId);
}
