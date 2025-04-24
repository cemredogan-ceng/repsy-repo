package com.repsy.storage;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Component
public class ObjectStorage implements StorageStrategy {

    @Override
    public void saveFile(String packageName, String version, MultipartFile file) throws IOException {
        System.out.println("📦 [MOCK] ObjectStorage: Saving file - " + file.getOriginalFilename());
        // Gerçek bir nesne deposu entegrasyonu yerine sadece log yazıyoruz.
    }

    @Override
    public Resource loadFile(String packageName, String version, String fileName) throws IOException {
        System.out.println("📥 [MOCK] ObjectStorage: Loading file - " + fileName);
        return new ByteArrayResource("mock data".getBytes()); // Sahte içerik
    }
}
