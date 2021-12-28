package com.example.recipe.requests;

import com.example.recipe.requests.responses.RecipeResponse;
import com.example.recipe.requests.responses.RecipeSearchResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ReceipeApi {
    // SEARCH
    @GET("/api/v2/recipes")
    Call<RecipeSearchResponse> searchRecipe(
            @Query("q") String query,
            @Query("page") String page
    );

    // GET RECIPE REQUEST
    @GET("api/get")
    Call<RecipeResponse> getRecipe(
            @Query("rId") String recipe_id
    );
}
