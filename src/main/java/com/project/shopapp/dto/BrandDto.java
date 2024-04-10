package com.project.shopapp.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

/**
 * DTO for {@link com.project.shopapp.domain.Brand}
 */

@Data
public class BrandDto implements Serializable {
    Long id;
    String name;
    String logo;
    @JsonIgnore
    private MultipartFile logoFile;
}