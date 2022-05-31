package com.correvate.file.service;

import com.correvate.file.service.reference.CompressType;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    Resource processFiles(CompressType compressType, MultipartFile[] files) throws Exception;
}
