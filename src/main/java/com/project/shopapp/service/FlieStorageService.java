package com.project.shopapp.service;


import com.project.shopapp.config.FileStorageProperties;
import com.project.shopapp.exception.FileNotFoundException;
import com.project.shopapp.exception.FileStorageException;
import org.apache.tomcat.util.file.ConfigurationSource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.*;
import java.util.UUID;

@Service
public class FlieStorageService {
    private final Path fileLogoStorageLocation;

    public FlieStorageService(FileStorageProperties fileStorageProperties) {
        String uploadDir = fileStorageProperties.getUploadLogoDir();
        if (uploadDir == null || uploadDir.isEmpty()) {
            throw new FileStorageException("Upload directory not configured properly.");
        }
        System.out.println("Upload directory: " + uploadDir); // Thêm dòng này để kiểm tra
        this.fileLogoStorageLocation = Paths.get(uploadDir).toAbsolutePath().normalize();
        try {
            Files.createDirectories(this.fileLogoStorageLocation);
        } catch (Exception ex) {
            throw new FileStorageException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }

    public String storeLogoFile(MultipartFile file){
        return storeFile(fileLogoStorageLocation, file);
    }
    private String storeFile(Path location, MultipartFile file){
        UUID uuid = UUID.randomUUID();
        String ext = StringUtils.getFilenameExtension(file.getOriginalFilename());
        String filename = uuid.toString() + "." + ext;
        try{
            if(filename.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + filename);
            }
            Path targetLocation = location.resolve(filename);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            return filename;
        }catch(Exception ex){
            throw new FileStorageException("Could not store file " + filename + ". Please try again!", ex);
        }
    }
    public Resource loadLogoFileAsResource(String filename) {
        return loadFileAsResource(fileLogoStorageLocation, filename);
    }
    private Resource loadFileAsResource(Path location, String filename) {
        try {
            Path filePath = location.resolve(filename).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if(resource.exists()) {
                return resource;
            } else {
                throw new FileNotFoundException("File not found " + filename);
            }
        } catch (Exception exception) {
            throw new FileNotFoundException("File not found " + filename, exception);
        }
    }
    public void deleteLogoFile(String filename) {
        deleteFile(fileLogoStorageLocation, filename);
    }
    private void deleteFile(Path location, String filename) {
        try {
            Path filePath = location.resolve(filename).normalize();
            if (!Files.exists(filePath)){
                throw new FileNotFoundException("File not found " + filename);
            }
            Files.delete(filePath);
        } catch (Exception exception) {
            throw new FileNotFoundException("File not found " + filename, exception);
        }
    }
}
