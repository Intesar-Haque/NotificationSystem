package com.example.notification.service.impl;

import com.example.notification.model.NotificationModel;
import com.example.notification.model.NotificationModelDto;
import com.example.notification.repo.NotificationRepo;
import com.example.notification.service.SenderService;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SenderServiceImpl implements SenderService {
    @Autowired
    private AmqpTemplate rabbitTemplate;
    @Autowired
    NotificationRepo notificationRepo;

    @Override
    public void send(NotificationModelDto notification, String exchangeName) {
        rabbitTemplate.convertAndSend(exchangeName, "", notification);
        //TODO : ADD TO DATABASE
//        NotificationModel notificationModel = new NotificationModel();
//        notificationModel.setTitle(notification.getTitle());
//        notificationModel.setBody(notification.getBody());
//        notificationModel.setLink(notification.getLink());
//        notificationRepo.save(notificationModel);

    }
}
