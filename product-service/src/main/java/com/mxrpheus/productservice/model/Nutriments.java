package com.mxrpheus.productservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Embeddable
public class Nutriments {
    @Column(name = "energy_kcal_100g")
    private Integer energyKcal100g;

    @Column(name = "proteins_100g", precision = 10, scale = 2)
    private BigDecimal proteins100g;

    @Column(name = "fat_100g", precision = 10, scale = 2)
    private BigDecimal fat100g;

    @Column(name = "sugars_100g", precision = 10, scale = 2)
    private BigDecimal sugars100g;

    @Column(name = "saturated_fat_100g ", precision = 10, scale = 2)
    private BigDecimal saturatedFat100g;

    @Column(name = "fiber_100g", precision = 10, scale = 2)
    private BigDecimal fiber100g;

    @Column(name = "salt_100g", precision = 10, scale = 2)
    private BigDecimal salt100g;
}
