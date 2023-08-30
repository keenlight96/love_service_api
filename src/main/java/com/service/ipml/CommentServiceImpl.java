package com.service.ipml;

import com.model.Chat;
import com.model.Comment;
import com.repository.ICommentRepository;
import com.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements ICommentService {
    @Autowired
    ICommentRepository iCommentRepository;

    @Override
    public List<Comment> getAll() {
        return iCommentRepository.findAll();
    }

    @Override
    public Comment getById(long id) {
        Optional<Comment> comment = iCommentRepository.findById(id);
        if (comment.isPresent()) {
            return comment.get();
        } else {
            return null;
        }
    }

    @Override
    public Comment create(Comment comment) {
        return iCommentRepository.save(comment);
    }

    @Override
    public Comment edit(Comment comment) {
        return iCommentRepository.save(comment);
    }

    @Override
    public void deleteById(long id) {
        iCommentRepository.deleteById(id);
    }
}
