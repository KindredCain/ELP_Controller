package com.elp.repository;

import com.elp.model.Course;
import com.elp.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
            "and tb_course.course_name like ?2  and tbc.delTime is null",nativeQuery = true)
    List<Course> findByCourseNameLike(String userId,String courseName);


    //根据用户id查找用户学过的课程信息并根据最后观看时间倒序查看
    @Query(value = "SELECT tbc, tbl, tblr "+
                    "FROM tb_course as tbc, tb_lesson as tbl, tb_lessonrecord tblr "+
                    "where tblr.user_num = ?1 and tblr.lesson_num = tbl.object_id and tbl.course_num = tbc.object_id "+
                    " and tblr.update_time is null"+
                    "order by tblr.last_view_time ",nativeQuery = true)
    List<Object[]> findByObjectIdOrderByLastViewTime(String userId);
    //根据课程和用户id 查找用户学过的课程和课程记录
    @Query(value = "SELECT tbc,tbco"+
            "FROM tb_course as tbc, tb_courserecord as tbco    "+
            "where tbco.user_num = ?1 and tbco.course_num = ?2 and tbco.course_num = tbc.object_id and  tbco.del_time is null",nativeQuery = true)
    Object[] findByObjectIdAndCourseNum(String userId,String courseId);
    //根据用户id和课程id筛选推荐的课程  不能用表.*的方式提取全部 nativeQuery = true表明为原生sql语句
    @Query(value = "SELECT tbc "+
                    "FROM tb_course as tbc, tb_course_office as tbco, tb_user as tbu "+
                    "where tbu.object_id = ?1 and tbu.office_num = tbco.office_num and tbco.course_num = ?2 and tbc.object_id = tbco.course_num and tbc.del_time is null",nativeQuery = true)
    Course findByCourseIdAndObjectId(String userId,String courseNum);
    //根据职位编号选出课程
    @Query(value = "SELECT tbc "+
                    "FROM tb_course as tbc, tb_course_office as tbco "+
                    "where tbco.office_num = ?1 and tbc.object_id = tbco.course_num and  tbc.del_time is null ",nativeQuery = true)
    List<Course> findByOfficeId(String officeId);
    //根据用户id选出课程
    @Query(value = "SELECT tbc, tbco"+
            "FROM tb_course as tbc, tb_course_office as tbco, tb_user as tbu "+
            "where tbu.object_id = ?1 and tbco.office_num = tbu.office_num and tbc.object_id = tbco.course_num and  tbc.del_time is null",nativeQuery = true)
    List<Object[]> findAllByUserId(String userId);
    //根据方向id查找出方向对应的课程
    @Query(value = "SELECT tbc "+
            "FROM tb_course as tbc, tb_course_office as tbco "+
            "where tbco.object_id = ?1 and tbc.object_id = tbco.course_num and  tbc.del_time is null ",nativeQuery = true)
    List<Course> findAllBySubjectId(String subjectId);
    //查找学的人最多的课程
    @Query(value = "SELECT tbc "+
            "FROM tb_course as tbc, tb_courserecord as tbcr "+
            "where tbc.object_id = tbcr.course_num and tbc.del_time is null and "+
            "group by tbc.object_id "+
            "order by count(tbcr.object_id) DESC limit 0,10",nativeQuery = true)
    public List<Course> findTopByCountCourseRecord();
    //最新的课程
    @Query("from Course course where course.delTime is null order by course.creatTime DESC")
    public List<Course> findAllOrderByCreatTime();
}
