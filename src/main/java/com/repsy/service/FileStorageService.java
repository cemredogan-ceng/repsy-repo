package com.repsy.service;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileStorageService {

    private final Path root = Paths.get("uploads");

    public Resource loadFile(String packageName, String version, String fileName) {
        try {
            Path filePath = root.resolve(packageName).resolve(version).resolve(fileName);
            if (!Files.exists(filePath)) {
                throw new RuntimeException("❌ Dosya bulunamadı: " + filePath);
            }
            return new UrlResource(filePath.toUri());
        } catch (MalformedURLException e) {
            throw new RuntimeException("❌ Dosya yolu hatalı: " + fileName, e);
        } catch (Exception e) {
            throw new RuntimeException("❌ Dosya yüklenemedi: " + fileName, e);
        }
    }
}


