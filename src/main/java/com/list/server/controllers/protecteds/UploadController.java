package com.list.server.controllers.protecteds;

import com.list.server.exceptions.UploadException;
import com.list.server.models.responses.UploadResponse;
import com.list.server.services.UploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/admin/upload")
@RequiredArgsConstructor
@Component("protectedUploadController")
public class UploadController {

    private final UploadService service;

    @PostMapping("/create/{type}")
    public ResponseEntity<UploadResponse> uploadImage(@PathVariable("type") String type, @RequestParam("file")MultipartFile file) {
        try {
            String fileName = service.store(file, type);
            UploadResponse response = new UploadResponse(fileName);
            return ResponseEntity.ok(response);
        } catch (UploadException exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new UploadResponse("Failed to store file: " + exception.getMessage()));
        }
    }

    @DeleteMapping("/delete/{type}/{filename}")
    public ResponseEntity<String> deleteFile(@PathVariable("type") String type, @PathVariable("filename") String filename) {
        try {
            service.deleteByFileName(filename, type);
            return ResponseEntity.ok("File deleted successfully: " + filename);
        } catch (UploadException exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete file: " + exception.getMessage());
        }
    }
}
