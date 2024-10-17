package com.jkevin.gradle_springboot_data_mybatis_plus;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.jkevin.gradle_springboot_data_mybatis_plus.entity.UserEntity;
import com.jkevin.gradle_springboot_data_mybatis_plus.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.event.annotation.BeforeTestClass;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
@Rollback
@SpringBootTest
class GradleProjectApplicationTests {

	@Autowired
	private UserService userService;

	@BeforeEach
	void addTest() {
		assertTrue(userService.save(new UserEntity("1","Kevin")));
	}

	@Test
	void updateTest() {
		assertEquals("Joker",userService.updateMethod().getUserName());
	}

	@Test
	@Transactional
	void multiTransTest() {

	}

	public void add() {
		userService.save(new UserEntity("2","Kevin"));
	}
}
