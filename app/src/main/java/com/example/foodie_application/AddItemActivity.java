package com.example.foodie_application;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class AddItemActivity extends AppCompatActivity {

    EditText image;
    EditText title;
    EditText desc;
    EditText ingredients;
    EditText calories;
    EditText link;
    Button save;
    MealItem mealItem;
    List<MealItem> mealItems;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);


        String listAsString = getIntent().getStringExtra("list");
        Gson gson = new Gson();
        Type type = new TypeToken<List<MealItem>>(){}.getType();
        mealItems = gson.fromJson(listAsString, type);

        image = findViewById(R.id.image);
        title = findViewById(R.id.title);
        desc = findViewById(R.id.desc);
        ingredients = findViewById(R.id.ingredients);
        calories = findViewById(R.id.calories);
        link = findViewById(R.id.link);
        save = findViewById(R.id.save);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!image.getText().toString().equals("")
                && !title.getText().toString().equals("")
                && !desc.getText().toString().equals("")
                && !ingredients.getText().toString().equals("")
                && !calories.getText().toString().equals("")
                && !link.getText().toString().equals("")) {

                    if (mealItems != null) {

                        mealItem = new MealItem(title.getText().toString(),
                                image.getText().toString(),
                                desc.getText().toString(),
                                calories.getText().toString(),
                                link.getText().toString(),
                                ingredients.getText().toString());

                        Gson gson = new Gson();

                        mealItems.add(mealItem);

                        String json = gson.toJson(mealItems);

                        Intent intent = new Intent(AddItemActivity.this, MainActivity.class);
                        intent.putExtra("list", json);
                        startActivity(intent);
                    } else {
                        mealItems = new ArrayList<>();
                        mealItem = new MealItem(title.getText().toString(),
                                image.getText().toString(),
                                desc.getText().toString(),
                                calories.getText().toString(),
                                link.getText().toString(),
                                ingredients.getText().toString());

                        Gson gson = new Gson();

                        mealItems.add(mealItem);

                        String json = gson.toJson(mealItems);

                        Intent intent = new Intent(AddItemActivity.this, MainActivity.class);
                        intent.putExtra("list", json);
                        startActivity(intent);
                    }
                } else {
                    Toast.makeText(AddItemActivity.this, getResources().getString(R.string.input_empty), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Gson gson = new Gson();
        String json = gson.toJson(mealItems);
        Intent intent = new Intent(AddItemActivity.this, MainActivity.class);
        intent.putExtra("list", json);
        startActivity(intent);
    }
}