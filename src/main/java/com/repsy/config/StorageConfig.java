package com.repsy.config;

import com.repsy.storage.FileSystemStorage;
import com.repsy.storage.ObjectStorage;
import com.repsy.storage.StorageStrategy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StorageConfig {

    @Value("${storage.strategy}")
    private String strategy;

    @Bean
    public StorageStrategy storageStrategy(FileSystemStorage fs, ObjectStorage os) {
        return strategy.equalsIgnoreCase("object") ? os : fs;
    }
}
