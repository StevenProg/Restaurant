package com.example.gebruiker.restaurant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class CategoriesActivity extends AppCompatActivity implements CategoriesRequest.Callback {

    ListView category_list_view;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);
        category_list_view = findViewById(R.id.category_listID);
        category_list_view.setOnItemClickListener(new ListClickListener());

        // gets information about possible categories, using CategoriesRequest.class
        CategoriesRequest request = new CategoriesRequest(getApplicationContext());
        request.getCategories(this);
    }

    // function for when the categories are received
    @Override
    public void gotCategories(ArrayList<String> categories) {
        ArrayAdapter<String> categoryAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, categories);
        category_list_view.setAdapter(categoryAdapter);
    }

    // function for when the categories can't be received
    @Override
    public void gotCategoriesError(String message) {
        CharSequence text = "Error!!!! !";
        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(this, text, duration);
        toast.show();
    }

    // listener to send clicked category to MenuActivity
    private class ListClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            Intent intent = new Intent(CategoriesActivity.this, MenuActivity.class);
            intent.putExtra("category", (String) adapterView.getItemAtPosition(i));
            startActivity(intent);
        }
    }
}

