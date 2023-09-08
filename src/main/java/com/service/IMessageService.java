package com.service;

import com.model.Account;
import com.model.Message;

import java.util.List;

public interface IMessageService extends ICrudService<Message> {
    public List<Message> getAllBySenderAndReceiver(Account sender, Account receiver);
}
