package com.ChanDao.controller;

import com.ChanDao.task.ScheduleChanDaoTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@SpringBootApplication
public class HelloController {

    @Autowired
    private ScheduleChanDaoTask scheduleChanDaoTask;

    @RequestMapping("hello")
    String hello() throws IOException {
        scheduleChanDaoTask.runChanDaoTask();
        return "Hello World!";
    }

}
