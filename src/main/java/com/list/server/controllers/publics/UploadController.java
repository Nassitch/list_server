package com.list.server.controllers.publics;

import com.list.server.exceptions.UploadException;
import com.list.server.exceptions.UploadFileNotFoundException;
import com.list.server.services.UploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/public/upload")
@RequiredArgsConstructor
@Component("publicUploadController")
public class UploadController {

    private final UploadService service;

    @GetMapping("/read/{type}/{filename}")
    public ResponseEntity<Resource> getImage(@PathVariable("type") String type, @PathVariable("filename") String filename) {
        try {
            Resource resource = service.loadAsResource(filename, type);
            HttpHeaders headers = new HttpHeaders();
            headers.add(headers.CONTENT_TYPE, Files.probeContentType(resource.getFile().toPath()));
            return new ResponseEntity<>(resource, headers, HttpStatus.OK);
        } catch (UploadException exception) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (IOException exception) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ExceptionHandler(UploadFileNotFoundException.class)
    public ResponseEntity<?> handleUploadFileNotFound(UploadFileNotFoundException exception) {
        return ResponseEntity.notFound().build();
    }
}
