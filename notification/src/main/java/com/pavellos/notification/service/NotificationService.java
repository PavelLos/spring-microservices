package com.pavellos.notification.service;

import com.pavellos.clients.notification.dto.NotificationRequest;
import com.pavellos.notification.model.Notification;
import com.pavellos.notification.repository.NotificationRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public record NotificationService(NotificationRepository notificationRepository) {

    public void send(NotificationRequest notificationRequest) {
        notificationRepository.save(
                Notification.builder()
                        .toCustomerId(notificationRequest.cusId())
                        .toCustomerEmail(notificationRequest.email())
                        .sender("BootServices")
                        .message(notificationRequest.message())
                        .sentAt(LocalDateTime.now())
                        .build()
        );
    }

}
