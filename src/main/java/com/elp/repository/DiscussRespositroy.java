package com.elp.repository;

import com.elp.model.Discuss;
import com.elp.model.Relation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

/**
 * Created by ASUS on 2017/7/3.
 */
public interface DiscussRespositroy extends JpaRepository<Discuss, String> {

    @Query("from Discuss discuss where discuss.delTime is null")
    List<Discuss> findAll();

    @Query("from Discuss discuss where discuss.objectId = :objectId AND discuss.delTime is null")
    Discuss findOne(@Param("objectId") String objectId);

    @Query("from Discuss discuss where discuss.objectId = :objectId AND discuss.delTime is null")
    List<Discuss> findById(@Param("objectId") String objectId);

    @Query("from Discuss discuss where discuss.lessonNum = :lessonNum AND discuss.delTime is null")
    List<Discuss> findByLessonNum(@Param("lessonNum") String lessonNum);
    //test
    @Query("from Discuss discuss,User user where user.objectId = discuss.talkUserNum")
    List<Object[]> findTest2();

    @Query(value = " select a.*, b.* from tb_discuss a,tb_user b where a.talk_user_num = b.object_id ", nativeQuery = true)
    List<Object[]> findTest();

    @Query(value = " select * from tb_discuss ", nativeQuery = true)
    List<Discuss> findTestSql();

    @Query(value = " select count(*) from tb_discuss ", nativeQuery = true)
    List<BigInteger> findNum();


}
