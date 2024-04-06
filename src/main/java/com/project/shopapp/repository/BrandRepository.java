package com.project.shopapp.repository;

import com.project.shopapp.domain.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BrandRepository extends JpaRepository<Brand, Long> {

  List<Brand> findByNameContainsIgnoreCase(String name);

  List<Brand> findByIdNotAndNameContainsIgnoreCase(Long id, String name);
}