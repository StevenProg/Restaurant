package com.example.gebruiker.restaurant;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;

/**
 * Created by ${Steven} on ${22/2}.
 */

public class MenuItemActivity extends AppCompatActivity {

    // gets information from MenuActivity about clicked menu item, and displays this in activity_menu_item.xml format
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_item);

        Intent intent = getIntent();
        MenuItem menuEntry = (MenuItem) intent.getSerializableExtra("menuEntry");

        TextView name = findViewById(R.id.NameID);
        TextView description = findViewById(R.id.DescritpionID);
        TextView price = findViewById(R.id.PriceID);
        ImageView image = findViewById(R.id.ImageID);


        name.setText(menuEntry.getName());
        description.setText(menuEntry.getDescription());
        price.setText("€" + menuEntry.getPrice().toString());
        Picasso.with(this).load(menuEntry.getImageUrl()).into(image);

    }
}
