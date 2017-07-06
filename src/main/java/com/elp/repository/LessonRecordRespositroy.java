package com.elp.repository;

import com.elp.model.LessonRecord;
import com.elp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by ASUS on 2017/7/3.
 */
public interface LessonRecordRespositroy extends JpaRepository<LessonRecord,String> {

    @Query("from LessonRecord lessonRecord where lessonRecord.delTime is null")
    List<LessonRecord> findAll();

    @Query("from LessonRecord lessonRecord where lessonRecord.objectId = :objectId AND lessonRecord.delTime is null")
    LessonRecord findOne(@Param("objectId") String objectId);

    @Query("from LessonRecord lessonRecord where lessonRecord.objectId = :objectId AND lessonRecord.delTime is null")
    List<LessonRecord> findById(@Param("objectId") String objectId);

    @Query("from LessonRecord lessonRecord where lessonRecord.lessonNum = :lessonNum and lessonRecord.userNum = :userNum AND lessonRecord.delTime is null")
    List<LessonRecord> findByLessonNumAndUserNum(@Param("lessonNum") String lessonNum,@Param("userNum") String userNum);
}
