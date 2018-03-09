package com.example.gebruiker.restaurant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity implements MenuItemsRequest.Callback{
    ListView menu_list_view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        // gets information about the clicked category from CategoriesActivity.class
        Intent intent = getIntent();
        String category = (String) intent.getSerializableExtra("category");
        MenuItemsRequest request = new MenuItemsRequest(getApplicationContext(), category);
        request.getMenuItems(this, category);

        menu_list_view = findViewById(R.id.menu_listID);
        menu_list_view.setOnItemClickListener(new ListClickListener());

    }

    // function for when the menus are received
    @Override
    public void gotMenuItems(ArrayList<MenuItem> menuItems) {
        MenuItemAdapter adapter = new MenuItemAdapter(this, R.layout.menu_item_view, menuItems);
        menu_list_view.setAdapter(adapter);
    }

    // function for when the categories can't be received
    @Override
    public void gotMenuItemsError(String message) {
        CharSequence text = "Error!!!! !";
        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(this, text, duration);
        toast.show();
    }

    // listener to send clicked menu item to MenuItemActivity
    public class ListClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            Intent intent = new Intent(MenuActivity.this, MenuItemActivity.class);
            intent.putExtra("menuEntry", (Serializable) adapterView.getItemAtPosition(i));
            startActivity(intent);
        }
    }
}
