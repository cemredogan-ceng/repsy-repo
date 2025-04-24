package com.repsy.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.repsy.model.MetaData;
import com.repsy.model.PackageEntity;
import com.repsy.repository.PackageRepository;
import com.repsy.storage.StorageStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.mock.web.MockMultipartFile;

import java.time.LocalDateTime;

import static org.mockito.Mockito.*;

public class UploadServiceTest {

    private PackageRepository packageRepository;
    private ObjectMapper objectMapper;
    private StorageStrategy storageStrategy;
    private UploadService uploadService;

    @BeforeEach
    void setUp() {
        packageRepository = mock(PackageRepository.class);
        objectMapper = new ObjectMapper();
        storageStrategy = mock(StorageStrategy.class);

        uploadService = new UploadService(packageRepository, objectMapper, storageStrategy);
    }

    @Test
    void testUploadPackage_savesEntityAndStoresFile() throws Exception {
        String metaJson = """
            {
                "name": "test-lib",
                "version": "1.0.0",
                "author": "cemre",
                "dependencies": []
            }
        """;
        MockMultipartFile metaFile = new MockMultipartFile("meta", "meta.json", "application/json", metaJson.getBytes());
        MockMultipartFile contentFile = new MockMultipartFile("package", "test-lib.jar", "application/java-archive", "fake-binary".getBytes());

        uploadService.uploadPackage(metaFile, contentFile);

        verify(packageRepository, times(1)).save(any(PackageEntity.class));
        verify(storageStrategy, times(1)).saveFile("test-lib", "1.0.0", contentFile);
    }
}
