package com.example.SqlThread.thread;

import com.example.SqlThread.entity.ResidentCitizen;
import com.example.SqlThread.repository.ResidentCitizenRepo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.BlockingQueue;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class OffsetThreadConsumer3 extends Thread{
    @Autowired
    private BlockingQueue<ResidentCitizen> queue;

    @Override
    public void run() {
        ResidentCitizen residentCitizen;
        boolean b = true;
        while (b){
            try {
                if ((residentCitizen = queue.take()).getId().equals("999999")){
                    this.interrupt();
                    b = false;
                } else {
                    System.out.println(residentCitizen.getId());
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
