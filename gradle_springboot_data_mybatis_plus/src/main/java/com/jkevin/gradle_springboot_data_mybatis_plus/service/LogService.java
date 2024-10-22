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

    // 默认事务传播级别，如果已经在一个事务中，就加入当前事务，否则重新开启一个新的事务
    // 内外方法异常都会进行回滚，因为内外方法在同一个事务中
    // 即使外层进行try catch也会回滚
    @Transactional(propagation= Propagation.REQUIRED)
    public void insertLogRequired(){
        logDao.insert(new LogEntity("insert","Propagation.REQUIRED"));
    }

    // 新建事务
    @Transactional(propagation= Propagation.REQUIRES_NEW)
    public void insertLogRequiresNew(){
        logDao.insert(new LogEntity("insert","Propagation.REQUIRES_NEW"));
    }

    // 加入成为子事务
    @Transactional(propagation= Propagation.NESTED)
    public void insertLogNested(){
        logDao.insert(new LogEntity("insert","Propagation.NESTED"));
        int i = 1/0;
    }

    // 支持事务，当前如果有事务，加入当前事务；如果没有事务，就以非事务的方式进行
    @Transactional(propagation= Propagation.SUPPORTS)
    public void insertLogSupports(){
        logDao.insert(new LogEntity("insert","Propagation.SUPPORTS"));
    }

    // 支持事务，外部必须有事务，不然会抛出异常
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

    // 无事务方法，调用有事务方法
    public void insertLogNoTransaction(){
        this.insertLogRequired();
        int i = 1/0;
    }

    public void noTrans(){
        logDao.insert(new LogEntity("insert","Propagation.NONE"));
    }

    // 无事务方法，调用有事务方法
    @Transactional
    public void insertLogTransaction(){
        this.noTrans();
        int i = 1/0;
    }

    // required事务，会加入到当前事务中，并不会另起一个事务
    @Transactional
    public void insertTest1(){
        this.insertLogRequired();
        int i= 1/0;
    }
}
