package com.repsy.repsyrepo.controller;

import com.repsy.service.UploadService;
import lombok.RequiredArgsConstructor;
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
            uploadService.uploadPackage(metaFile);
            return ResponseEntity.ok("Upload successful ✅");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("❌ Upload failed: " + e.getMessage());
        }
    }
}
