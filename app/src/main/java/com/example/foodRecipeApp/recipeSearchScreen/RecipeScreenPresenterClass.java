package com.example.foodRecipeApp.recipeSearchScreen;

public class RecipeScreenPresenterClass implements RecipeSearchContractInterface.Presenter {
    RecipeSearchContractInterface.View view;
    RecipeSearchModelInterface modelImp;

    public RecipeScreenPresenterClass(RecipeSearchContractInterface.View view) {
        this.view = view;
        modelImp = new RecipeSearchModelClass(this);
    }

    @Override
    public void onSearchButtonClicked(String itemName) {
        modelImp.isValidName(itemName);
    }

    @Override
    public void validName() {
        view.moveToRecipeListActivity();
    }

    @Override
    public void invalidName() {
        view.onEmptyString("Item name can't be empty");
    }

}
