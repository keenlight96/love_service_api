package com.service.ipml;

import com.model.Comment;
import com.model.Image;
import com.repository.IImageRepository;
import com.service.IImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ImageServiceImpl implements IImageService {
    @Autowired
    IImageRepository iImageRepository;

    @Override
    public List<Image> getAll() {
        return iImageRepository.findAll();
    }

    @Override
    public Image getById(long id) {
        Optional<Image> image = iImageRepository.findById(id);
        if (image.isPresent()) {
            return image.get();
        } else {
            return null;
        }
    }

    @Override
    public Image create(Image image) {
        return iImageRepository.save(image);
    }

    @Override
    public Image edit(Image image) {
        return iImageRepository.save(image);
    }

    @Override
    public void deleteById(long id) {
        iImageRepository.deleteById(id);
    }


}
