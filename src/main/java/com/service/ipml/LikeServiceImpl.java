package com.service.ipml;

import com.model.Like;
import com.repository.ILikeRepository;
import com.service.ILikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LikeServiceImpl implements ILikeService {
    @Autowired
    ILikeRepository iLikeRepository;
    @Override
    public List<Like> getAll() {
        return null;
    }

    @Override
    public Like getById(long id) {
        return null;
    }

    @Override
    public Like create(Like like) {
        return null;
    }

    @Override
    public Like edit(Like like) {
        return null;
    }

    @Override
    public void deleteById(int id) {

    }
}
