package com.controller;

import com.model.Image;
import com.service.IImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/images")
public class ImageController {
    @Autowired
    IImageService iImageService;
    @GetMapping("/{id}")
    public ResponseEntity<List<Image>> getAllImageByIdAccount(@PathVariable long id){
        List<Image> images = iImageService.getAllImageByAccountId(id);
        return new ResponseEntity<>(images, HttpStatus.OK);
    }
}
