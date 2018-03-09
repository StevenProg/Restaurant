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

public class CategoriesRequest implements Response.Listener<JSONObject>, Response.ErrorListener {
    public interface Callback {
        void gotCategories(ArrayList<String> categories);
        void gotCategoriesError(String message);
    }

    private Callback current_activity;
    private Context context_reference;
    private ArrayList<String> menu_categories = new ArrayList<>();

    CategoriesRequest(Context context) {
        context_reference = context;
    }


    // Requests data from 'url' in JSON format
    void getCategories(final Callback activity) {
        current_activity = activity;
        RequestQueue queue = Volley.newRequestQueue(context_reference);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                "https://resto.mprog.nl/categories",
                null, this, this);
        queue.add(jsonObjectRequest);
    }

    // when request is successful, set the JSON data in current_activity
    @Override
    public void onResponse(JSONObject response) {
        JSONArray category_response;
        try {
            category_response = response.getJSONArray("categories");
            int length = category_response.length();
            for (int i = 0; i < length; i++) {
                    menu_categories.add(category_response.getString(i));
            }
        } catch (JSONException e) {
            Log.d("JSON", e.toString());
        }

        current_activity.gotCategories(menu_categories);

    }

    @Override
    public void onErrorResponse(VolleyError error) {
        current_activity.gotCategoriesError(error.getMessage());
    }
}
