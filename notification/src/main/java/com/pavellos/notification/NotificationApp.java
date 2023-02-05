package com.pavellos.notification;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(
        scanBasePackages = {
                "com.pavellos.notification",
                "com.pavellos.amqp"
        }
)
@EnableFeignClients(basePackages = "com.pavellos.clients")
public class NotificationApp
{
    public static void main( String[] args )
    {
        SpringApplication.run(NotificationApp.class, args);
    }
}
