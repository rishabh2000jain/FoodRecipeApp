package com.example.foodRecipeApp.recipeListScreen;



import com.example.foodRecipeApp.network.RetrofitHelper;

import com.example.foodRecipeApp.recipeListScreen.recipeApiPOJO.RecipeModel;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecipeListModelClass implements RecipeListModelInterface {
    private final RecipeListContractInterface.Presenter presenter;

    public RecipeListModelClass(RecipeListContractInterface.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void getDataFromApi(String recipeName) {
        Call<RecipeModel> list = RetrofitHelper.getServicesInstance().getRecipeList(recipeName);
        list.enqueue(new Callback<RecipeModel>() {
            @Override
            public void onResponse(Call<RecipeModel> call, Response<RecipeModel> response) {
                RecipeModel recipeModel = response.body();
                presenter.onResponse(recipeModel);
            }

            @Override
            public void onFailure(Call<RecipeModel> call, Throwable t) {
                presenter.onFailure();
            }
        });
    }

}
