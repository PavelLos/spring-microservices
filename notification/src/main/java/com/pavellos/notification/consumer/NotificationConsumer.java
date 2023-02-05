package com.pavellos.notification.consumer;

import com.pavellos.clients.notification.dto.NotificationRequest;
import com.pavellos.notification.service.NotificationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@AllArgsConstructor
public class NotificationConsumer {
    private final NotificationService notificationService;

    @RabbitListener(queues = "${rabbitmq.queue.notification}")
    public void consume(NotificationRequest notificationRequest){
         log.info("Consumed message from rabbitMq. Request: {}", notificationRequest);
         notificationService.send(notificationRequest);
    }
}
