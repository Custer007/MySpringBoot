package com.mr;

import com.mr.model.Apple;
import com.mr.model.UrUser;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.awt.*;
import java.lang.reflect.Constructor;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootTest
class MySpringBootApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	/**
	 * stream筛选与切片
	 */
	void testFunc(){
		UrUser urUser1 = new UrUser(1,"郭富城","1","1");
		UrUser urUser2 = new UrUser(3,"刘德华","3","3");
		UrUser urUser3 = new UrUser(5,"张学友","5","5");
		UrUser urUser4 = new UrUser(5,"张学友","5","5");
		UrUser urUser5 = new UrUser(5,"张学友","5","5");

		System.out.print("相等吗");
		System.out.println( urUser4.hashCode()== urUser5.hashCode());//相等

		List<UrUser> userList = Arrays.asList(urUser1, urUser2, urUser3);
		Stream<UrUser> stream = userList.stream();
		// filter(Predicate e) -接受Lambda,从流中排除某些元素
		System.out.println("==========filter==============");
		stream.filter(e -> e.getId() > 2).forEach(System.out::println);

		// limit 截断流，使其元素不超过限定数量
		System.out.println("==========limit==============");
		userList.stream().limit(2).forEach(System.out::println);
		// skip 跳过元素，返回去掉前n个元素的流
		System.out.println("==========skip==============");
		userList.stream().skip(2).forEach(System.out::println);
		//distinct 筛选 和数据库distinct类似
		System.out.println("==========distinct==============");
		userList.stream().distinct().forEach(System.out::println);


		List<String> list = Arrays.asList("apple", "banana", "pear", "orange");
		list.stream().map(e -> e.toUpperCase()).forEach(System.out::println);

		Stream<UrUser> limit = userList.stream().limit(2);
		List<UrUser> collect = limit.collect(Collectors.toList());

        try {
			Class<?> clazz = Class.forName("com.mr.model.Apple");

			Constructor constructor = clazz.getConstructor();
			Apple apple = (Apple)constructor.newInstance();
			apple.setName("999999999@qq.com");
			System.out.println("获取到的Email=" + apple.getName());
		} catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}
