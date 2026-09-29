package com.smartpantrymanager;

import java.util.HashMap;
import java.util.Map;

/** Picks a drawable for an ingredient or recipe from its name. */
public class AddIngredientActivity {
    private static final Map<String, Integer> INGREDIENTS = new HashMap<>();
    private static final Map<String, Integer> RECIPES = new HashMap<>();

    static {
        INGREDIENTS.put("tomato", R.drawable.tomato);
        INGREDIENTS.put("tomatoes", R.drawable.tomato);
        INGREDIENTS.put("milk", R.drawable.milk);
        INGREDIENTS.put("bread", R.drawable.bread);
        INGREDIENTS.put("cheese", R.drawable.cheese);
        INGREDIENTS.put("egg", R.drawable.eggs);
        INGREDIENTS.put("eggs", R.drawable.eggs);

        RECIPES.put("omelette", R.drawable.omelette);
        RECIPES.put("tomato salad", R.drawable.salad);
        RECIPES.put("french toast", R.drawable.frechntoast); // matches your filename
        RECIPES.put("pancakes", R.drawable.pancakes);
    }

    public static int forIngredient(String name) {
        Integer res = name == null ? null : INGREDIENTS.get(name.trim().toLowerCase());
        return res != null ? res : R.drawable.food_placeholder;
    }

    public static int forRecipe(String name) {
        Integer res = name == null ? null : RECIPES.get(name.trim().toLowerCase());
        return res != null ? res : R.drawable.pancakes;
    }
}

