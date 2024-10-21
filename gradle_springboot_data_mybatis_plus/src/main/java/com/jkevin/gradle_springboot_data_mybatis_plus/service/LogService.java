package com.jkevin.gradle_springboot_data_mybatis_plus.service;

import com.jkevin.gradle_springboot_data_mybatis_plus.dao.LogDao;
import com.jkevin.gradle_springboot_data_mybatis_plus.entity.LogEntity;
import com.jkevin.gradle_springboot_data_mybatis_plus.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author JiangYukai
 * @version 1.0
 * @description: TODO
 * @date 2024/10/21 16:11
 */
@Service
public class LogService {

    @Autowired
    LogDao logDao;

    // 默认事务传播级别
    @Transactional(propagation= Propagation.REQUIRED)
    public void insertLogRequired(){
        logDao.insert(new LogEntity("insert","Propagation.REQUIRED"));
    }

    // 新建事务
    @Transactional(propagation= Propagation.REQUIRES_NEW)
    public void insertLogRequiresNew(){
        logDao.insert(new LogEntity("insert","Propagation.REQUIRES_NEW"));
    }

    // 新建事务
    @Transactional(propagation= Propagation.NESTED)
    public void insertLogNested(){
        logDao.insert(new LogEntity("insert","Propagation.NESTED"));
        int i = 1/0;
    }

    // 新建事务
    @Transactional(propagation= Propagation.SUPPORTS)
    public void insertLogSupports(){
        logDao.insert(new LogEntity("insert","Propagation.SUPPORTS"));
    }

    // 新建事务
    @Transactional(propagation= Propagation.MANDATORY)
    public void insertLogMandatory(){
        logDao.insert(new LogEntity("insert","Propagation.MANDATORY"));
    }

    // 不支持事务
    @Transactional(propagation= Propagation.NOT_SUPPORTED)
    public void insertLogNotSupported(){
        logDao.insert(new LogEntity("insert","Propagation.NOT_SUPPORTED"));
    }

    // 不支持事务，在事务中抛出异常
    @Transactional(propagation= Propagation.NEVER)
    public void insertLogNever(){
        logDao.insert(new LogEntity("insert","Propagation.NEVER"));
    }
}
