package com.correvate.file.controller;

import com.correvate.file.service.FileService;
import com.correvate.file.service.reference.CompressType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/compress")
public class CompressController {
    private final FileService fileService;

    @PostMapping()
    public ResponseEntity<?> compressUploadedFiles(@RequestParam CompressType compressType, @RequestParam MultipartFile[] files) throws Exception {
        return ResponseEntity.ok(fileService.processFiles(compressType, files));
    }
}
