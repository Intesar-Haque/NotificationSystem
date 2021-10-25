package com.example.notification.service.impl;

import com.example.notification.service.ExchangeService;
import com.example.notification.service.QueueService;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;

@Service
public class ExchangeServiceImpl implements ExchangeService {
    @Autowired
    private RabbitAdmin rabbitAdmin;

    @Override
    public void addNewExchange(String exchangeName) {
        Exchange exchange = new Exchange() {
            @Override
            public String getName() {
                return exchangeName;
            }
            @Override
            public String getType() {
                return "direct";
            }
            @Override
            public boolean isDurable() {
                return true;
            }
            @Override
            public boolean isAutoDelete() {
                return false;
            }
            @Override
            public Map<String, Object> getArguments() {
                return null;
            }
            @Override
            public boolean isDelayed() {
                return false;
            }
            @Override
            public boolean isInternal() {
                return false;
            }
            @Override
            public boolean shouldDeclare() {
                return false;
            }
            @Override
            public Collection<?> getDeclaringAdmins() {
                return null;
            }
            @Override
            public boolean isIgnoreDeclarationExceptions() {
                return false;
            }
            @Override
            public void setAdminsThatShouldDeclare(Object... objects) {

            }
        };
        rabbitAdmin.declareExchange(exchange);
    }

    @Override
    public void addQueueToExchange(String queueName, String exchangeName) {
        Queue queue = new Queue(queueName, false, false, false);
        Binding binding = new Binding(
                queueName,
                Binding.DestinationType.QUEUE,
                exchangeName,
                "",
                null
        );
        rabbitAdmin.declareQueue(queue);
        rabbitAdmin.declareBinding(binding);

    }


}
