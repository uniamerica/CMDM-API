package com.example.cmdmapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
public class CmdmApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(CmdmApiApplication.class, args);
    }

}
