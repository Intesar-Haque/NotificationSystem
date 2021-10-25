package com.example.notification.service;

public interface ExchangeService {
    void addNewExchange(String exchangeName);
    void addQueueToExchange(String queueName, String exchangeName);
}
