package com.example.recipe.activities;

import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.style.LineHeightSpan;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.recipe.R;
import com.example.recipe.adapters.RecipeRecyclerAdapter;
import com.example.recipe.adapters.interfaces.OnRecipeListener;
import com.example.recipe.model.Recipe;
import com.example.recipe.requests.ReceipeApi;
import com.example.recipe.requests.ServiceGenerator;
import com.example.recipe.requests.responses.RecipeResponse;
import com.example.recipe.viewmodels.RecipeListViewModel;
import com.google.android.material.snackbar.Snackbar;


import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReceipeListActivity extends BaseActivity implements OnRecipeListener {
    private static final String TAG = "ReceipeListActivity";
    private RecipeListViewModel recipeListViewModel;
    private RecyclerView mRecyclerView;
    private RecipeRecyclerAdapter recipeRecyclerAdapter;
    private SearchView searchView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        recipeListViewModel = new ViewModelProvider(this).get(RecipeListViewModel.class);

        setContentView(R.layout.activity_recipe_list);
        subscribeObservers();
        mRecyclerView = findViewById(R.id.recipeRecyclerView);
        initRecyclerView();
        searchView = findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(new androidx.appcompat.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                Toast.makeText(ReceipeListActivity.this, s, Toast.LENGTH_SHORT).show();
                searchRecipes(s,1);
                return false;


            }
        });
        searchRecipes("chicken",1);
        findViewById(R.id.textBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar mySnackbar = Snackbar.make(findViewById(R.id.textBtn),
                        "No Connection", Snackbar.LENGTH_LONG);
                mySnackbar.setAction("Retry", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(ReceipeListActivity.this, "Clicked", Toast.LENGTH_SHORT).show();
                    }
                });
                mySnackbar.show();;
            }
        });

    }
    public void initRecyclerView(){
        recipeRecyclerAdapter = new RecipeRecyclerAdapter(this);
        mRecyclerView.setAdapter(recipeRecyclerAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
    public void subscribeObservers(){
        recipeListViewModel.getRecipes().observe(this, new Observer<List<Recipe>>() {
            @Override
            public void onChanged(List<Recipe> recipes) {
                if (recipes != null){
                    recipeRecyclerAdapter.setRecipeList(recipes);
                    for (Recipe recipe:recipes){
                        Log.e(TAG, "onChanged: received list "+recipe.toString() );
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

    @Override
    public void onRecipeClick(int adapterPosition) {

    }

    @Override
    public void onCategoryClick(String category) {

    }
}