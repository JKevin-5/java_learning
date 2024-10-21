package com.jkevin.gradle_springboot_data_mybatis_plus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author JiangYukai
 * @version 1.0
 * @description: TODO
 * @date 2024/10/21 15:52
 */
@Service
public class TestService {

    @Autowired
    UserServiceImpl userService;

    @Autowired
    LogService logService;

    // 外层事务出错，不会影响require_new事务，因为require_new另起了一个新的事务，只会回滚insertUser方法
    // 内层事务（insertLogRequiresNew) 出错，会回滚外层整个事务
    @Transactional
    public void test1(){
        userService.insertUser();
        logService.insertLogRequiresNew();
        int i = 1/0;
    }

    // 内层是nested模式下，外层使用try-catch异常，外层不会进行回滚
    // 内层是required模式，外层使用try-catch异常，外层同样会进行回滚
    @Transactional
    public void test2(){
        userService.insertUser();
        try{
            logService.insertLogNested();
        }catch (Exception ignored){}
    }
}
