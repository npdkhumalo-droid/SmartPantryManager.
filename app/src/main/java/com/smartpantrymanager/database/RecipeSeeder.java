package com.smartpantrymanager.database;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

/** Pre-loads the recipe collection. Ingredient format: "name:quantity:unit". */
final class RecipeSeeder {
    private RecipeSeeder() {}

    // Salt, pepper, water and cooking oil are assumed to be always available and are not listed.
    private static final String[][] RECIPES = {
            {"Omelette", "Beat the eggs with the milk.|Melt a little butter or oil in a pan.|Pour in the eggs and cook until nearly set.|Add the cheese, fold in half and serve.",
                    "eggs:3:pcs", "milk:50:ml", "cheese:30:g"},
            {"Scrambled Eggs", "Whisk the eggs with the milk.|Melt the butter in a pan on low heat.|Stir the eggs gently until softly set.|Serve straight away.",
                    "eggs:3:pcs", "milk:30:ml", "butter:10:g"},
            {"French Toast", "Whisk the eggs and milk in a shallow dish.|Dip each slice of bread on both sides.|Fry in a hot pan until golden, about 2 minutes per side.|Serve warm.",
                    "bread:4:slices", "eggs:2:pcs", "milk:100:ml"},
            {"Pancakes", "Whisk the flour, milk and eggs into a smooth batter.|Rest the batter for 10 minutes.|Pour a ladle into a hot greased pan.|Flip when bubbles appear and cook until golden.",
                    "flour:200:g", "milk:300:ml", "eggs:2:pcs"},
            {"Tomato Salad", "Slice the tomatoes, cucumber and onion.|Combine in a bowl.|Drizzle with olive oil and season.|Toss and serve.",
                    "tomato:3:pcs", "cucumber:1:pcs", "onion:1:pcs", "olive oil:15:ml"},
            {"Cheese Toastie", "Butter one side of each slice of bread.|Place the cheese between the unbuttered sides.|Toast in a pan until golden on both sides.|Cut in half and serve.",
                    "bread:2:slices", "cheese:40:g", "butter:10:g"},
            {"Egg Sandwich", "Boil or fry the eggs.|Butter the bread.|Slice the tomato.|Layer everything between the bread and serve.",
                    "bread:2:slices", "eggs:2:pcs", "butter:10:g", "tomato:1:pcs"},
            {"Tomato Soup", "Chop the tomatoes, onion and garlic.|Soften the onion and garlic in a pot.|Add the tomatoes and stock and simmer for 20 minutes.|Blend until smooth and season.",
                    "tomato:6:pcs", "onion:1:pcs", "garlic:2:cloves", "vegetable stock:500:ml"},
            {"Spaghetti Pomodoro", "Boil the spaghetti until al dente.|Fry the garlic in olive oil.|Add the chopped tomatoes and simmer for 10 minutes.|Toss with the pasta and serve.",
                    "spaghetti:200:g", "tomato:4:pcs", "garlic:2:cloves", "olive oil:20:ml"},
            {"Mac and Cheese", "Boil the macaroni until tender.|Melt the butter and stir in the milk.|Add the cheese and stir until melted.|Mix in the macaroni and serve.",
                    "macaroni:250:g", "cheese:150:g", "milk:300:ml", "butter:30:g"},
            {"Cheese Quesadilla", "Place a tortilla in a dry pan.|Cover half with cheese and fold over.|Cook until golden on both sides.|Slice and serve.",
                    "tortilla:2:pcs", "cheese:100:g"},
            {"Fried Rice", "Cook the rice and let it cool.|Scramble the eggs in a hot pan and set aside.|Fry the chopped onion, add the rice and soy sauce.|Stir in the eggs and serve.",
                    "rice:200:g", "eggs:2:pcs", "onion:1:pcs", "soy sauce:15:ml"},
            {"Bruschetta", "Toast the bread.|Rub each slice with garlic.|Top with chopped tomato and olive oil.|Season and serve.",
                    "bread:4:slices", "tomato:3:pcs", "garlic:1:cloves", "olive oil:15:ml"},
            {"Banana Milkshake", "Peel and slice the bananas.|Add to a blender with the cold milk.|Blend until smooth.|Pour into glasses.",
                    "banana:2:pcs", "milk:300:ml"},
            {"Chicken Stir Fry", "Slice the chicken, pepper and onion.|Fry the chicken until cooked through.|Add the vegetables and stir fry for 5 minutes.|Add the soy sauce and serve.",
                    "chicken breast:300:g", "bell pepper:1:pcs", "onion:1:pcs", "soy sauce:30:ml"},
            {"Potato Wedges", "Heat the oven to 220 C.|Cut the potatoes into wedges.|Toss with olive oil and season.|Roast for 30 minutes, turning once.",
                    "potato:4:pcs", "olive oil:30:ml"},
            {"Cheesy Mashed Potatoes", "Peel and boil the potatoes until soft.|Drain and mash with the butter and milk.|Stir in the grated cheese.|Season and serve.",
                    "potato:5:pcs", "butter:30:g", "milk:100:ml", "cheese:50:g"},
            {"Greek Salad", "Chop the tomatoes and cucumber.|Add the crumbled feta.|Dress with olive oil.|Toss and serve.",
                    "tomato:3:pcs", "cucumber:1:pcs", "feta cheese:100:g", "olive oil:20:ml"},
            {"Porridge", "Combine the oats and milk in a pot.|Stir over medium heat for 5 minutes.|Pour into a bowl.|Drizzle with honey.",
                    "oats:80:g", "milk:300:ml", "honey:1:tbsp"},
            {"Garlic Bread", "Mix the soft butter with crushed garlic.|Spread over the bread.|Bake at 200 C for 8 minutes.|Serve hot.",
                    "bread:4:slices", "butter:30:g", "garlic:2:cloves"},
    };

    static void seed(SQLiteDatabase db) {
        for (String[] r : RECIPES) {
            ContentValues rv = new ContentValues();
            rv.put("name", r[0]);
            rv.put("steps", r[1].replace("|", "\n"));
            long recipeId = db.insert("recipes", null, rv);
            for (int i = 2; i < r.length; i++) {
                String[] p = r[i].split(":");
                ContentValues iv = new ContentValues();
                iv.put("recipe_id", recipeId);
                iv.put("name", p[0]);
                iv.put("quantity", Double.parseDouble(p[1]));
                iv.put("unit", p[2]);
                db.insert("recipe_ingredients", null, iv);
            }
        }
    }
}

