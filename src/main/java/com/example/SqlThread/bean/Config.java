package com.example.SqlThread.bean;

import com.example.SqlThread.entity.ResidentCitizen;
import com.example.SqlThread.repository.ResidentCitizenRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.stream.Stream;

@Configuration
public class Config {
    @Autowired
    ResidentCitizenRepo repo;


//    @Bean
//    public Stream<ResidentCitizen> residentCitizenStream(){
//        return repo.streamAllCitizen();
//    }

    @Bean
    public BlockingQueue<ResidentCitizen> residentCitizens(){
        BlockingQueue<ResidentCitizen> queue = new ArrayBlockingQueue<>(1000);
        return queue;
    }

//    @Bean
//    @PostConstruct
//    public void beanQuery(){
//        System.out.println(re.getCountCitizen());
//        re.getAListCitizen(10, 15).stream().forEach(c -> System.out.println(c.toString()));
//    }
}
