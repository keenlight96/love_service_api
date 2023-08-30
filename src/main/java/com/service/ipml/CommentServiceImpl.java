package com.service.ipml;

import com.model.Comment;
import com.repository.ICommentRepository;
import com.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements ICommentService {
    @Autowired
    ICommentRepository iCommentRepository;

    @Override
    public List<Comment> getAll() {
        return null;
    }

    @Override
    public Comment getById(long id) {
        return null;
    }

    @Override
    public Comment create(Comment comment) {
        return null;
    }

    @Override
    public Comment edit(Comment comment) {
        return null;
    }

    @Override
    public void deleteById(int id) {

    }
}
