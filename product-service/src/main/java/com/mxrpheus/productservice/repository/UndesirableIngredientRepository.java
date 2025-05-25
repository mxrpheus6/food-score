package com.mxrpheus.productservice.repository;

import com.mxrpheus.productservice.model.UndesirableIngredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UndesirableIngredientRepository extends JpaRepository<UndesirableIngredient, Long> {
}
