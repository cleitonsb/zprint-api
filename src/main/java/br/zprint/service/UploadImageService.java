package br.zprint.service;

import br.zprint.error.exception.DocumentStorageException;
import br.zprint.model.Usuario;
import org.apache.tika.Tika;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class UploadImageService {

    @Autowired
    private Environment env;

    public String uploadFile(MultipartFile file, Usuario usuario) {
        try {

            Path storageLocation = Paths.get(env.getProperty("file.upload-dir")).toAbsolutePath().normalize();;

            if(!file.isEmpty()) {
                String type = (new Tika()).detect(file.getBytes());
                if (!"image/jpeg".equals(type) && !"image/png".equals(type)) {
                    throw new Exception("Formato não permitido para o avatar");
                }

                BufferedImage bImage = ImageIO.read(file.getInputStream());

                BufferedImage bImageOut = new BufferedImage(225, 225, bImage.getType());

                Graphics2D g2d = bImageOut.createGraphics();
                g2d.drawImage(bImage, 0, 0, 225, 225, null);
                g2d.dispose();

                String fileName = usuario.getId() + ".jpg";
                Path targetLocation = storageLocation.resolve(fileName);

                ImageIO.write(bImageOut, "JPG", new File(targetLocation.toString()));

                return env.getProperty("server.endereco") + "/usuario/avatar/" + usuario.getId() + ".jpg";
            }

        }catch (IOException ex){
            ex.printStackTrace();
        }catch (Exception ex){
            ex.printStackTrace();
        }

        return "";
    }

    public Resource getFile(String nomeArquivo) throws Exception {
        try {
            Path storageLocation = Paths.get(env.getProperty("file.upload-dir")).toAbsolutePath().normalize();;
            Path filePath = storageLocation.resolve(nomeArquivo).normalize();
            Resource resource = new UrlResource(filePath.toUri());

            if (resource.exists()) {
                return resource;
            } else {
                throw new FileNotFoundException("Arquivo não encontrado: " + nomeArquivo);
            }
        }catch (MalformedURLException ex){
            throw new FileNotFoundException("Arquivo não encontrado: " + nomeArquivo);
        }
    }
}
