package com.controller;

import com.model.Image;
import com.model.dto.ImageDTO;
import com.service.IImageService;
import com.service.ipml.ImageServiceImpl;
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
    @Autowired
    private ImageServiceImpl imageService;
        @GetMapping("/albumImageCCDV")
    public ResponseEntity<List<Image>> getAllImage(){
        return new ResponseEntity<>(iImageService.getAll(),HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<List<Image>> getAllImageByIdAccount(@PathVariable long id){
        List<Image> images = iImageService.getAllImageByAccountId(id);
        return new ResponseEntity<>(images, HttpStatus.OK);
    }

    @PostMapping("/album")
    public ResponseEntity<ImageDTO> saveImage(@RequestBody ImageDTO imageDTO){
           imageService.save(imageDTO);
            return new ResponseEntity<>(imageDTO,HttpStatus.OK);
    }

}
