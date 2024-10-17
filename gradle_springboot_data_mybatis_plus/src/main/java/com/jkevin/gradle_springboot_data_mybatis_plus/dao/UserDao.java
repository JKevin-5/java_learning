package com.jkevin.gradle_springboot_data_mybatis_plus.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jkevin.gradle_springboot_data_mybatis_plus.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author mid2098
 * @version 1.0
 * @description: TODO
 * @date 2024/10/17 14:31
 */
@Mapper
public interface UserDao extends BaseMapper<UserEntity> {

}
