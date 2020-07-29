package com.example.foodie_application;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<MealItem> mealItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String listAsString = getIntent().getStringExtra("list");
        Gson gson = new Gson();
        Type type = new TypeToken<List<MealItem>>(){}.getType();
        mealItems = gson.fromJson(listAsString, type);

        try {
            if (mealItems != null) {
                MealItemAdapter mealItemAdapter = new MealItemAdapter(mealItems);
                GridLayoutManager gridLayoutManager;
                if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                     gridLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
                } else {
                    gridLayoutManager = new GridLayoutManager(getApplicationContext(), 1);
                }

                RecyclerView recyclerView = findViewById(R.id.rv);
                recyclerView.setLayoutManager(gridLayoutManager);
                recyclerView.setAdapter(mealItemAdapter);
            }
        } catch (Exception e) {
        }

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Gson gson = new Gson();
                String json = gson.toJson(mealItems);
                Intent intent = new Intent(MainActivity.this, AddItemActivity.class);
                intent.putExtra("list", json);
                startActivity(intent);
            }
        });
    }
}