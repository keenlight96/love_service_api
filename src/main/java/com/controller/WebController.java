package com.controller;

import com.model.Hello;
import com.model.Message;
import com.service.IAccountService;
import com.service.IMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class WebController {
    @Autowired
    IAccountService iAccountService;
    @Autowired
    IMessageService iMessageService;
    @Autowired
    SimpMessagingTemplate simpMessagingTemplate;

//    @MessageMapping("/hello")
//    public void greeting(Message2 message2) throws Exception {
//        simpMessagingTemplate.convertAndSend("/topic/hi", new Hello(message2.getSender()+" : " + message2.getMessage()));
//    }

    @MessageMapping("/hello")
    public void greeting(Message message) throws Exception {
//        System.out.println(message.getSender().toString());
//        message.setSender(iAccountService.getById(message.getSender().getId()));
//        Account sender = iAccountService.getById(message.getSender().getId());
//        Account receiver = iAccountService.getById(message.getReceiver().getId());
//        message.setSender(sender);
//        message.setReceiver(receiver);
        iMessageService.create(message);
        String destination = "/topic/hi/";
        Long senderId  = message.getSender().getId();
        Long receiverId  = message.getReceiver().getId();
        if (senderId < receiverId) {
            destination += senderId + "_" + receiverId;
        } else {
            destination += receiverId + "_" + senderId;
        }
        simpMessagingTemplate.convertAndSend(destination, new Hello(message));
    }
}
