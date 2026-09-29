package com.smartpantrymanager.Models;

/** One ingredient line of a recipe, e.g. "eggs", 3, "pcs". */
class RecipeMatch {
    public final String name;
    public final double quantity;
    public final String unit;

    public RecipeMatch(String name, double quantity, String unit) {
        this.name = name;
        this.quantity = quantity;
        this.unit = unit;
    }
}
