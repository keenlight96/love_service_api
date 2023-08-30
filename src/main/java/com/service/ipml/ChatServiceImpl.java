package com.service.ipml;

import com.model.Bill;
import com.model.Chat;
import com.repository.IChatRepository;
import com.service.IChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChatServiceImpl implements IChatService {
    @Autowired
    IChatRepository iChatRepository;

    @Override
    public List<Chat> getAll() {
        return iChatRepository.findAll();
    }

    @Override
    public Chat getById(long id) {
        Optional<Chat> chat = iChatRepository.findById(id);
        if (chat.isPresent()) {
            return chat.get();
        } else {
            return null;
        }
    }

    @Override
    public Chat create(Chat chat) {
        return iChatRepository.save(chat);
    }

    @Override
    public Chat edit(Chat chat) {
        return iChatRepository.save(chat);
    }

    @Override
    public void deleteById(long id) {
        iChatRepository.deleteById(id);
    }
}
