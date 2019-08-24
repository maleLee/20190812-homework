package com.lee.homework;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Company AAA软件教育
 * @Author Seven Lee
 * @Date Create in 2019/8/13 8:55
 * @Description
 **/
@SpringBootApplication
@MapperScan("com.lee.homework.mapper")
public class ApplicationRun {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationRun.class, args);
    }

}
