package com.example.foodRecipeApp.recipeListScreen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodRecipeApp.R;
import com.example.foodRecipeApp.recipeListScreen.recipeApiPOJO.Hit;
import com.example.foodRecipeApp.recipeListScreen.recipeApiPOJO.RecipeModel;
import com.example.foodRecipeApp.broadcastReceiver.MyReceiver;
import com.example.foodRecipeApp.recipeDetailFragment.RecipeDetailsFragment;

public class RecipeListActivity extends AppCompatActivity implements RecipeListContractInterface.View, RecipeListRecyclerViewAdapter.OnItemClickListener {
    private RecyclerView recyclerView;
    private RecipeListContractInterface.Presenter presenter;
    private TextView foodNameTxt;
    private String name;
    private MyReceiver myReceiver;
    private ProgressDialog progressDialog;
    private RecipeModel recipeModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_list);
        presenter = new RecipeListPresenterClass(this);
        //hooks
        recyclerView = findViewById(R.id.recycler_view);
        foodNameTxt = findViewById(R.id.food_name_txt);
        Intent intent = getIntent();
        name = intent.getStringExtra("RecipeName");
        // setting progress dialog
        foodNameTxt.setText(name);
        progressDialog = new ProgressDialog(this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage("getting Data.....");
        progressDialog.setCancelable(false);
        startLoadingData();
        myReceiver = new MyReceiver();
        registerNetworkBroadcast();
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
    public void dataLoadingSuccess(RecipeModel recipeModel) {
        this.recipeModel = recipeModel;
        progressDialog.dismiss();
        if(recipeModel.getHits().size() == 0){
            Toast.makeText(this,"no results found for "+name,Toast.LENGTH_LONG).show();
        }
        RecipeListRecyclerViewAdapter recipeListRecyclerViewAdapter = new RecipeListRecyclerViewAdapter(this, this, recipeModel);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(recipeListRecyclerViewAdapter);
    }

    @Override
    public void dataLoadingFailed() {
        progressDialog.dismiss();
        Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void onItemClick(int position) {
        //invoking fragment for item clicked
        FragmentManager fragmentManager = getSupportFragmentManager();
        if (fragmentManager.getBackStackEntryCount() == 0) {
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            RecipeDetailsFragment recipeDetailsFragment = new RecipeDetailsFragment();
            Bundle bundle = new Bundle();
            Hit hit = recipeModel.getHits().get(position);
            bundle.putParcelable("RecipeHit", hit);
            recipeDetailsFragment.setArguments(bundle);
            fragmentTransaction.replace(R.id.frame_layout, recipeDetailsFragment).addToBackStack(null);
            fragmentTransaction.commit();
        }
    }


    private void startLoadingData() {
        // starting data download
        presenter.downloadData(name);
        progressDialog.show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterNetworkChanges();
    }
}