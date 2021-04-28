package com.example.foodRecipeApp.network;

import com.example.foodRecipeApp.recipeListScreen.recipeApiPOJO.RecipeModel;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class RetrofitHelper {
    private static final String YOUR_APP_ID = "1f652301";
    private static final String YOUR_APP_KEY = "0abd04e898aa390b2e44b0a0caeeb8e0";
    final static String baseUrl = "https://api.edamam.com/";
    final static String urlPart2 = "&app_id=" + YOUR_APP_ID + "&app_key=" + YOUR_APP_KEY;
    private static GETService services = null;

    public static GETService getServicesInstance() {
        if (services == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            services = retrofit.create(GETService.class);
        }
        return services;
    }

    public interface GETService {

        @GET("search?" + urlPart2)
        Call<RecipeModel> getRecipeList(@Query(value = "q") String q);
    }

}
