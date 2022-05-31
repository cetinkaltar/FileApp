package com.correvate.file.service.impl;

import com.correvate.file.service.CompressService;
import com.correvate.file.service.FileService;
import com.correvate.file.service.reference.CompressType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@Slf4j
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {
    private final CompressService compressService;

    @Override
    public Resource processFiles(CompressType compressType, MultipartFile[] files) throws Exception {
        File zippedFile = compressService.compressFilesToZip(files);

        return getFileAsResource(zippedFile.getName());
    }

    private Resource getFileAsResource(String fileName) throws Exception {
        final String absolutePath = Paths.get(System.getProperty("java.io.tmpdir")).toAbsolutePath().toString();
        Path filePath = Paths.get(absolutePath, fileName).toAbsolutePath().normalize();
        try {
            Resource resource = new UrlResource(filePath.toUri());
            return resource;
        } catch (Exception ex) {
            log.error("Error while creating resource for file " + filePath);
            throw new Exception(ex.getMessage());
        }
    }
}
