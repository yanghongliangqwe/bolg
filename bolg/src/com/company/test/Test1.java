package com.company.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.company.service.IAreaService;
import com.company.service.IBlogKindService;

public class Test1 {
	@Test
	public void test() {
		ApplicationContext context = new ClassPathXmlApplicationContext("spring/spring.xml");
		IAreaService areaService = context.getBean(IAreaService.class);
	
		System.out.println(areaService);
	}
}
