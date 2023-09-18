package com.controller;

import com.model.Account;
import com.model.Bill;
import com.model.Hello;
import com.model.Message;
import com.service.IAccountService;
import com.service.IBillService;
import com.service.IMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;

import java.util.Date;

@Controller
public class WebController {
    @Autowired
    IAccountService iAccountService;
    @Autowired
    IMessageService iMessageService;
    @Autowired
    IBillService iBillService;
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

        String destination = "/topic/";

        if (message.getType().equals("private")) {
            long senderId = message.getSender().getId();
            long receiverId = message.getReceiver().getId();

            Message newMessage = iMessageService.create(message);
            simpMessagingTemplate.convertAndSend(destination + senderId, newMessage);
            simpMessagingTemplate.convertAndSend(destination + receiverId, newMessage);
        } else if (message.getType().equals("notification")) {
            Bill currentBill = iBillService.getById(Long.parseLong(message.getSubtype()));
            String status = currentBill.getStatus().getNameStatus();
            String string = "";
            if (status.equals("wait")) {
                string = "đã tạo một đơn thuê mới.\nMã đơn [" + message.getSubtype() + "].";

                message.setSender(currentBill.getAccountUser());
                message.setReceiver(currentBill.getAccountCCDV());
            } else if (status.equals("recevied")) {
                string = "đã xác nhận đơn thuê của bạn.\nMã đơn [" + message.getSubtype() + "].";

                message.setSender(currentBill.getAccountCCDV());
                message.setReceiver(currentBill.getAccountUser());
            } else if (status.equals("complete")) {
                string = "đã xác nhận hoàn thành đơn thuê.\nMã đơn [" + message.getSubtype() + "].";

                message.setSender(currentBill.getAccountUser());
                message.setReceiver(currentBill.getAccountCCDV());
            } else if (status.equals("cancel from wait by user")) {
                string = "đã hủy đơn thuê mới tạo.\nMã đơn [" + message.getSubtype() + "].";

                message.setSender(currentBill.getAccountUser());
                message.setReceiver(currentBill.getAccountCCDV());
            } else if (status.equals("cancel from wait by ccdv")) {
                string = "đã hủy đơn thuê của bạn.\nMã đơn [" + message.getSubtype() + "].";

                message.setSender(currentBill.getAccountCCDV());
                message.setReceiver(currentBill.getAccountUser());
            } else if (status.equals("cancel from recevied by user")) {
                string = "đã hủy đơn thuê mà bạn đã chấp thuận.\nMã đơn [" + message.getSubtype() + "].";

                message.setSender(currentBill.getAccountUser());
                message.setReceiver(currentBill.getAccountCCDV());
            } else if (status.equals("cancel from recevied by ccdv")) {
                string = "đã hủy đơn thuê của bạn ở trạng thái đã chấp thuận.\nMã đơn [" + message.getSubtype() + "].";

                message.setSender(currentBill.getAccountCCDV());
                message.setReceiver(currentBill.getAccountUser());
            }

            message.setMessage(string);
            message.setSubtype(status);
            message.setDate(new Date());

            Message newMessage = iMessageService.create(message);
            simpMessagingTemplate.convertAndSend(destination + newMessage.getReceiver().getId(), newMessage);



//            Account sender = iAccountService.getById(message.getSender().getId());
//
//            if (message.getSubtype().equals("create bill")) {
//                String string = "đã tạo một đơn thuê mới.\nMã đơn [" + message.getMessage() + "].";
//
//                message.setSender(sender);
//                message.setMessage(string);
//
//                Message newMessage = iMessageService.create(message);
//                simpMessagingTemplate.convertAndSend(destination + receiverId, newMessage);
//            } else {
//                Bill currentBill = iBillService.getById(Long.parseLong(message.getMessage()));
//
//                if (message.getSubtype().equals("receive bill")) {
//                    String string = "đã chấp nhận đơn thuê của bạn.\nMã đơn [" + message.getMessage() + "].";
//
//                    message.setSender(sender);
//                    message.setMessage(string);
//
//                    Message newMessage = iMessageService.create(message);
//                    simpMessagingTemplate.convertAndSend(destination + currentBill.getAccountUser().getId(), newMessage);
//                } else if (message.getSubtype().equals("cancel bill from wait by ccdv")) {
//                    String string = "đã hủy đơn thuê của bạn.\nMã đơn [" + message.getMessage() + "].";
//
//                    message.setSender(sender);
//                    message.setMessage(string);
//
//                    Message newMessage = iMessageService.create(message);
//                    simpMessagingTemplate.convertAndSend(destination + currentBill.getAccountUser().getId(), newMessage);
//                }
//            }
        }
    }
}
