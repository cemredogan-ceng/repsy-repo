package com.repsy.storage;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;


import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileSystemStorage implements StorageStrategy {

    private final Path root = Paths.get("uploads");

    @Override
    public void saveFile(String packageName, String version, MultipartFile file) throws IOException {
        Path targetDir = root.resolve(packageName).resolve(version);
        Files.createDirectories(targetDir);
        Path targetFile = targetDir.resolve(file.getOriginalFilename());
        file.transferTo(targetFile);
    }

    @Override
    public Resource loadFile(String packageName, String version, String fileName) throws IOException {
        Path filePath = root.resolve(packageName).resolve(version).resolve(fileName);
        if (!Files.exists(filePath)) {
            throw new IOException("File not found: " + filePath);
        }
        return new UrlResource(filePath.toUri());
    }
}

