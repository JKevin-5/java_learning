package com.jkevin.gradle_springboot_data_mybatis_plus.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author mid2098
 * @version 1.0
 * @description: TODO
 * @date 2024/10/17 14:32
 */
@Data
@TableName("tb_user")
public class UserEntity {

    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    private String userNo;

    private String userName;

    public UserEntity(String userNo,String userName) {
        this.userNo = userNo;
        this.userName = userName;
    }
}
