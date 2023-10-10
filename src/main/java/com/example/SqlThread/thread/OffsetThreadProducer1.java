package com.example.SqlThread.thread;

import com.example.SqlThread.entity.ResidentCitizen;
import com.example.SqlThread.repository.ResidentCitizenRepo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.BlockingQueue;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class OffsetThreadProducer1 extends Thread{
    @Autowired
    ResidentCitizenRepo repo;

    @Autowired
    private BlockingQueue<ResidentCitizen> queue;

    @Override
    public void run() {
        long start = System.currentTimeMillis();
        boolean b = true;
        while (b){
            for (int i = 300; i<600; i++){
                List<ResidentCitizen> list = repo.getAListCitizen(1000, i*1000);
                for (ResidentCitizen residentCitizen : list){
                    try {
                        queue.put(residentCitizen);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
            b = false;
            long end = System.currentTimeMillis();
            System.out.println("Consumed time :" + (end - start));
        }
    }
}
