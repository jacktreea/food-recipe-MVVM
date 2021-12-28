package com.example.recipe.repositories;

import androidx.lifecycle.LiveData;

import com.example.recipe.model.Recipe;
import com.example.recipe.requests.RecipeApiClient;

import java.util.List;

public class RecipeRepository {
    private static RecipeRepository instance;
    private RecipeApiClient recipeApiClient;
    public static RecipeRepository getInstance(){
        if (instance == null){
            instance = new RecipeRepository();
        }
        return instance;
    }
    public RecipeRepository(){
        recipeApiClient = RecipeApiClient.getInstance();
    }
    public LiveData<List<Recipe>>geRecipes(){
        return recipeApiClient.getRecipes();
    }
    public void searchRecipesApi(String query, int pageNumber){
        if (pageNumber == 0){
            pageNumber = 1;
        }
        recipeApiClient.searchRecipe(query, pageNumber);
    }
}
