package com.service.ipml;

import com.model.Comment;
import com.model.Likes;
import com.repository.ILikeRepository;
import com.service.ILikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LikeServiceImpl implements ILikeService {
    @Autowired
    ILikeRepository iLikeRepository;
    @Override
    public List<Likes> getAll() {
        return iLikeRepository.findAll();
    }

    @Override
    public Likes getById(long id) {
        Optional<Likes> like = iLikeRepository.findById(id);
        if (like.isPresent()) {
            return like.get();
        } else {
            return null;
        }
    }

    @Override
    public Likes create(Likes likes) {
        return iLikeRepository.save(likes);
    }

    @Override
    public Likes edit(Likes likes) {
        return iLikeRepository.save(likes);
    }

    @Override
    public void deleteById(long id) {
        iLikeRepository.deleteById(id);
    }
}
