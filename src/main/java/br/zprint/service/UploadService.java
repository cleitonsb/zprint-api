package br.zprint.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class UploadService {

    @Autowired
    private Environment env;

    public String uploadFile(MultipartFile file, Long usuarioId) {
        try{
            Path storageLocation = Paths.get(env.getProperty("file.upload-dir")).toAbsolutePath().normalize();

            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            Path targetLocation = storageLocation.resolve(fileName);

            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
        }catch (Exception ex){
            ex.printStackTrace();
        }

        return "success";
    }
}
