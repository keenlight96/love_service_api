package com.service.ipml;

import com.model.Chat;
import com.repository.IChatRepository;
import com.service.IChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatServiceImpl implements IChatService {
    @Autowired
    IChatRepository iChatRepository;

    @Override
    public List<Chat> getAll() {
        return null;
    }

    @Override
    public Chat getById(int id) {
        return null;
    }

    @Override
    public Chat create(Chat chat) {
        return null;
    }

    @Override
    public Chat edit(Chat chat) {
        return null;
    }

    @Override
    public void deleteById(int id) {

    }
}
