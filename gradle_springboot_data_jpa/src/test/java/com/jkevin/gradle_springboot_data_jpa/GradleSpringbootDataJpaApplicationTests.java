package com.jkevin.gradle_springboot_data_jpa;

import com.jkevin.gradle_springboot_data_jpa.entity.UserEntity;
import com.jkevin.gradle_springboot_data_jpa.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@Slf4j
//@SpringBootTest
// junit5写法
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = GradleSpringbootDataJpaApplication.class)
class GradleSpringbootDataJpaApplicationTests {

    @Autowired
    private UserRepository userRepository;

    @DisplayName("新增h2数据")
    @Test
    public void addTest() {
        // 新增数据
        UserEntity entity = userRepository.save(new UserEntity(2L,"阿牛", 11));
        log.info("新增结果:{}\n", entity);
        assertNotNull(entity);
    }

    @Test
    public void testEdit(){
        // 修改数据
        UserEntity entity = userRepository.save(new UserEntity(1L, "哈哈哈哈",22));
        log.info("按照id修改结果:{}\n", entity);
        assertNotNull(entity);
    }

    @Test
    public void testSelect(){
        // 按照id查询
        Optional<UserEntity> optional = userRepository.findById(1L);
        optional.ifPresent(x -> log.info("按照id查询结果:{}\n", x));

        // 查询所有
        log.info("查询所有");
        Iterable<UserEntity> iterable = userRepository.findAll();
        iterable.forEach(x -> log.info("item:{}", x));
    }

    @Test
    public void testDelete(){
        // 删除数据
        userRepository.deleteById(1L);

        // 删除id=1的数据以后
        System.out.println();
        log.info("删除id=1的数据以后");
        Iterable<UserEntity> iterable = userRepository.findAll();
        iterable.forEach(x -> log.info("item:{}", x));
    }
}
