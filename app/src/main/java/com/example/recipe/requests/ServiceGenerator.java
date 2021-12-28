package com.example.recipe.requests;

import com.example.recipe.utils.Constant;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {

    private static Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
            .baseUrl(Constant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create());
    private static Retrofit retrofit = retrofitBuilder.build();
    private static ReceipeApi receipeApi = retrofit.create(ReceipeApi.class);

    public static ReceipeApi getReceipeApi(){
        return receipeApi;
    }

}
