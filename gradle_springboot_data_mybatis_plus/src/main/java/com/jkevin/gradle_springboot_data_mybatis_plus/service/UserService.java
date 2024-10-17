package com.jkevin.gradle_springboot_data_mybatis_plus.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jkevin.gradle_springboot_data_mybatis_plus.entity.UserEntity;

/**
 * @author mid2098
 * @version 1.0
 * @description: TODO
 * @date 2024/10/17 14:36
 */
public interface UserService extends IService<UserEntity> {

    UserEntity updateMethod();
}
