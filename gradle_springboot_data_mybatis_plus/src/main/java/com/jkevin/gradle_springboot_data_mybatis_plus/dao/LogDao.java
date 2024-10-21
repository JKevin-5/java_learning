package com.jkevin.gradle_springboot_data_mybatis_plus.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jkevin.gradle_springboot_data_mybatis_plus.entity.LogEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author JiangYukai
 * @version 1.0
 * @description: TODO
 * @date 2024/10/21 12:56
 */
@Mapper
public interface LogDao extends BaseMapper<LogEntity> {
}
