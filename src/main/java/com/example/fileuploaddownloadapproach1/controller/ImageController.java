package com.example.fileuploaddownloadapproach1.controller;

import com.example.fileuploaddownloadapproach1.service.StorageService;
import com.example.fileuploaddownloadapproach1.utils.ImageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/image")
public class ImageController {

    @Autowired
    private StorageService storageService;

    @GetMapping("")
    public String welcome(){
        return "This is small module of File Download Upload Approach 1";
    }

    @PostMapping("/upload")
    public ResponseEntity<ImageResponse> uploadImage(@RequestParam("image")MultipartFile file) {

        String fileName;
        try {
            fileName = storageService.uploadImage(file);

            return new ResponseEntity<>(new ImageResponse(fileName, "File Uploaded Successfully !!"),
                    HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(
                    new ImageResponse(null, "Internal Server Error, File Not Uploaded"),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/download/{fileName}")
    public ResponseEntity<?> downloadImage(@PathVariable String fileName){
        byte[] imageData = storageService.downloadImage(fileName);
//        System.out.println(Arrays.toString(imageData));

        if(imageData != null){
            return ResponseEntity.status(HttpStatus.OK)
                    .contentType(MediaType.valueOf(MediaType.IMAGE_PNG_VALUE))
                    .body(imageData);
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("File doesn't exist");
        }
    }
}
