package com.example.notification.controller;

import com.example.notification.model.NotificationModelDto;
import com.example.notification.service.ExchangeService;
import com.example.notification.service.QueueService;
import com.example.notification.service.ReceiverService;
import com.example.notification.service.SenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RabbitNotificationController {
    @Autowired
    QueueService queueService;
    @Autowired
    ExchangeService exchangeService;
    @Autowired
    SenderService senderService;
    @Autowired
    ReceiverService receiverService;

    @RequestMapping("/queue/create")
    public ResponseEntity<?> createQueue(@RequestParam String queueName){
        queueService.addNewQueue(queueName);
        return ResponseEntity.ok().body("Queue Created");
    }
    @RequestMapping("/exchange/create")
    public ResponseEntity<?> createExchange(@RequestParam String exchangeName){
        exchangeService.addNewExchange(exchangeName);
        return ResponseEntity.ok().body("Exchange Created");
    }
    @RequestMapping("/exchange/bind")
    public ResponseEntity<?> bindQueueExchane(@RequestParam String queueName, @RequestParam String exchangeName){
        exchangeService.addQueueToExchange(queueName,exchangeName);
        return ResponseEntity.ok().body("Queue added to exchange");
    }
    @RequestMapping("/notification/send")
    public ResponseEntity<?> sendNotification(@RequestBody NotificationModelDto notification, @RequestParam String exchangeName){
        senderService.send(notification,exchangeName);
        return ResponseEntity.ok().body("Notification sent");
    }
    @RequestMapping("/notification/receive")
    public ResponseEntity<?> recieveNotification(@RequestParam String queueName){
        List<NotificationModelDto> notifications = receiverService.receive(queueName);
        return ResponseEntity.ok().body(notifications);
    }

}
