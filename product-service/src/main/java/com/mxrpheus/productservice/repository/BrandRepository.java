package com.mxrpheus.productservice.repository;

import com.mxrpheus.productservice.model.Brand;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {
    Page<Brand> findAll(Pageable pageable);
    Boolean existsByName(String name);
}
