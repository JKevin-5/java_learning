package com.jkevin.gradle_springboot_aop.controller;

import com.jkevin.gradle_springboot_aop.aop.MyAnnotation;
import com.jkevin.gradle_springboot_aop.dto.HelloDto;
import com.jkevin.gradle_springboot_aop.service.MyService;
import com.jkevin.gradle_springboot_aop.service.MyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author mid2098
 * @version 1.0
 * @description: TODO
 * @date 2024/10/14 13:21
 */
@RestController
public class IndexController {

    @Autowired
    private MyService myService;

    @Autowired
    private MyServiceImpl myServiceImpl;

    @GetMapping("/")
    public String index(){
        return myService.hello();
    }

    @GetMapping("/hello")
    public String hello(){
        return myServiceImpl.hello2(new HelloDto("hello"));
    }

    @MyAnnotation
    @GetMapping("/my")
    public String my(){
        return "my";
    }
}
