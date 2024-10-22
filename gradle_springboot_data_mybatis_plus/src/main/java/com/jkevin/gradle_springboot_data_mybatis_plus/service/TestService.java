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

    // 外层有事务，则加入事务
    @Transactional
    public void test3(){
        logService.insertLogSupports();
        int i = 1/0;
    }

    // 外层没有事务，则不创建事务
    public void test4(){
        logService.insertLogSupports();
        int i = 1/0;
    }

    // 外层必须有事务，否则报错
    public void test5(){
        logService.insertLogMandatory();
    }

    // 不支持事务
    @Transactional
    public void test6(){
        logService.insertLogNotSupported();
    }

    // 不支持事务，在事务中将进行报错
    @Transactional
    public void test7(){
        logService.insertLogNever();
    }

    public void test8(){
        logService.insertLogNoTransaction();
    }

    public void test9(){
        logService.insertLogTransaction();
    }
    
    public void test10(){
        logService.insertTest1();
    }
}
