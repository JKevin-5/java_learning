package com.jkevin.gradle_springboot_data_mybatis_plus.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jkevin.gradle_springboot_data_mybatis_plus.dao.UserDao;
import com.jkevin.gradle_springboot_data_mybatis_plus.entity.UserEntity;
import org.springframework.stereotype.Service;

/**
 * @author mid2098
 * @version 1.0
 * @description: TODO
 * @date 2024/10/17 14:46
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserDao, UserEntity> implements UserService {


    @Override
    public UserEntity updateMethod() {
        UserEntity user = this.getOne(new LambdaQueryWrapper<UserEntity>().eq(UserEntity::getUserNo,"1"));
        user.setUserName("Joker");
        this.updateById(user);
        return this.getOne(new LambdaQueryWrapper<UserEntity>().eq(UserEntity::getUserNo,"1"));
    }
}
