package com.example.notification.service;

import com.example.notification.dto.NotifyRequest;

public interface NotifyService {

    void send(NotifyRequest notifyRequest);
}
