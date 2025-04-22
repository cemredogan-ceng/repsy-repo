package com.repsy.controller;

import com.repsy.service.UploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/")
public class UploadController {

    private final UploadService uploadService;

    @PostMapping("/{packageName}/{version}")
    public ResponseEntity<String> upload(
            @PathVariable String packageName,
            @PathVariable String version,
            @RequestParam("package") MultipartFile packageFile,
            @RequestParam("meta") MultipartFile metaFile
    ) {
        try {
            uploadService.uploadPackage(metaFile, packageFile);
            return ResponseEntity.ok("✅ Upload successful!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("❌ Upload failed: " + e.getMessage());
        }
    }

    @GetMapping("/{packageName}/{version}/{fileName}")
    public ResponseEntity<Resource> downloadFile(
            @PathVariable String packageName,
            @PathVariable String version,
            @PathVariable String fileName
    ) {
        try {
            Resource file = uploadService.loadFile(packageName, version, fileName);
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
                    .body(file);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}


