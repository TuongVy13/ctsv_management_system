package com.ctsv.backend.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
@Service
public class FileUploadService {
    @Value("${app.upload.dir}")
    private String uploadDir;

    public String storeFile(MultipartFile file, String targetFileName ) throws IOException {
        Path uploadPath = Paths.get(uploadDir);
        String originalFilename= StringUtils.cleanPath(file.getOriginalFilename());
        String fileExtension="";
        if(originalFilename.contains(".")){
            fileExtension=originalFilename.substring(originalFilename.lastIndexOf("."));
        }else {
            fileExtension =".jpg";
        }
        String customFilename=targetFileName + fileExtension;
        Path targetLocation=uploadPath.resolve(customFilename);
        Files.copy(file.getInputStream(),targetLocation, StandardCopyOption.REPLACE_EXISTING);
        return customFilename;
    }
    public void deleteFile(String filename){
        if(filename==null||filename.isEmpty()) return;
        try{
            Path uploadPath=Paths.get(uploadDir);
            Path fileToDelete=uploadPath.resolve(filename);
            Files.deleteIfExists(fileToDelete);
        }catch (IOException e) {
            System.err.println("Không thể xóa file vật lý: " + filename + ". Lỗi: " + e.getMessage());
        }
    }
}
