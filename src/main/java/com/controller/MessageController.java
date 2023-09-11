package com.controller;

import com.model.Account;
import com.model.Message;
import com.service.IAccountService;
import com.service.IMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@Controller
@RequestMapping("/message")
public class MessageController {
    @Autowired
    IMessageService iMessageService;
    @Autowired
    IAccountService iAccountService;

    @GetMapping("/allBySenderAndReceiver/{receiverId}")
    public ResponseEntity<List<Message>> getAllBySenderAndReceiver(@PathVariable Long receiverId) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Account account = iAccountService.findByUsername(userDetails.getUsername()).orElseGet(null);
        return new ResponseEntity<>(iMessageService.getAllBySenderAndReceiver(account.getId(), receiverId), HttpStatus.OK);
    }

    @PostMapping("/hi/{senderId}/{receiverId}")
    public ResponseEntity<String> hiMessage(@PathVariable long senderId, @PathVariable long receiverId) {
        Message message = new Message();
        message.setSender(iAccountService.getById(senderId));
        message.setReceiver(iAccountService.getById(receiverId));
        message.setMessage("Hi");

        iMessageService.create(message);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
