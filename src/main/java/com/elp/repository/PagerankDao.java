package com.elp.repository;

import com.elp.model.PagerankEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by NWJ on 2017/7/7.
 */

@Repository
public class PagerankDao {
    @Autowired
    private EntityManagerFactory emf;

    public List<PagerankEntity> findPagerank(){
        List<PagerankEntity> result = new ArrayList<PagerankEntity>();
        EntityManager em = emf.createEntityManager();
        String sql = "select a.object_id as oa,b.object_id as ob " +
                "from tb_user as a,tb_user as b " +
                "where a.office_num = b.office_num and a.object_id <> b.object_id " +
                "union all " +
                "select tb_user.object_id as oa,tb_course.object_id as ob " +
                "from tb_user,tb_course,tb_course_office " +
                "where tb_user.office_num = tb_course_office.office_num and tb_course.object_id = tb_course_office.course_num " +
                "union all " +
                "select tb_course.object_id as oa,tb_user.object_id as ob " +
                "from tb_user,tb_course,tb_course_office " +
                "where tb_user.office_num = tb_course_office.office_num and tb_course.object_id = tb_course_office.course_num " +
                "union all " +
                "select ca.course_num as oa,cb.course_num as ob " +
                "from tb_course_office as ca,tb_course_office as cb " +
                "where ca.office_num = cb.office_num and ca.object_id <> cb.object_id ";
        Query query =  em.createNativeQuery(sql);
        List<Object[]> list = query.getResultList();
        for(int i = 0; i < list.size(); i++){
            Object[] item = list.get(i);
            result.add(new PagerankEntity((String) item[0], (String) item[1]));
        }
        return result;
    }
}
