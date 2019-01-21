package com.wang.inis;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.*;

@EnableTransactionManagement
@SpringBootApplication
@EnableCaching
@EnableScheduling
public class InisApplication {


    public static void main(String[] args) {


        SpringApplication.run(InisApplication.class, args);

    }
}


