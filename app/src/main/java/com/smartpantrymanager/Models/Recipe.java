package com.smartpantrymanager.Models;

public class Recipe {

    private String name;
    private String imageName;
    private int ingredientsAvailable;
    private int totalIngredients;

    public Recipe(String name, String imageName,
                  int ingredientsAvailable,
                  int totalIngredients) {

        this.name = name;
        this.imageName = imageName;
        this.ingredientsAvailable = ingredientsAvailable;
        this.totalIngredients = totalIngredients;
    }

    public String getName() {
        return name;
    }

    public String getImageName() {
        return imageName;
    }

    public int getIngredientsAvailable() {
        return ingredientsAvailable;
    }

    public int getTotalIngredients() {
        return totalIngredients;
    }
}
