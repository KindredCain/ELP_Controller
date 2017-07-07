package com.elp.service;

import com.elp.model.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by NWJ on 2017/7/6.
 */

@Service
public class BaseService {
    public void delete (JpaRepository repository, BaseEntity baseEntity){
        Timestamp time = new Timestamp(new Date().getTime());
        baseEntity.setDelTime(time);
        repository.save(baseEntity);
    }
}
