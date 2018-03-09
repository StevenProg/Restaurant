package com.example.gebruiker.restaurant;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by ${Steven} on ${22/2}.
 */

public class MenuItemAdapter extends ArrayAdapter<MenuItem> {

    private ArrayList<MenuItem> menuItemArrayList;

    MenuItemAdapter(@NonNull Context context, int resource, @NonNull ArrayList<MenuItem> objects) {
        super(context, resource, objects);
        menuItemArrayList = objects;
    }


    // sets the input for the menu list
    @NonNull
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.menu_item_view, parent, false);
        }

        TextView itemName = convertView.findViewById(R.id.ItemNameID);
        TextView itemPrice = convertView.findViewById(R.id.ItemPriceID);
        ImageView itemImage = convertView.findViewById(R.id.ItemImageID);

        MenuItem itemOnMenu = menuItemArrayList.get(position);
        String name = itemOnMenu.getName();
        String price = itemOnMenu.getPrice().toString();
        String image = itemOnMenu.getImageUrl();


        itemName.setText(name);
        itemPrice.setText("€"+ price);
        Picasso.with(this.getContext()).load(image).into(itemImage);

        return convertView;
    }
}