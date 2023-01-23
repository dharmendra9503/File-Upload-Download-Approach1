package com.example.fileuploaddownloadapproach1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface StorageService {

    String uploadImage(MultipartFile file) throws IOException;

    byte[] downloadImage(String fileName);
}
