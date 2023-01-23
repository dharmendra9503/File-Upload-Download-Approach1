package com.example.fileuploaddownloadapproach1.service.Implementation;

import com.example.fileuploaddownloadapproach1.model.ImageData;
import com.example.fileuploaddownloadapproach1.repository.StorageRepository;
import com.example.fileuploaddownloadapproach1.service.StorageService;
import com.example.fileuploaddownloadapproach1.utils.ImageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class StorageImplementation implements StorageService{

    @Autowired
    private StorageRepository storageRepository;

    @Override
    public String uploadImage(MultipartFile file) throws IOException {

        String fileName = file.getOriginalFilename();
        String fileType = file.getContentType();

        storageRepository.save(ImageData.builder()
                .name(fileName)
                .type(fileType)
                .imageData(ImageUtils.compressImage(file.getBytes())).build());

        return fileName;
    }

    @Override
    public byte[] downloadImage(String fileName) {

        Optional<ImageData> dbImageData = storageRepository.findByName(fileName);

        return dbImageData.map(imageData -> ImageUtils.decompressImage(imageData.getImageData())).orElse(null);
    }
}
