
package com.example.foodRecipeApp.recipeListScreen.recipeApiPOJO;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Recipe {

    @SerializedName("uri")
    @Expose
    private String uri;
    @SerializedName("label")
    @Expose
    private String label;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("source")
    @Expose
    private String source;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("shareAs")
    @Expose
    private String shareAs;
    @SerializedName("yield")
    @Expose
    private Integer yield;

    @SerializedName("ingredientLines")
    @Expose
    private List<String> ingredientLines = null;

    public String getUri() {
        return uri;
    }


    public String getLabel() {
        return label;
    }


    public String getImage() {
        return image;
    }


    public String getSource() {
        return source;
    }


    public String getUrl() {
        return url;
    }


    public String getShareAs() {
        return shareAs;
    }


    public Integer getYield() {
        return yield;
    }


    public List<String> getIngredientLines() {
        return ingredientLines;
    }



}
