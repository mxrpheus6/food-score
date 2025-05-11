package com.mxrpheus.productservice.model;

import com.mxrpheus.productservice.model.enums.ProductType;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "product", indexes = @Index(columnList = "barcode", unique = true))
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "barcode", nullable = false, unique = true)
    private String barcode;

    @Column(name = "product_name", nullable = false)
    private String productName;

    @ManyToOne
    @JoinColumn(name = "brand_id", nullable = false)
    private Brand brand;

    @Column(name = "quantity", nullable = false)
    private String quantity;

    @Column(name = "packaging", nullable = false)
    private String packaging;

    @Column(name = "origin", nullable = false)
    private String origin;

    @Column(name = "ingredients_text", nullable = false)
    private String ingredientsText;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductIngredient> parsedIngredients;

    @Column(name = "is_palm_oil_used", nullable = false)
    private Boolean isPalmOilUsed;

    @Embedded
    private Nutriments nutriments;

    @Enumerated(EnumType.STRING)
    @Column(name = "product_type", nullable = false)
    private ProductType productType;

    @Column(name = "nutri_score")
    private String nutriScore;
}
