package com.example.recipe.requests;

import static com.example.recipe.utils.Constant.BASE_URL;
import static com.example.recipe.utils.Constant.NETWORK_TIMEOUT;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.recipe.AppExecutors;
import com.example.recipe.model.Recipe;
import com.example.recipe.requests.responses.RecipeSearchResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Response;

public class RecipeApiClient {
    private static final String TAG = "RecipeApiClient";
    private static RecipeApiClient instance;
    private MutableLiveData<List<Recipe>>mRecipes;
    private RetrieveRecipeRunnable retrieveRecipeRunnable;
    public static RecipeApiClient getInstance(){
        if (instance == null){
            instance = new RecipeApiClient();
        }
        return instance;
    }

    public RecipeApiClient(){
       mRecipes = new MutableLiveData<>();
    }
    public LiveData<List<Recipe>> getRecipes(){
        return mRecipes;
    }
    public void searchRecipe(String query, int pageNumber){
        if (retrieveRecipeRunnable != null){
            retrieveRecipeRunnable = null;
        }
        retrieveRecipeRunnable = new RetrieveRecipeRunnable(query, pageNumber);
        Log.e(TAG, "searchRecipe: query "+query );
        Log.e(TAG, "searchRecipe: query "+pageNumber );
        if (retrieveRecipeRunnable != null){
            Log.e(TAG, "searchRecipe: Runnable not null "+retrieveRecipeRunnable.pageNumber );
        }else{
            Log.e(TAG, "searchRecipe: Runnable is null " );

        }
            final Future future = AppExecutors.getInstance().getmNetworkIo().submit(retrieveRecipeRunnable);

        AppExecutors.getInstance().getmNetworkIo().schedule(new Runnable() {
            @Override
            public void run() {
                //let the user is timed out
                future.cancel(true);
            }
        },NETWORK_TIMEOUT, TimeUnit.MILLISECONDS);
    }
    private class RetrieveRecipeRunnable implements Runnable{
        private String query;
        private int pageNumber;
        private boolean cancelRequest;

        public RetrieveRecipeRunnable(String query, int pageNumber) {
            this.query = query;
            this.pageNumber = pageNumber;
            cancelRequest = false;
        }

        @Override
        public void run() {
            try {
                Response response= getRecipes(query,pageNumber).execute();
                if (cancelRequest){
                    return;
                }
                if (response.code() == 200){
                    List<Recipe>recipeList = new ArrayList<>(((RecipeSearchResponse)response.body()).getRecipeList());
                    if (pageNumber == 1){
                        mRecipes.postValue(recipeList);
                    }else{
                        List<Recipe>currentRecipes = mRecipes.getValue();
                        currentRecipes.addAll(recipeList);
                        mRecipes.postValue(currentRecipes);
                    }
                }else{
                    String error = response.errorBody().string();
                    Log.e(TAG, "run: "+error );
                    mRecipes.postValue(null);
                }
            } catch (IOException e) {
                e.printStackTrace();
                mRecipes.postValue(null);
            }
        }
        private Call<RecipeSearchResponse>getRecipes( String query, int pageNumber){
            return ServiceGenerator.getReceipeApi().searchRecipe(query,String.valueOf(pageNumber));
        }
    }
}
