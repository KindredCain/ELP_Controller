package com.elp.repository;

import com.elp.model.Course;
import com.elp.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by ASUS on 2017/7/3.
 */
public interface CourseRepository extends JpaRepository<Course,String> {

    @Query("from Course course where course.delTime is null")
    List<Course> findAll();

    @Query("from Course course where course.objectId = :objectId and course.delTime is null")
    Course findById(@Param("objectId") String objectId);

    //根据课程名模糊查询课程
    @Query(value = "SELECT tb_course.* "+
            "FROM tb_course, tb_user "+
            "where tb_user.object_id = ?1 and tb_course.course_power <= tb_user.user_power "+
            "and tb_course.course_name like ?2  and tb_course.del_time is null",nativeQuery = true)
    List<Course> findByCourseNameLike(String userId,String courseName);
    //根据用户id查找用户学过的课程信息并根据最后观看时间倒序查看 已根据用户权限显示
    @Query(value =  "FROM Course course, Lesson lesson, LessonRecord lessonRecord  "+
                    "where lessonRecord.userNum = ?1 and lessonRecord.lessonNum = lesson.objectId and lesson.courseNum = course.objectId "+
                    "and lessonRecord.updateTime is null "+
                    "order by lessonRecord.updateTime ")
    List<Object[]> findByUserNumOrderByUpdateTime(String userId);
    //根据课程和用户id 查找用户学过的课程和课程记录
    @Query(value = "from Course course, CourseRecord courseRecord "+
            "where courseRecord.userNum = ?1 and courseRecord.courseNum = ?2 and courseRecord.courseNum = course.objectId and  courseRecord.delTime is null")
    List<Object[]> findByObjectIdAndCourseNum(String userId,String courseId);
    //根据用户id和课程id筛选推荐的课程  不能用表.*的方式提取全部 nativeQuery = true表明为原生sql语句
    @Query(value = "SELECT tbc.* "+
                    "FROM tb_course as tbc, tb_user as tbu "+
                    "where tbu.object_id = ?1 and tbc.object_id = ?2 "+
                    "and tbc.course_power <= tbu.user_power and tbc.del_time is null ",nativeQuery = true)
    Course findByCourseIdAndObjectId(String userId,String courseNum);
    //根据职位编号选出课程
    @Query(value = "SELECT tbc "+
                    "FROM tb_course as tbc, tb_course_office as tbco "+
                    "where tbco.office_num = ?1 and tbc.object_id = tbco.course_num and  tbc.del_time is null ",nativeQuery = true)
    List<Course> findByOfficeId(String officeId);
    //根据用户id选出课程
    @Query("FROM Course course, CourseRelationOffice courseRelationOffice, User user "+
            "where user.objectId = ?1 and course.objectId = courseRelationOffice.courseNum "+
            "and course.coursePower <= user.userPower and  course.delTime is null ")
    List<Object[]> findAllByUserId(String userId);
    //根据方向id查找出方向对应的课程
    @Query(value = "SELECT tbc "+
            "FROM tb_course as tbc, tb_course_office as tbco "+
            "where tbco.object_id = ?1 and tbc.object_id = tbco.course_num and  tbc.del_time is null ",nativeQuery = true)
    List<Course> findAllBySubjectId(String subjectId);
    //查找学的人最多的课程
    @Query(value = "SELECT tbc.* "+
            "FROM tb_course as tbc, tb_courserecord as tbcr "+
            "where tbc.object_id = tbcr.course_num and tbc.del_time is null and "+
            "group by tbc.object_id "+
            "order by count(tbcr.object_id) DESC limit 0,10",nativeQuery = true)
    public List<Course> findTopByCountCourseRecord();
    //最新的课程
    @Query("from Course course where course.delTime is null order by course.creatTime DESC")
    public List<Course> findAllOrderByCreatTime();
    //根据用户筛选最新的课程
    @Query(value = "SELECT tbc "+
                    "FROM tb_course as tbc, tb_user as tbu "+
                    "where tbc.course_power <= tbu.user_power and tbu.object_id = ?1 "+
                    "and  tbc.del_time is null ",nativeQuery = true)
    public List<Course> findAllByUserIdOrderByCreatTime(String userId);
    //
    /*
     @Query(value = "SELECT tbc.* "+
            "FROM tb_course as tbc, tb_course_office as tbco, tb_user as tbu "+
            "where tbco.subject_name = ?1 and tbc.object_id = tbco.course_num "+
            "and tbu.object_id = userand tbc.del_time is null ",nativeQuery = true)
     */
    @Query("from Course course, CourseRelationOffice courseRelationOffice, User user "+
            "where courseRelationOffice.subjectName = ?1 and course.objectId = courseRelationOffice.courseNum "+
            "and user.objectId = ?2 and course.coursePower <= user.userPower and course.delTime is null ")
    public List<Object[]> findAllBySubjectName(String subjctName,String userId);

    //课时数+1
    @Modifying
    @Query(value = "update Course course " +
            "set course.courseSumLesson = course.courseSumLesson + 1 " +
            "where course.objectId = ?1")
    public void addCourseSumLesson (String objectId);
    //课时数-1
    @Modifying
    @Query(value = "update Course course " +
            "set course.courseSumLesson = course.courseSumLesson - 1 " +
            "where course.objectId = ?1")
    public void subCourseSumLesson (String objectId);
}
