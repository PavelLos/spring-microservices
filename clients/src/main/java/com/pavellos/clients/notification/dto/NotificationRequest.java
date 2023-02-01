package com.pavellos.clients.notification.dto;


public record NotificationRequest(Long cusId, String email, String message) {
}
