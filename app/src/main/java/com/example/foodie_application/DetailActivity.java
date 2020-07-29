package com.example.foodie_application;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class DetailActivity extends AppCompatActivity {

    List<MealItem> mealItems;

    TextView title;
    TextView desc;
    TextView ingredients;
    TextView calories;
    TextView link;
    ImageView src;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        String listAsString = getIntent().getStringExtra("list");
        Gson gson = new Gson();
        Type type = new TypeToken<List<MealItem>>(){}.getType();
        mealItems = gson.fromJson(listAsString, type);

        int pos = getIntent().getIntExtra("pos", -1);

        if (mealItems != null && pos > -1) {
            title = findViewById(R.id.title);
            desc = findViewById(R.id.desc);
            ingredients = findViewById(R.id.ingredients);
            calories = findViewById(R.id.calories);
            link = findViewById(R.id.link);
            src = findViewById(R.id.image);

            title.setText(mealItems.get(pos).getTitle());
            desc.setText(mealItems.get(pos).getDesc());
            ingredients.setText(mealItems.get(pos).getIngredients());
            calories.setText(mealItems.get(pos).getCalories());
            link.setText(mealItems.get(pos).getUrl());
            Glide.with(this).load(mealItems.get(pos).getImage()).into(src);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Gson gson = new Gson();
        String json = gson.toJson(mealItems);
        Intent intent = new Intent(DetailActivity.this, MainActivity.class);
        intent.putExtra("list", json);
        startActivity(intent);
    }
}