package com.example.recipe.Activities;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.recipe.R;

public abstract class BaseActivity extends AppCompatActivity {
    ProgressBar mProgressBar;
    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        ConstraintLayout mContraintLayout = (ConstraintLayout) getLayoutInflater().inflate(R.layout.activity_base, null);
        mProgressBar = mContraintLayout.findViewById(R.id.mProgressBar);
        FrameLayout mFrameLayout = mContraintLayout.findViewById(R.id.mFrameLayout);
        getLayoutInflater().inflate(layoutResID,mFrameLayout,true);
    }
    public void showProgressBar(boolean visibility) {
        mProgressBar.setVisibility(visibility? View.VISIBLE:View.GONE);
    }
}
