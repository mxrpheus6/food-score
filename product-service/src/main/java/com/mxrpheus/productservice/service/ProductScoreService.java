package com.mxrpheus.productservice.service;

import com.mxrpheus.productservice.model.Nutriments;
import com.mxrpheus.productservice.model.enums.ProductType;
import org.springframework.stereotype.Service;

@Service
public class ProductScoreService {

    public int calculateFoodScore(Nutriments n, ProductType productType) {
        int negative = getEnergyPoints(n) +
                getSugarPoints(n) +
                getSatFatPoints(n) +
                getSaltPoints(n);

        int positive = getProteinPoints(n) +
                getFiberPoints(n);

        if (productType == ProductType.FRUIT || productType == ProductType.VEGETABLE) {
            positive += 2;
        }

        return negative - positive;
    }

    public String getFoodScoreGrade(int score, ProductType type) {
        return switch (type) {
            case BEVERAGE -> {
                if (score <= 1) yield "A";
                else if (score <= 5) yield "B";
                else if (score <= 9) yield "C";
                else if (score <= 13) yield "D";
                else yield "E";
            }
            case CHEESE, GENERAL, FRUIT, VEGETABLE, DAIRY, BAKERY -> {
                if (score < 0) yield "A";
                else if (score < 3) yield "B";
                else if (score < 11) yield "C";
                else if (score < 19) yield "D";
                else yield "E";
            }
            case MEAT -> {
                if (score < 0) yield "A";
                else if (score < 4) yield "B";
                else if (score < 9) yield "C";
                else if (score < 16) yield "D";
                else yield "E";
            }
            case SNACK -> {
                if (score < 5) yield "A";
                else if (score < 8) yield "B";
                else if (score < 15) yield "C";
                else if (score < 22) yield "D";
                else yield "E";
            }
        };
    }

    private int getEnergyPoints(Nutriments n) {
        if (n.getEnergyKcal100g() == null) return 0;
        int kj = (int) Math.round(n.getEnergyKcal100g() * 4.184);
        return getPoints(kj, new int[]{335, 670, 1005, 1340, 1675, 2010, 2345, 2680, 3015, 3350});
    }

    private int getSugarPoints(Nutriments n) {
        if (n.getSugars100g() == null) return 0;
        return getPoints(n.getSugars100g().doubleValue(), new double[]{4.5, 9, 13.5, 18, 22.5, 27, 31, 36, 40, 45});
    }

    private int getSatFatPoints(Nutriments n) {
        if (n.getSaturatedFat100g() == null) return 0;
        return getPoints(n.getSaturatedFat100g().doubleValue(), new double[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10});
    }

    private int getSaltPoints(Nutriments n) {
        if (n.getSalt100g() == null) return 0;
        return getPoints(n.getSalt100g().doubleValue(), new double[]{0.09, 0.18, 0.27, 0.36, 0.45, 0.54, 0.63, 0.72, 0.81, 0.9});
    }

    private int getFiberPoints(Nutriments n) {
        if (n.getFiber100g() == null) return 0;
        return getPoints(n.getFiber100g().doubleValue(), new double[]{0.9, 1.9, 2.8, 3.7, 4.7});
    }

    private int getProteinPoints(Nutriments n) {
        if (n.getProteins100g() == null) return 0;
        return getPoints(n.getProteins100g().doubleValue(), new double[]{1.6, 3.2, 4.8, 6.4, 8.0});
    }

    private int getPoints(double value, double[] borders) {
        for (int i = borders.length - 1; i >= 0; i--) {
            if (value > borders[i]) return i + 1;
        }
        return 0;
    }

    private int getPoints(int value, int[] borders) {
        for (int i = borders.length - 1; i >= 0; i--) {
            if (value > borders[i]) return i + 1;
        }
        return 0;
    }
}
