package com.example.SqlThread.controller;

import com.example.SqlThread.entity.ResidentCitizen;
import com.example.SqlThread.repository.ResidentCitizenRepo;
import com.example.SqlThread.thread.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.concurrent.BlockingQueue;
import java.util.stream.Stream;

import static com.example.SqlThread.util.LambdaExceptionUtil.rethrowConsumer;

@RestController
@RequestMapping(value = "")
public class Controller {
    @Autowired
    private BlockingQueue<ResidentCitizen> queue;

    @Autowired
    private ResidentCitizenRepo repo;


    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    private OffsetThreadProducer producer;

    @Autowired
    private OffsetThreadProducer1 producer1;

    @Autowired
    private OffsetThreadProducer2 producer2;

    @Autowired
    private OffsetThreadProducer3 producer3;

    @Autowired
    private OffsetThreadConsumer consumer;

    @Autowired
    private OffsetThreadConsumer1 consumer1;

    @Autowired
    private OffsetThreadConsumer2 consumer2;

    @Autowired
    private OffsetThreadConsumer3 consumer3;

    @Autowired
    private OffsetThreadConsumer4 consumer4;

    @Autowired
    private OffsetThreadConsumer5 consumer5;

    int i = 0;

    @GetMapping("/paging")
    @Transactional(readOnly = true)
    public void paging(){
        final int pageSize = 1000;
        Slice<ResidentCitizen> page;
        int index = 0;
        do{
            page = repo.findAllBy(PageRequest.of(index, pageSize));
            for (ResidentCitizen residentCitizen : page){
                entityManager.detach(residentCitizen);
            }
            System.out.println(page.getSize());
//            entityManager.clear();
            System.out.println(page.getSize());
            index++;
        }while (page.hasNext());
    }

    @GetMapping("/streaming")
    @Transactional(readOnly = true)
    public void streaming(){
        try (Stream<ResidentCitizen> stream = repo.streamAllCitizen()){
            stream.forEach(residentCitizen -> {
//                System.out.println(residentCitizen.getId());
                i = i +1;
                entityManager.detach(residentCitizen);
            });
            System.out.println(i);

//            stream.forEach(rethrowConsumer(residentCitizen -> {
//                System.out.println(residentCitizen.getId());
//                i = i +1;
//                entityManager.detach(residentCitizen);
//            }));

        }
    }

    @PostMapping("/add")
    public ResponseEntity<?> addOne(@RequestBody ResidentCitizen residentCitizen){
        return ResponseEntity.ok(repo.save(residentCitizen));
    }

    @GetMapping("/last")
    public ResponseEntity<?> getLast(@RequestParam int limit, @RequestParam int offset){
        return ResponseEntity.ok(repo.getAListCitizen(limit, offset));
    }

    @GetMapping("/pc")
    public void threadPC(){
        producer.start();
        producer1.start();
        producer2.start();
        producer3.start();
        consumer.start();
        consumer1.start();
        consumer2.start();
        consumer3.start();
        consumer4.start();
        consumer5.start();
    }

    @GetMapping("/findall")
    public ResponseEntity<?> findAll(){
        return ResponseEntity.ok(repo.findAll());
    }

//    @GetMapping("/sqlpc")
//    @Transactional(readOnly = true)
//    public void sqlpc(){
//        try(Stream<ResidentCitizen> stream = repo.streamAllCitizen()){
//            stream.forEach(residentCitizen -> {
//                try {
//                    queue.put(residentCitizen);
//                    entityManager.detach(residentCitizen);
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
//            });
//        }
//    }

}
