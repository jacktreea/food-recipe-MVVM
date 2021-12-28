package com.example.recipe.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.recipe.R;
import com.example.recipe.model.Recipe;
import com.example.recipe.requests.ReceipeApi;
import com.example.recipe.requests.ServiceGenerator;
import com.example.recipe.requests.responses.RecipeResponse;
import com.example.recipe.requests.responses.RecipeSearchResponse;
import com.example.recipe.viewmodels.RecipeListViewModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReceipeListActivity extends BaseActivity {
    private static final String TAG = "ReceipeListActivity";
    private RecipeListViewModel recipeListViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        recipeListViewModel = new ViewModelProvider(this).get(RecipeListViewModel.class);
        setContentView(R.layout.activity_receipe_list);
        subscribeObservers();
        findViewById(R.id.testBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchRecipes("indian", 1);
            }
        });
    }
    public void subscribeObservers(){
        recipeListViewModel.getRecipes().observe(this, new Observer<List<Recipe>>() {
            @Override
            public void onChanged(List<Recipe> recipes) {
                if (recipes != null){
                    for (Recipe recipe: recipes){
                        Log.e(TAG, "onChanged: Received List of Recipes "+recipe.toString() );
                    }
                }else{
                    Log.e(TAG, "onChanged: Results is NULL" );
                }

            }
        });
    }
    private void searchRecipes(String query, int pageNumber){
        recipeListViewModel.searchRecipesViewModel(query, pageNumber);
    }
    public void testRetroficRequest(){
        ReceipeApi receipeApi = ServiceGenerator.getReceipeApi();
        //Call<RecipeSearchResponse>responseCall = receipeApi.searchRecipe("Indian ","1");

        Call<RecipeResponse>responseCall = receipeApi.getRecipe("41470");

        responseCall.enqueue(new Callback<RecipeResponse>() {
            @Override
            public void onResponse(Call<RecipeResponse> call, Response<RecipeResponse> response) {
                if (response.code() == 200){
                    Recipe mRecipe = response.body().getRecipe();
                    Log.e(TAG, "onResponse: Returned Object of Recipe "+mRecipe.toString());
                }else{
                    try {
                        Log.e(TAG, "onResponse: Error Occured "+response.errorBody().toString() );
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<RecipeResponse> call, Throwable t) {

            }
        });
//
//        responseCall.enqueue(new Callback<RecipeSearchResponse>() {
//            @Override
//            public void onResponse(Call<RecipeSearchResponse> call, Response<RecipeSearchResponse> response) {
//                Log.e(TAG, "onResponse: "+response.toString() );
//                if (response.code() == 200){
//                    assert response.body() != null;
//                    Log.e(TAG, "onResponse: Results "+response.body().getRecipeList().toString() );
//                    List<Recipe>recipeList = new ArrayList<>(response.body().getRecipeList());
//                    for (Recipe recipe: recipeList ){
//                        Log.e(TAG, "onResponse: list of recipe "+recipe.toString() );
//                    }
//                }else{
//                    try {
//                        Log.e(TAG, "onResponse: Error occured "+response.errorBody().toString() );
//                    }catch (Exception e){
//                        e.printStackTrace();
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(Call<RecipeSearchResponse> call, Throwable t) {
//
//            }
//        });
    }
}