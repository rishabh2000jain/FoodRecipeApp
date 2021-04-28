package com.example.foodRecipeApp.homeScreen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.foodRecipeApp.R;
import com.example.foodRecipeApp.recipeSearchScreen.RecipeSearchActivity;

public class HomeScreenActivity extends AppCompatActivity implements View.OnClickListener {
    AppCompatButton btnHomeToSearchRecipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_sreen);
        setActionBar(findViewById(R.id.toolbar));
        btnHomeToSearchRecipe = findViewById(R.id.home_to_search_recipe);
        btnHomeToSearchRecipe.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, RecipeSearchActivity.class);
        startActivity(intent);
    }
}