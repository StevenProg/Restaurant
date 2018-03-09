package com.example.gebruiker.restaurant;

import android.content.Context;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by ${Steven} on ${22/2}.
 */

public class MenuItemsRequest implements Response.Listener<JSONObject>, Response.ErrorListener {


    public interface Callback {
        void gotMenuItems(ArrayList<MenuItem> categories);
        void gotMenuItemsError(String message);
    }

    private String current_category;
    private Callback current_activity;
    private Context context_reference;
    private ArrayList<MenuItem> menu_options = new ArrayList<>();

    MenuItemsRequest(Context context, String category) {
        context_reference = context;
        current_category = category;
    }


    // Requests data from 'url' in JSON format
    void getMenuItems(final Callback activity, String category) {
        current_activity = activity;
        current_category = category;
        RequestQueue queue = Volley.newRequestQueue(context_reference);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                "https://resto.mprog.nl/menu",
                null, this, this);
        queue.add(jsonObjectRequest);
    }

    // selects the data which has te same category as the received category, and puts these in menu_options
    @Override
    public void onResponse(JSONObject response) {
        JSONArray menu_array;
        try {
            menu_array = response.getJSONArray("items");
            int length = menu_array.length();
            JSONObject menu_object;
            for (int i = 0; i < length; i++) {
                menu_object = menu_array.getJSONObject(i);
                String category = menu_object.getString("category");
                if (current_category.equals(category)) {
                    String name = menu_object.getString("name");
                    String description = menu_object.getString("description");
                    String imageUrl = menu_object.getString("image_url");
                    Double price = menu_object.getDouble("price");
                    menu_options.add(new MenuItem(name, description, imageUrl, price, category));
                }
            }
        } catch (JSONException e) {
            Log.d("JSON", e.toString());
        }
        current_activity.gotMenuItems(menu_options);

    }

    @Override
    public void onErrorResponse(VolleyError error) {
        current_activity.gotMenuItemsError(error.getMessage());

    }
}
