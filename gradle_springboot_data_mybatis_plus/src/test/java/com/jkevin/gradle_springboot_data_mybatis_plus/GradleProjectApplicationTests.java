package com.jkevin.gradle_springboot_data_mybatis_plus;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.jkevin.gradle_springboot_data_mybatis_plus.dao.LogDao;
import com.jkevin.gradle_springboot_data_mybatis_plus.entity.LogEntity;
import com.jkevin.gradle_springboot_data_mybatis_plus.entity.UserEntity;
import com.jkevin.gradle_springboot_data_mybatis_plus.service.LogService;
import com.jkevin.gradle_springboot_data_mybatis_plus.service.TestService;
import com.jkevin.gradle_springboot_data_mybatis_plus.service.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class GradleProjectApplicationTests {

	@Autowired
	private UserServiceImpl userServiceImpl;

	@Autowired
	private TestService testService;

	@Autowired
	private LogDao logDao;

	@Test
	public void add() {
		userServiceImpl.save(new UserEntity("2","Kevin"));
	}

	// requiresNew 另起一个事务，原来的事务报错不会影响该新事务
	@Test
	public void t1() {
		try{
			testService.test1();
		}catch (Exception e){

		}
		Long count = logDao.selectCount(new LambdaQueryWrapper<LogEntity>().eq(LogEntity::getContent,"Propagation.REQUIRES_NEW"));
		assertTrue(count>=1);
	}

	// nested 加入到外层事务中，在外部事务中创建子事务
	@Test
	@Rollback
	@Transactional
	public void t2() {
		testService.test2();
		long count = userServiceImpl.count(new LambdaQueryWrapper<UserEntity>().eq(UserEntity::getUserNo,"1"));
		assertTrue(count>=1);
	}

	// supports 外部事务存在加入外部事务，外部事务中回滚则会一起进行回滚
	@Test
	public void t3(){
		try{
			testService.test3();
		}catch (Exception e){}
		Long count = logDao.selectCount(new LambdaQueryWrapper<LogEntity>().eq(LogEntity::getContent,"Propagation.SUPPORTS"));
		assertEquals(0,count);
	}

	// supports 外部不存在事务，不会进行回滚，直接无事务进行执行
	@Test
	@Rollback
	@Transactional
	public void t4(){
		try{
			testService.test4();
		}catch (Exception e){}
		Long count = logDao.selectCount(new LambdaQueryWrapper<LogEntity>().eq(LogEntity::getContent,"Propagation.SUPPORTS"));
		assertEquals(1,count);
	}

	// 外部没有事务，就会报错
	@Test
	public void t5(){
		assertThrows(Exception.class, () -> testService.test5());
	}

	// 不支持事务
	@Test
	public void t6(){
		testService.test6();
		Long count = logDao.selectCount(new LambdaQueryWrapper<LogEntity>().eq(LogEntity::getContent,"Propagation.NOT_SUPPORTED"));
		assertTrue(count>=1);
	}

	// 外部存在事务，则报错
	@Test
	public void t7(){
		assertThrows(Exception.class, () -> testService.test7());
	}

	// 场景1：无事务调用有事务的方法
	// 外层报错无法回滚，事务失效
	@Test
	public void t8(){
		Long count1 = logDao.selectCount(new LambdaQueryWrapper<LogEntity>().eq(LogEntity::getContent,"Propagation.REQUIRED"));
		assertThrows(Exception.class, () -> testService.test8());
		Long count2 = logDao.selectCount(new LambdaQueryWrapper<LogEntity>().eq(LogEntity::getContent,"Propagation.REQUIRED"));
        assertEquals(1, count2 - count1);
	}

	// 场景2：有事务调用无事务方法
	// 无事务方法会被加进外层事务中，会进行回滚
	@Test
	public void t9(){
		Long count1 = logDao.selectCount(new LambdaQueryWrapper<LogEntity>().eq(LogEntity::getContent,"NONE"));
		assertThrows(Exception.class, () -> testService.test9());
		Long count2 = logDao.selectCount(new LambdaQueryWrapper<LogEntity>().eq(LogEntity::getContent,"NONE"));
		assertEquals(0, count2 - count1);
	}

	// 场景3：有事务的方法调用有事务的方法
	// 外层事务失败，内层也会进行回滚
	@Test
	public void t10(){
		Long count1 = logDao.selectCount(new LambdaQueryWrapper<LogEntity>().eq(LogEntity::getContent,"Propagation.REQUIRED"));
		assertThrows(Exception.class, () -> testService.test10());
		Long count2 = logDao.selectCount(new LambdaQueryWrapper<LogEntity>().eq(LogEntity::getContent,"Propagation.REQUIRED"));
		assertEquals(0, count2 - count1);
	}
}
