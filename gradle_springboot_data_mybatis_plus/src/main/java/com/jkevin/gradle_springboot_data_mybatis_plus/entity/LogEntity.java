package com.jkevin.gradle_springboot_data_mybatis_plus.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author mid2098
 * @version 1.0
 * @description: TODO
 * @date 2024/10/17 14:32
 */
@Data
@TableName("tb_log")
public class LogEntity {

    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    private String type;

    private String content;

    public LogEntity(String type, String content) {
        this.type = type;
        this.content = content;
    }
}
