package com.example.recipe.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.recipe.R;
import com.example.recipe.adapters.interfaces.OnRecipeListener;
import com.example.recipe.model.Recipe;

import java.util.List;

public class RecipeRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    List<Recipe>recipeList;
    OnRecipeListener onRecipeListener;

    public RecipeRecyclerAdapter( OnRecipeListener onRecipeListener) {

        this.onRecipeListener = onRecipeListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_recipe_list_item,parent,false);
        return new RecipeViewHolder(view, onRecipeListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((RecipeViewHolder)holder).title.setText(recipeList.get(position).getTitle());
        ((RecipeViewHolder)holder).publisher.setText(recipeList.get(position).getPublisher());
        ((RecipeViewHolder)holder).socialScore.setText(String.valueOf(recipeList.get(position).getSocial_rank()));
        RequestOptions requestOptions = new RequestOptions().placeholder(R.drawable.ic_launcher_background);
        Glide.with(holder.itemView.getContext()).setDefaultRequestOptions(requestOptions).load(recipeList.get(position).getImageUrl()).into(((RecipeViewHolder)holder).imageView);


    }
    public void setRecipeList(List<Recipe>recipeList){
        this.recipeList = recipeList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (recipeList != null){
            return recipeList.size();
        }else{
            return 0;
        }
    }
}
