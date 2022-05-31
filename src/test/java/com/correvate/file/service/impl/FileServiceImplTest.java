package com.correvate.file.service.impl;

import com.correvate.file.service.CompressService;
import com.correvate.file.service.reference.CompressType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.io.Resource;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@ExtendWith(MockitoExtension.class)
class FileServiceImplTest {
    @Mock
    CompressService compressService;

    @InjectMocks
    FileServiceImpl fileServiceImpl;

    @Test
    public void processFiles() throws Exception {
        //given
        MockMultipartFile firstFile = new MockMultipartFile("data", "filename.txt", "text/plain", "some xml".getBytes());
        MockMultipartFile secondFile = new MockMultipartFile("data", "other-file-name.data", "text/plain", "some other type".getBytes());
        MultipartFile files[] = {firstFile, secondFile};
        File testFile = new File("testFile");
        Mockito.when(compressService.compressFilesToZip(files)).thenReturn(testFile);

        //when
        Resource resource = fileServiceImpl.processFiles(CompressType.ZIP, files);

        //then
        Mockito.verify(compressService, Mockito.times(1)).compressFilesToZip(files);
    }
}