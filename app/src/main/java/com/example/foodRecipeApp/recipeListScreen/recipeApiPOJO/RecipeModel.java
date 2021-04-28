
package com.example.foodRecipeApp.recipeListScreen.recipeApiPOJO;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RecipeModel {

    @SerializedName("q")
    @Expose
    private String q;
    @SerializedName("from")
    @Expose
    private Integer from;
    @SerializedName("to")
    @Expose
    private Integer to;
    @SerializedName("more")
    @Expose
    private Boolean more;
    @SerializedName("count")
    @Expose
    private Integer count;
    @SerializedName("hits")
    @Expose
    private List<Hit> hits = null;

    public String getQ() {
        return q;
    }


    public Integer getFrom() {
        return from;
    }



    public Integer getTo() {
        return to;
    }


    public Integer getCount() {
        return count;
    }


    public List<Hit> getHits() {
        return hits;
    }


}
