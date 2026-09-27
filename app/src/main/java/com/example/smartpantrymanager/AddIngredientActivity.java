package com.example.smartpantrymanager;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class RecipeDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

        TextView tvDetailName = findViewById(R.id.tvDetailName);
        TextView tvIngredients = findViewById(R.id.tvIngredients);
        TextView tvSteps = findViewById(R.id.tvSteps);

        // Example dynamic content
        tvDetailName.setText("Spaghetti Bolognese");
        tvIngredients.setText("• Pasta\n• Minced beef\n• Tomato sauce");
        tvSteps.setText("1. Boil pasta\n2. Cook beef\n3. Mix with sauce");
    }
}
