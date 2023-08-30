package com.service.ipml;

import com.model.Likes;
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
    public List<Likes> getAll() {
        return null;
    }

    @Override
    public Likes getById(long id) {
        return null;
    }

    @Override
    public Likes create(Likes likes) {
        return null;
    }

    @Override
    public Likes edit(Likes likes) {
        return null;
    }

    @Override
    public void deleteById(long id) {

    }
}
