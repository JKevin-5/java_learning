package com.jkevin.gradle_project_data_caffeine;

import com.jkevin.gradle_project_data_caffeine.service.CacheService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@Slf4j
@SpringBootTest
class GradleProjectApplicationTests {

	@Autowired
	private CacheService cacheService;

	@Test
	void delTest() {
		// 设置缓存
		String time1 = cacheService.putCache("del");
		// 清除缓存
		cacheService.delCache("del");
		// 重新设置缓存
		assertNotEquals(time1,cacheService.getCache("del"));
	}


	/**
	 * @author mid2098
	 * @version 1.0
	 * @description: 测试缓存时间
	 * @date 2024/10/17 10:43
	 */
	@Test
	void timeOutTest() throws InterruptedException {
		String time = cacheService.putCache("test");
		assertEquals(time,cacheService.getCache("test"));
		Thread.sleep(2000);
		log.info("获取缓存时间：{}",cacheService.getCache("test"));
		assertNotEquals(time,cacheService.getCache("test"));
	}

	/**
	 * @author mid2098
	 * @version 1.0
	 * @description: 设置固定缓存大小  超过后使用 LRU算法清理缓存
	 * @date 2024/10/17 11:03
	 */
	@Test
	void sizeTest(){
		// 共2个条目
		String time1 = cacheService.putCache("1");
		String time2 = cacheService.putCache("2");
		String time3 = cacheService.putCache("3");
		String time4 = cacheService.putCache("4");

		// 缓存1 经过多次访问已经晋升到protected cache 因此不会被淘汰
		String new1 = cacheService.getCache("1");
		log.info("time1: {},{},是否相同{}",time1,new1,time1.equals(new1));
		log.info("time1: {},{},是否相同{}",time1,cacheService.getCache("1"),time1.equals(cacheService.getCache("1")));
		String new2 = cacheService.getCache("2");
		log.info("time2: {},{},是否相同{}",time2,new2,time2.equals(new2));
		String new3 = cacheService.getCache("3");
		log.info("time3: {},{},是否相同{}",time3,new3,time3.equals(new3));
		String new4 = cacheService.getCache("4");
		log.info("time4: {},{},是否相同{}",time4,new4,time4.equals(new4));
		log.info("time4: {},{},是否相同{}",time4,cacheService.getCache("4"),time4.equals(cacheService.getCache("4")));
		log.info("time1: {},{},是否相同{}",time1,cacheService.getCache("1"),time1.equals(cacheService.getCache("1")));
	}
}
