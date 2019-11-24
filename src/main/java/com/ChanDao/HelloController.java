package com.ChanDao;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class HelloController {

    @RequestMapping("hello")
    String hello() {
        return "Hello World!";
    }

}
