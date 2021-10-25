package com.example.notification.service.impl;

import com.example.notification.model.NotificationModelDto;
import com.example.notification.service.ReceiverService;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class ReceiverServiceImpl implements ReceiverService {
    @Autowired
    private AmqpTemplate rabbitTemplate;

    @Override
    public List<NotificationModelDto> receive(String queueName) {
        List<NotificationModelDto> notifications = new ArrayList<>();
        NotificationModelDto notification = (NotificationModelDto) rabbitTemplate.receiveAndConvert(queueName);
        while(Objects.nonNull(notification)){
            notifications.add(notification);
            notification = (NotificationModelDto) rabbitTemplate.receiveAndConvert(queueName);
        }
        return notifications;
    }
}
