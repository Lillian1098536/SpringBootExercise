package edu.university.interceptor.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class LoggerController {

    @RequestMapping("/logger")
    public String executeLogger() {
        log.info("Inside the executeLogger method");
        return "Test Spring Boot Interceptor";
    }

}
