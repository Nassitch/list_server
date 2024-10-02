package com.list.server.services;

import com.list.server.exceptions.UploadException;
import com.list.server.repositories.UploadRepository;
import com.list.server.upload.UploadProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class UploadService implements UploadRepository {

    private final Path rootLocation;

    @Autowired
    public UploadService(UploadProperties properties) {

        if (properties.getLocation().trim().isEmpty()) {
            throw new UploadException("File upload location can not be Empty.");
        }

        this.rootLocation = Paths.get(properties.getLocation());
    }

    public String store(MultipartFile file, String type) {
        try {
            if (file.isEmpty()) {
                throw new UploadException("Failed to store empty file.");
            }

            String originalFileName = file.getOriginalFilename();
            if (originalFileName == null || originalFileName.contains("..")) {
                throw new UploadException("Invalid file path.");
            }

            String fileName = UUID.randomUUID().toString() + "_" + originalFileName;
            Path filePath = rootLocation.resolve(type).resolve(fileName).normalize();

            Files.createDirectories(filePath.getParent());
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            return fileName;
        } catch (IOException exception) {
            throw new UploadException("Failed to store file.", exception);
        }
    }

    public List<String> loadAll(String type) throws IOException {
        Path directory = rootLocation.resolve(type);
        if (!Files.exists(directory) || !Files.isDirectory(directory)) {
            throw new IOException("Directory not found: " + directory);
        }
        try {
            Stream<Path> stream = Files.walk(directory, 1);
            return stream
                    .filter(path -> !path.equals(directory))
                    .map(directory::relativize)
                    .map(Path::toString)
                    .collect(Collectors.toList());
        } catch (IOException exception) {
            throw new UploadException("Failed to read stored files.", exception);
        }
    }

    @Override
    public Path load(String filename, String type) {
        return rootLocation.resolve(type).resolve(filename).normalize();
    }

    public Resource loadAsResource(String filename, String type) {
        try {
            Path filePath = load(filename, type);
            Resource resource = new UrlResource(filePath.toUri());

            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new UploadException("Could not read file: " + filename);
            }
        } catch (MalformedURLException exception) {
            throw new UploadException("Could not read file: " + filename, exception);
        }
    }

    public void deleteByFileName(String filename, String type) {
        try {
            Path filePath = load(filename, type);
            Files.deleteIfExists(filePath);
        } catch (IOException exception) {
            throw new UploadException("Failed to delete file: " + filename, exception);
        }
    }

    @Override
    public void init() {
        try {
            Files.createDirectories(rootLocation);
        } catch (IOException exception) {
            throw new UploadException("Could not initialize storage.", exception);
        }
    }
}
