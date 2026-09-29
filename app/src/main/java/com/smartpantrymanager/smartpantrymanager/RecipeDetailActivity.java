package com.smartpantrymanager.smartpantrymanager;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.smartpantrymanager.Models.Ingredients;
import com.smartpantrymanager.R;
import com.smartpantrymanager.adapters.PantryAdapter;

public class RecipeDetailActivity extends AppCompatActivity {

    private TextView tvRecipeName;
    private TextView tvIngredients;
    private TextView tvSteps;

    @SuppressLint("MissingInflatedId")
    @Override
    protected <DbHelper> void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

        tvRecipeName = findViewById(R.id.tvRecipeName);
        tvIngredients = findViewById(R.id.tvIngredients);
        tvSteps = findViewById(R.id.tvSteps);

        long id = getIntent().getLongExtra("recipe_id", -1);

        DbHelper dbHelper = new DbHelper(this);
        PantryAdapter.Recipe recipe = dbHelper.getRecipe(id);

        if (recipe == null) {
            finish();
            return;
        }

        tvRecipeName.setText(recipe.getName());

        StringBuilder ingredientsText = new StringBuilder();

        if (recipe.getIngredients() != null) {
            for (Ingredients ingredient : recipe.getIngredients()) {
                ingredientsText.append("• ")
                        .append(ingredient.getQty())
                        .append(" ")
                        .append(ingredient.getUnit())
                        .append(" ")
                        .append(ingredient.getName())
                        .append("\n");
            }
        }

        tvIngredients.setText(ingredientsText.toString().trim());
        tvSteps.setText(recipe.getSteps());
    }
}