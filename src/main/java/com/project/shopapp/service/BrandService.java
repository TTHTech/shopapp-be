package com.project.shopapp.service;

import com.project.shopapp.domain.Brand;
import com.project.shopapp.dto.BrandDto;
import com.project.shopapp.exception.BrandException;
import com.project.shopapp.exception.ExceptionResponse;
import com.project.shopapp.repository.BrandRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandService {
    @Autowired
    private BrandRepository brandRepository;
    @Autowired
    private FlieStorageService flieStorageService;

    public Brand insertBrand(BrandDto dto) {
        List<?> foundedList = brandRepository.findByNameContainsIgnoreCase(dto.getName());
        if(foundedList.size() > 0) {
            throw new BrandException("Brand already exists");
        }
        Brand entity = new Brand();
        BeanUtils.copyProperties(dto, entity);
        if(dto.getLogoFile() != null) {
            String filename = flieStorageService.storeLogoFile(dto.getLogoFile());
            entity.setLogo(filename);
            dto.setLogoFile(null);
        }
        return brandRepository.save(entity);
    }
}
