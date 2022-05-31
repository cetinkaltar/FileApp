package com.correvate.file.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;

public interface CompressService {
    File compressFilesToZip(MultipartFile[] files) throws Exception;
}
