package com.example.foodRecipeApp.recipeSearchScreen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.foodRecipeApp.R;
import com.example.foodRecipeApp.broadcastReceiver.MyReceiver;
import com.example.foodRecipeApp.recipeListScreen.RecipeListActivity;

public class RecipeSearchActivity extends AppCompatActivity implements RecipeSearchContractInterface.View, View.OnClickListener {

    private EditText recipeSearchEdt;
    private AppCompatButton searchBtn;
    private RecipeSearchContractInterface.Presenter presenter;
    private MyReceiver myReceiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_search);
        recipeSearchEdt = findViewById(R.id.recipe_search_edt);
        searchBtn = findViewById(R.id.search_recipe_btn);
        presenter = new RecipeScreenPresenterClass(this);
        searchBtn.setOnClickListener(this);
        myReceiver = new MyReceiver();
        registerNetworkBroadcast();
    }

    @Override
    public void moveToRecipeListActivity() {
        Intent intent = new Intent(this, RecipeListActivity.class);
        String recipeName = recipeSearchEdt.getText().toString();
        intent.putExtra("RecipeName",recipeName);
        startActivity(intent);
    }

    @Override
    public void onEmptyString(String text) {
        recipeSearchEdt.setError(text);
    }
    protected void unregisterNetworkChanges() {
        try {
            unregisterReceiver(myReceiver);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    private void registerNetworkBroadcast() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            registerReceiver(myReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
        }
    }
    @Override
    public void onClick(View v) {
        presenter.onSearchButtonClicked(recipeSearchEdt.getText().toString());
        recipeSearchEdt.setText("");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterNetworkChanges();
    }
}