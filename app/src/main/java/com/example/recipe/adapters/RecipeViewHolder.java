package com.example.recipe.adapters;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recipe.R;
import com.example.recipe.adapters.interfaces.OnRecipeListener;

public class RecipeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    TextView title, publisher, socialScore;
    AppCompatImageView imageView;
    OnRecipeListener onRecipeListener;
    public RecipeViewHolder(@NonNull View itemView, OnRecipeListener onRecipeListener) {
        super(itemView);
        title = itemView.findViewById(R.id.recipe_title);
        publisher = itemView.findViewById(R.id.recipe_publisher);
        socialScore = itemView.findViewById(R.id.recipe_social_score);
        imageView = itemView.findViewById(R.id.recipe_image);
        this.onRecipeListener = onRecipeListener;
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        onRecipeListener.onRecipeClick(getAdapterPosition());

    }
}
