package com.pavellos.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.pavellos.clients")
public class CustomerApp
{
    public static void main( String[] args )
    {
        SpringApplication.run(CustomerApp.class,args);
    }
}
