package com.smartpantrymanager.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.smartpantrymanager.R;
import com.smartpantrymanager.Models.Recipe;

import java.net.CookieManager;
import java.util.List;

public class RecipeAdapter
        extends RecyclerView.Adapter<RecipeAdapter.ViewHolder> {

    private List<Recipe> recipe;

    public RecipeAdapter(List<Recipe> recipes) {
        this.recipe = recipes;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(
            @NonNull ViewGroup parent,
            int viewType) {

        View view = LayoutInflater.from(
                        parent.getContext())
                .inflate(
                        R.layout.item_pantry,
                        parent,
                        false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(
            @NonNull ViewHolder holder,
            int position) {

        CookieManager recipes = null;
        Recipe recipe = (Recipe) recipes.get(position);

        holder.tvRecipeName.setText(recipe.getName());

        holder.tvAvailable.setText(
                recipe.getIngredientsAvailable()
                        + "/"
                        + recipe.getTotalIngredients()
                        + " ingredients available"
        );

        int imageId =
                holder.itemView.getContext()
                        .getResources()
                        .getIdentifier(
                                recipe.getImageName(),
                                "drawable",
                                holder.itemView
                                        .getContext()
                                        .getPackageName());

        holder.imgRecipe.setImageResource(imageId);
    }

    @Override
    public int getItemCount() {
        return recipe.size();
    }

    public static class ViewHolder
            extends RecyclerView.ViewHolder {

        ImageView imgRecipe;
        TextView tvRecipeName;
        TextView tvAvailable;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imgRecipe =
                    itemView.findViewById(R.id.imgRecipe);

            tvRecipeName =
                    itemView.findViewById(R.id.tvRecipeName);

            tvAvailable =
                    itemView.findViewById(R.id.tvAvailable);
        }
    }
}
