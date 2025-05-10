package com.mxrpheus.productservice.model.enums;

//TODO: это так оставлять нельзя : )
public enum IngredientType {
    BASE,               // основной ингредиент
    ADDITIVE,           // пищевая добавка (E-коды)
    PRESERVATIVE,       // консервант
    SWEETENER,          // подсластитель
    FLAVORING,          // ароматизатор
    COLORANT,           // краситель
    EMULSIFIER,         // эмульгатор
    STABILIZER,         // стабилизатор
    PALM_OIL,           // маркерная категория для пальмового масла
    ALLERGEN,           // аллерген
    GLUTEN,             // глютен
    CANCER_CAUSING,     // канцерогенные ингредиенты
    GIT_PROBLEM,        // ингредиенты, вызывающие проблемы с ЖКТ
    SUSPICIOUS,         // ингредиенты с подозрительной репутацией
    BANNED_IN_SOME_COUNTRIES,
    OTHER               // если не подходит ни одна категория
}