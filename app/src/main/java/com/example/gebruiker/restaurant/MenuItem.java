package com.example.gebruiker.restaurant;

import java.io.Serializable;

/**
 * Created by ${Steven} on ${22/2}.
 */

public class MenuItem implements Serializable{
    private String name, description, imageUrl, category;
    private Double price;

    MenuItem(String name, String description, String imageUrl, Double price, String category) {
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.price = price;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    String getDescription() {
        return description;
    }

    String getImageUrl() {
        return imageUrl;
    }

    Double getPrice() {
        return price;
    }

}

