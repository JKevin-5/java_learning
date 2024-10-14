package com.jkevin.gradle_springboot_aop.service;

import com.jkevin.gradle_springboot_aop.dto.HelloDto;
import org.springframework.stereotype.Service;

/**
 * @author mid2098
 * @version 1.0
 * @description: TODO
 * @date 2024/10/14 14:05
 */
@Service
public class MyServiceImpl implements MyService{

    @Override
    public String hello() {
        return "hello";
    }

    public String hello2(HelloDto dto) {
        return dto.toString();
    }
}
