package com.service;

import com.model.Image;
import com.model.dto.ImageDTO;

import java.util.List;

public interface IImageService extends ICrudService<Image>{
    List<Image> getAllImageByAccountId(long id);
    ImageDTO finById(long id);
}
