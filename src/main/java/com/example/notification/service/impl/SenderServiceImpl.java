package com.example.notification.service.impl;


import com.example.notification.model.NotificationModelDto;
import com.example.notification.service.SenderService;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SenderServiceImpl implements SenderService {
    @Autowired
    private AmqpTemplate rabbitTemplate;

    @Override
    public void send(NotificationModelDto notification, String exchangeName) {
        rabbitTemplate.convertAndSend(exchangeName, "", notification);
        //TODO : ADD TO DATABASE
    }
}
