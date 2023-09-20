package com.service.ipml;

import com.model.Account;
import com.model.Message;
import com.repository.IMessageRepository;
import com.service.IMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class MessageServiceImpl implements IMessageService {
    @Autowired
    IMessageRepository iMessageRepository;

    @Override
    public List<Message> getAll() {
        return iMessageRepository.findAll();
    }

    @Override
    public Message getById(long id) {
        return iMessageRepository.findById(id).get();
    }

    @Override
    public Message create(Message message) {
        return iMessageRepository.save(message);
    }

    @Override
    public Message edit(Message message) {
        return iMessageRepository.save(message);
    }

    @Override
    public void deleteById(long id) {
        iMessageRepository.deleteById(id);
    }

    @Override
    public List<Message> getAllBySenderAndReceiver(Long senderId, Long receiverId) {
        List<Message> messages = iMessageRepository.findAllBySenderAndReceiver(senderId, receiverId);
        for (Message message :
                messages) {
            if (message.getReceiver().getId() == senderId) {
                message.setIsRead(true);
                iMessageRepository.save(message);
            }
        }
        messages.sort(new Comparator<Message>() {
            @Override
            public int compare(Message o1, Message o2) {
                return (int) (o1.getId() - o2.getId());
            }
        });
        return messages;
//        return iMessageRepository.findAll();
    }

    @Override
    public Message setReadMessage(Long id) {
        Message message = getById(id);
        message.setIsRead(true);
        return edit(message);
    }

    @Override
    public List<Message> getAllNotifications(Long userId) {
        return iMessageRepository.getAllNotifications(userId);
    }

    @Override
    public Message confirmReadNotification(Long notificationId) {
        Message notification = iMessageRepository.findById(notificationId).orElseGet(null);
        notification.setIsRead(true);
        iMessageRepository.save(notification);
        return notification;
    }

    @Override
    public void confirmReadAllNotifications(Long userId) {
        List<Message> notifications = iMessageRepository.getAllUnreadNotifications(userId);
        for (Message message :
                notifications) {
            message.setIsRead(true);
            iMessageRepository.save(message);
        }
    }
}
