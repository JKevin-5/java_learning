package com.jkevin.gradle_springboot_data_redis;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@Slf4j
//@SpringBootTest
// junit5写法
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = GradleSpringbootDataRedisApplication.class)
class GradleSpringbootDataRedisApplicationTests {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @DisplayName("新增数据")
    @Test
    public void addTest() {
        redisTemplate.opsForValue().set("name", "Kevin");

        assert "Kevin".equals(redisTemplate.opsForValue().get("name"));
    }

    @DisplayName("获取数据")
    @Test
    public void getTest() {
        assert "Kevin".equals(redisTemplate.opsForValue().get("name"));
    }

    @DisplayName("删除数据")
    @Test
    public void deleteTest() {
        redisTemplate.delete("name");
        assert null == redisTemplate.opsForValue().get("name");
    }
}
