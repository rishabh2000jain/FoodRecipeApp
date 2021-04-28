package com.example.foodRecipeApp.recipeListScreen;


import com.example.foodRecipeApp.recipeListScreen.recipeApiPOJO.RecipeModel;

public class RecipeListPresenterClass implements RecipeListContractInterface.Presenter {
    private final RecipeListContractInterface.View view;
    private final RecipeListModelInterface model;

    public RecipeListPresenterClass(RecipeListContractInterface.View view) {
        this.view = view;
        model = new RecipeListModelClass(this);
    }

    @Override
    public void downloadData(String name) {
        model.getDataFromApi(name);
    }

    @Override
    public void onResponse(RecipeModel recipeModel) {
        view.dataLoadingSuccess(recipeModel);
    }

    @Override
    public void onFailure() {
        view.dataLoadingFailed();
    }

}
