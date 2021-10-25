package com.example.notification.service;

import com.example.notification.model.NotificationModelDto;

public interface SenderService {
    void send(NotificationModelDto notification, String exchangeName);
}
