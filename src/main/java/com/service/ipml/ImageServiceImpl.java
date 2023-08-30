package com.service.ipml;

import com.model.Image;
import com.repository.IImageRepository;
import com.service.IImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageServiceImpl implements IImageService {
    @Autowired
    IImageRepository iImageRepository;

    @Override
    public List<Image> getAll() {
        return null;
    }

    @Override
    public Image getById(long id) {
        return null;
    }

    @Override
    public Image create(Image image) {
        return null;
    }

    @Override
    public Image edit(Image image) {
        return null;
    }

    @Override
    public void deleteById(long id) {

    }
}
