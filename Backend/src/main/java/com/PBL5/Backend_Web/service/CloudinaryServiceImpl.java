package com.PBL5.Backend_Web.service;

import com.PBL5.Backend_Web.persistence.ResponseObject;
import com.PBL5.Backend_Web.persistence.ResultResponse;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

@Service
@Slf4j
public class CloudinaryServiceImpl implements CloudinaryService {
    @Autowired
    private Cloudinary cloudinary;


    private boolean isImageFile(MultipartFile file){
        String fileExtension = FilenameUtils.getExtension(file.getOriginalFilename());
        return Arrays.asList(new String[] {"png","jpg","jpeg","bmp"}).contains(fileExtension.trim().toLowerCase());
    }

    private boolean isSizelessThan10Mb(MultipartFile file)
    {
        float fileSizeInMegaBytes = file.getSize()/1_000_000;
        if(fileSizeInMegaBytes > 10.0f) return false;
        return true;
    }

    @Override
    public ResultResponse UploadImage(MultipartFile file, String folder) {
        try {
            if(file.isEmpty())
                return new ResultResponse(ResponseObject.FAIRLURE,"file empty",HttpStatus.NOT_FOUND);
            if (!isImageFile(file)) return new ResultResponse(ResponseObject.FAIRLURE,"file not valid png,jpg,jpeg,bmp", HttpStatus.NOT_FOUND);
            if(!isSizelessThan10Mb(file)) return new ResultResponse(ResponseObject.FAIRLURE,"file size more than 10Mb",HttpStatus.NOT_IMPLEMENTED);
            Map tmp = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.asMap("resource_type", "auto", "folder", folder + "/"));
            String img = (String) tmp.get("secure_url");
            return new ResultResponse(ResponseObject.SUCCESS,img,HttpStatus.OK);
        } catch (IOException e) {
            return new ResultResponse(ResponseObject.FAIRLURE,"error IO",HttpStatus.NOT_IMPLEMENTED);
        }
    }

    @Override
    public boolean DeleteImage(String folder, String url) {
        String [] strings = url.split("/");
        url = strings[strings.length-1];
        String [] strings1 = url.split("\\W");
        url = strings1[0];
        url = folder+"/"+url;
        try {
            Map tmp = cloudinary.uploader().destroy(url,ObjectUtils.asMap( "invalidate", true));
            String rs = (String) tmp.get("result");
            if(!rs.equals("ok"))
                return false;
            return true;
        } catch (IOException e) {
            return false;
        }

    }


}
