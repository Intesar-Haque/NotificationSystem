package com.example.notification.service.impl;

import com.example.notification.service.QueueService;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;

@Service
public class QueueServiceImpl implements QueueService {
    @Autowired
    private RabbitAdmin rabbitAdmin;

    @Override
    public void addNewQueue(String queueName) {
        Queue queue = new Queue(queueName, false, false, false);
        Binding binding = new Binding(
                queueName,
                Binding.DestinationType.QUEUE,
                "Default",
                "",
                null
        );
        rabbitAdmin.declareQueue(queue);
        rabbitAdmin.declareBinding(binding);
    }
}
