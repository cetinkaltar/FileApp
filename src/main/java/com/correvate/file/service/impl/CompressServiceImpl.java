package com.correvate.file.service.impl;

import com.correvate.file.service.CompressService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Service
@Slf4j
public class CompressServiceImpl implements CompressService {
    private static final String ZIP_FILE_NAME = "response.zip";

    public File compressFilesToZip(MultipartFile[] files) throws Exception {
        final String absolutePath = Paths.get(System.getProperty("java.io.tmpdir")).toAbsolutePath().toString();
        try (FileOutputStream fos = new FileOutputStream(absolutePath + "/" + ZIP_FILE_NAME);
             ZipOutputStream zipOut = new ZipOutputStream(fos);) {

            for (MultipartFile srcFile : files) {
                InputStream fis = srcFile.getInputStream();
                ZipEntry zipEntry = new ZipEntry(srcFile.getOriginalFilename());
                zipOut.putNextEntry(zipEntry);

                byte[] bytes = new byte[1024];
                int length;
                while ((length = fis.read(bytes)) >= 0) {
                    zipOut.write(bytes, 0, length);
                }
                fis.close();
            }

            zipOut.close();
            fos.close();

            return new File(ZIP_FILE_NAME);
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }

    }
}
