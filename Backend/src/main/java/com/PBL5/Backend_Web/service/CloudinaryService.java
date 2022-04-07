package com.PBL5.Backend_Web.service;

import com.PBL5.Backend_Web.persistence.ResultResponse;
import org.springframework.web.multipart.MultipartFile;

public interface CloudinaryService {
    ResultResponse UploadImage(MultipartFile file, String folder);
    boolean DeleteImage(String folder,String url);
}
