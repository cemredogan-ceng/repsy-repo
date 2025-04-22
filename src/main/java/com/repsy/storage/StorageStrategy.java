package com.repsy.storage;

import java.io.IOException;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface StorageStrategy {
    void saveFile(String packageName, String version, MultipartFile file) throws IOException;
    Resource loadFile(String packageName, String version, String fileName) throws IOException;
}


