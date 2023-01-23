package com.example.fileuploaddownloadapproach1.repository;

import com.example.fileuploaddownloadapproach1.model.ImageData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StorageRepository extends JpaRepository<ImageData, Long> {

    Optional<ImageData> findByName(String fileName);

}
