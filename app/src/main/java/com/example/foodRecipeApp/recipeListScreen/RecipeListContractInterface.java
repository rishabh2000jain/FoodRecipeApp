package com.example.foodRecipeApp.recipeListScreen;

import com.example.foodRecipeApp.recipeListScreen.recipeApiPOJO.RecipeModel;


public interface RecipeListContractInterface {
    interface View {
        void dataLoadingSuccess(RecipeModel recipeModel);
        void dataLoadingFailed();
    }

    interface Presenter {
        void downloadData(String recipeName);
        void onResponse(RecipeModel recipeModel);
        void onFailure();
    }
}
