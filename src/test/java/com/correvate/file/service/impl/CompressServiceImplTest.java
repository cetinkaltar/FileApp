package com.correvate.file.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class CompressServiceImplTest {

    @InjectMocks
    CompressServiceImpl compressServiceImpl;

    @Test
    public void compressFilesToZip() throws Exception {
        //given
        MockMultipartFile firstFile = new MockMultipartFile("data", "filename.txt", "text/plain", "some xml".getBytes());
        MockMultipartFile secondFile = new MockMultipartFile("data", "other-file-name.data", "text/plain", "some other type".getBytes());
        MultipartFile files[] = {firstFile, secondFile};

        //when
        File result = compressServiceImpl.compressFilesToZip(files);

        //then
        assertEquals("response.zip", result.getName());
    }
}