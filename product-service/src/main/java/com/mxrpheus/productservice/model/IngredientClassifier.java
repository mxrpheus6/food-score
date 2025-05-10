package com.mxrpheus.productservice.model;

import com.mxrpheus.productservice.model.enums.IngredientType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "ingredient_classifier")
public class IngredientClassifier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "pattern", nullable = false)
    private String pattern;

    @Enumerated(EnumType.STRING)
    @Column(name = "classified_as", nullable = false)
    private IngredientType classifiedAs;
}
