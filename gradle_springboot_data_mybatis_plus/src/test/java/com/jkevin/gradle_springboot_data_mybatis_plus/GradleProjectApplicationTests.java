package com.jkevin.gradle_springboot_data_mybatis_plus;

import com.jkevin.gradle_springboot_data_mybatis_plus.entity.UserEntity;
import com.jkevin.gradle_springboot_data_mybatis_plus.service.TestService;
import com.jkevin.gradle_springboot_data_mybatis_plus.service.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
@SpringBootTest
class GradleProjectApplicationTests {

	@Autowired
	private UserServiceImpl userServiceImpl;

	@Autowired
	private TestService testService;

//	@BeforeEach
//	void addTest() {
//		assertTrue(userService.save(new UserEntity("1","Kevin")));
//	}

	@Test
	void updateTest() {
		assertEquals("Joker",userServiceImpl.updateMethod().getUserName());
	}

	@Test
	public void add() {
		userServiceImpl.save(new UserEntity("2","Kevin"));
	}

	// requiresNew 另起一个事务，原来的事务报错不会影响该新事务
	@Test
	public void t1() {
		testService.test1();
	}

	// nested 加入到外层事务中，在外部事务中创建子事务
	@Test
	public void t2() {
		testService.test2();
	}


}
