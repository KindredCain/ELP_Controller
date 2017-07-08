package com.elp.repository;

import com.elp.model.Course;
import com.elp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
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
    @Query(value = "SELECT tbc "+
            "FROM tb_course, tb_user, tb_course_office "+
            "where tb_user.object_id = ?1 and tb_user.office_num = tb_course_office.office_num and tb_course_office.course_num = tb_course.object_id "+
            "and tb_course.course_name like ?2 ",nativeQuery = true)
    Course findByCourseNameLike(String userId,String courseName);


    //根据用户id查找用户学过的课程信息并根据最后观看时间倒序查看
    @Query(value = "SELECT tbc "+
                    "FROM tb_course as tbc, tb_courserecord as tbco    "+
                    "where tbco.user_num = ?1 and tbco.course_num = tbc.object_id "+
                    "order by tbco.last_view_time ",nativeQuery = true)
    List<Course> findByObjectIdOrderByLastViewTime(String userId);

    //根据用户id和课程id筛选推荐的课程  不能用表.*的方式提取全部 nativeQuery = true表明为原生sql语句
    @Query(value = "SELECT tbc "+
                    "FROM tb_course as tbc, tb_course_office as tbco, tb_user as tbu "+
                    "where tbu.object_id = ?1 and tbu.office_num = tbco.office_num and tbco.course_num = ?2 and tbc.object_id = tbco.course_num ",nativeQuery = true)
    Course findByCourseIdAndObjectId(String userId,String courseId);

    //根据职位编号选出课程
    @Query(value = "SELECT tbc "+
                    "FROM tb_course as tbc, tb_course_office as tbco "+
                    "where tbco.office_num = ?1 and tbc.object_id = tbco.course_num ",nativeQuery = true)
    List<Course> findByOfficeId(String officeId);

    //根据id查找出方向对应的课程
    @Query(value = "SELECT tbc "+
            "FROM tb_course as tbc, tb_course_office as tbco "+
            "where tbco.object_id = ?1 and tbc.object_id = tbco.course_num ",nativeQuery = true)
    List<Course> findAllBySubjectId(String subjectId);
}
