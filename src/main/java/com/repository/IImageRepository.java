package com.repository;

import com.model.Image;
import com.model.dto.ImageDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IImageRepository extends JpaRepository<Image,Long> {
    @Query("SELECT i FROM Image i " +
            "WHERE i.account.id = :accountId " +
            "ORDER BY i.id DESC ")
    List<Image> getImagesSortedByIdDesc(@Param("accountId") long accountId);


}
