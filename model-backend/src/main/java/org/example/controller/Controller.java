package org.example.controller;

import org.example.entities.ResponseDTO;
import org.example.modelbackend.GrpcClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

@RestController
public class Controller {

    @PostMapping("/predict")
    public ResponseEntity<ResponseDTO> predict(@RequestParam("image") MultipartFile image) throws IOException {
        return ResponseEntity.ok(GrpcClient.run(image));
    }

    @GetMapping("/images/{id}")
    public ResponseEntity<byte[]> getImage(@PathVariable String id) {
        byte[] imageData = ImageStorage.getImage(id);
        if (imageData == null) {
            return ResponseEntity.notFound().build();
        }
        // For example, if your images are PNG
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_PNG)
                .body(imageData);
    }
}
