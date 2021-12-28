package com.example.recipe.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.recipe.model.Recipe;
import com.example.recipe.repositories.RecipeRepository;

import java.util.List;

public class RecipeListViewModel  extends ViewModel {
  RecipeRepository recipeRepository;

    public RecipeListViewModel() {
        recipeRepository = RecipeRepository.getInstance();
    }
    public LiveData<List<Recipe>>getRecipes(){
        return recipeRepository.geRecipes();
    }
    public void searchRecipesViewModel(String query, int pageNumber){
        recipeRepository.searchRecipesApi(query, pageNumber);
    }
}
