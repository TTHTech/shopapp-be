package com.project.shopapp.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "file")
public class FileStorageProperties {
    private String uploadLogoDir;

    // Getter and Setter
    public String getUploadLogoDir() {
        return uploadLogoDir;
    }

    public void setUploadLogoDir(String uploadLogoDir) {
        this.uploadLogoDir = uploadLogoDir;
    }
}
