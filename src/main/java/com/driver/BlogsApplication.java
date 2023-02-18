package com.driver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BlogsApplication {
    public static void main(String[] args) throws Exception{
        try {
            SpringApplication.run(BlogsApplication.class, args);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }
}
