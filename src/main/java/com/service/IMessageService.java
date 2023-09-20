package com.service;

import com.model.Account;
import com.model.Message;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IMessageService extends ICrudService<Message> {
    List<Message> getAllBySenderAndReceiver(Long senderId, Long receiverId);
    Message setReadMessage(Long id);
    List<Message> getAllNotifications(Long userId);
    Message confirmReadNotification(Long notificationId);
    void confirmReadAllNotifications(Long userId);
}
