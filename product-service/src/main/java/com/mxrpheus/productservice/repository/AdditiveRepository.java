package com.mxrpheus.productservice.repository;

import com.mxrpheus.productservice.model.Additive;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdditiveRepository extends JpaRepository<Additive, Long> {
}
