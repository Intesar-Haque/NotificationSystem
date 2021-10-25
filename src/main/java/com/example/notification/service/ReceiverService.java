package com.example.notification.service;

import com.example.notification.model.NotificationModelDto;

import java.util.List;

public interface ReceiverService {
    List<NotificationModelDto> receive(String queueName);
}
