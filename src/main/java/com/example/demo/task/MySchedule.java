package com.example.demo.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: liming522
 * @Description:
 * @Date: create in 2021/6/24 16:51
 * 本身就支持并发，多个定时任务由单个线程控制; 且单个定时任务是串行执行，执行完一次在执行第二次。
 */
@Component
public class MySchedule {
    Logger logger = LoggerFactory.getLogger(MySchedule.class);

   // @Scheduled(cron = "0/1 * * * * ? ") //每1秒执行一次
    public void testCron1() {
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
       /* try {
            Thread.sleep(5*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        logger.info(sdf.format(new Date()) + "任务1*********每1秒执行一次");
    }



  //  @Scheduled(cron = "0/1 * * * * ? ") //每1秒执行一次
    public void testCron2() {
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        logger.info(sdf.format(new Date()) + "任务2*********每1秒执行一次");
    }
}
    
