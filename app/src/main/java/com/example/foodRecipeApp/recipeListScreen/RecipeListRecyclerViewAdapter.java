package com.example.foodRecipeApp.recipeListScreen;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.example.foodRecipeApp.R;
import com.example.foodRecipeApp.recipeListScreen.recipeApiPOJO.Recipe;
import com.example.foodRecipeApp.recipeListScreen.recipeApiPOJO.RecipeModel;

public class RecipeListRecyclerViewAdapter extends RecyclerView.Adapter<RecipeListRecyclerViewAdapter.ViewHolder> {

    private final LayoutInflater layoutInflater;
    private final OnItemClickListener onItemClickListener;
    private final RecipeModel recipeModel;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public RecipeListRecyclerViewAdapter(Context context, OnItemClickListener onItemClickListener, RecipeModel recipeModel) {
        layoutInflater = LayoutInflater.from(context);
        this.onItemClickListener = onItemClickListener;
        this.recipeModel = recipeModel;
    }

    @NonNull
    @Override
    public RecipeListRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.recycler_view_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeListRecyclerViewAdapter.ViewHolder holder, int position) {
        String name,requiredIngredient;
        Recipe recipe  = recipeModel.getHits().get(position).getRecipe();
        name = recipe.getLabel();
        //setting position of view's so it can be used while click event is performed
        holder.position = position;
        requiredIngredient = "Required Ingredient: "+recipe.getIngredientLines().size();
        //food image
        Glide.with(holder.itemView)
                .load(recipe.getImage())
                .transition(DrawableTransitionOptions.withCrossFade())
                .placeholder(R.drawable.fast_food_icon_white)
                .into(holder.foodImage);
        //food name
        holder.foodName.setText(name);
        //number of required ingredient for food
        holder.requiredIngredient.setText(requiredIngredient);
    }

    @Override
    public int getItemCount() {
        return recipeModel.getHits().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final ImageView foodImage;
        private final TextView requiredIngredient;
        private final TextView foodName;
        int position;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            //hooks
            foodImage = itemView.findViewById(R.id.food_image);
            requiredIngredient = itemView.findViewById(R.id.recipe_ingredient);
            foodName = itemView.findViewById(R.id.recipe_name);
            itemView.setOnClickListener(this);
        }



        @Override
        public void onClick(View v) {
            onItemClickListener.onItemClick(position);
        }
    }
}
