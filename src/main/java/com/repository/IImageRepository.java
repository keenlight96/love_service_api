package com.repository;

import com.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IImageRepository extends JpaRepository<Image,Long> {
    List<Image> getImageByAccount_Id(long id);
}
