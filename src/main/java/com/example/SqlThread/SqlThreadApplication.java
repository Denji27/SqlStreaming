package com.example.SqlThread;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;



@SpringBootApplication
public class SqlThreadApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(SqlThreadApplication.class, args);
//		ResidentCitizenRepo re = context.getBean(ResidentCitizenRepo.class);
//		System.out.println(re.getCountCitizen());
//		re.getAListCitizen(10, 15).stream().forEach(c -> System.out.println(c.toString()));
//		ApplicationContext context1 = SpringApplication.run(SqlThreadApplication.class, args);
//		AnnotationConfigApplicationContext ann = new AnnotationConfigApplicationContext();
//		context1.getBean("beanQuery");

//		context.getBean(OffsetThreadConsumer.class).start();
//		context.getBean(OffsetThreadProducer.class).start();
//		context.getBean("residentCitizens");

	}
}
