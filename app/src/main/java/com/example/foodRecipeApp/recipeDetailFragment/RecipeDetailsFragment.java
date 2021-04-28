package com.example.foodRecipeApp.recipeDetailFragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.example.foodRecipeApp.R;
import com.example.foodRecipeApp.recipeListScreen.recipeApiPOJO.Hit;
import com.example.foodRecipeApp.recipeListScreen.recipeApiPOJO.Recipe;

public class RecipeDetailsFragment extends Fragment {

    private ImageView foodImage;
    private TextView recipeName, recipeIngredient, recipeLink;
    private Hit recipeHit;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        foodImage = view.findViewById(R.id.frag_food_image);
        recipeName = view.findViewById(R.id.frag_recipe_name);
        recipeIngredient = view.findViewById(R.id.frag_ingredient_list);
        recipeLink = view.findViewById(R.id.frag_recipe_web_link);
        recipeHit = getArguments().getParcelable("RecipeHit");
        setDataToViews();
    }
    private void setDataToViews() {
        Recipe recipe = recipeHit.getRecipe();
        // recipe image url
        String url = recipe.getImage();
        Glide.with(requireView()).load(url)
                .placeholder(R.drawable.fast_food_icon_white)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(foodImage);
        //recipe name
        String name = recipe.getLabel();
        recipeName.setText(name);

        //recipe Ingredient
        String ingredient = "";
        for (String in : recipe.getIngredientLines()) {
            ingredient = ingredient.concat(in).concat("\n\n");

        }
        recipeIngredient.setText(ingredient);

        //recipe link
        String link = recipe.getUrl();
        recipeLink.setText(link);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_recipe_details, container, false);
    }

}