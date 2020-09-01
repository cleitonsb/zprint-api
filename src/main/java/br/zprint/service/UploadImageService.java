package br.zprint.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class UploadImageService {

    @Autowired
    private Environment env;

    public String uploadFile(MultipartFile file, Long usuarioId) {
        try {
            Path storageLocation = Paths.get(env.getProperty("file.upload-dir")).toAbsolutePath().normalize();

            BufferedImage bImage = ImageIO.read(file.getInputStream());

            BufferedImage bImageOut = new BufferedImage(225, 225, bImage.getType());

            Graphics2D g2d = bImageOut.createGraphics();
            g2d.drawImage(bImage, 0, 0, 225, 225, null);
            g2d.dispose();

            String fileName = usuarioId + ".jpg";
            Path targetLocation = storageLocation.resolve(fileName);

            ImageIO.write(bImageOut, "JPG", new File(targetLocation.toString()));

        }catch (IOException ex){
            ex.printStackTrace();
        }catch (Exception ex){
            ex.printStackTrace();
        }

        return "success";
    }
}
