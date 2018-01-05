package com.smartdo.scc.mabang.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
@EnableAutoConfiguration
//@ComponentScan(basePackages = {"com.smartdo.scc.mabang.backend.service"})
public class StartMain {
    public static void main(String[] args) {
        SpringApplication.run(StartMain.class, args);
    }
}