package com.example.foodRecipeApp.recipeSearchScreen;

public class RecipeSearchModelClass implements RecipeSearchModelInterface {
    private RecipeSearchContractInterface.Presenter presenter;

    public RecipeSearchModelClass(RecipeSearchContractInterface.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void isValidName(String itemName) {
        if(itemName.isEmpty()){
            presenter.invalidName();
        }else{
            presenter.validName();
        }
    }
}
