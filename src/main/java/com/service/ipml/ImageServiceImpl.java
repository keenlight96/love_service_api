package com.service.ipml;

import com.model.Comment;
import com.model.Image;
import com.model.dto.ImageDTO;
import com.repository.IImageRepository;
import com.service.IImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
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


    @Override
    public List<Image> getAllImageByAccountId(long id) {
        return iImageRepository.getImageByAccount_Id(id);
    }

    @Override
    public ImageDTO finById(long id) {
        List<Image> imageList = iImageRepository.findAll();
        List<String> strings = new ArrayList<>();
        for (Image image :imageList){
            strings.add(image.getImg());
        }
        Image image = iImageRepository.findById(id).get();
        ImageDTO imageDTO = new ImageDTO(image.getId(), Collections.singletonList(image.getImg()),image.getAccount(),image.getIsActive());
    return imageDTO;
    }

}
