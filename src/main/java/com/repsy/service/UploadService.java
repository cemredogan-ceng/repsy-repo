package com.repsy.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.repsy.model.MetaData;
import com.repsy.model.PackageEntity;
import com.repsy.repository.PackageRepository;
import com.repsy.storage.StorageStrategy;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UploadService {

    private final PackageRepository packageRepository;
    private final ObjectMapper objectMapper;
    private final StorageStrategy storageStrategy;

    public void uploadPackage(MultipartFile metaFile, MultipartFile contentFile) throws IOException {
        MetaData metaData = objectMapper.readValue(metaFile.getBytes(), MetaData.class);

        PackageEntity entity = new PackageEntity();
        entity.setName(metaData.getName());
        entity.setVersion(metaData.getVersion());
        entity.setAuthor(metaData.getAuthor());
        entity.setUploadDate(LocalDateTime.now());

        packageRepository.save(entity);

        storageStrategy.saveFile(metaData.getName(), metaData.getVersion(), contentFile);
    }

    public Resource loadFile(String packageName, String version, String fileName) throws IOException {
        return storageStrategy.loadFile(packageName, version, fileName);
    }
}


