package com.example.foodRecipeApp.recipeSearchScreen;

public interface RecipeSearchContractInterface {
    interface Presenter {
        void onSearchButtonClicked(String itemName);
        void validName();
        void invalidName();
    }

    interface View {
        void moveToRecipeListActivity();
        void onEmptyString(String text);
    }
}
