package com.example.restoaslam.model;


public class Food {
    String imageResource, name, description;
    Integer price;


    // Tambahkan setter untuk atribut imageResource
    public void setImageResource(String imageResource) {
        this.imageResource = imageResource;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setPrice(int price) {
        this.price = price;
    }






    public String getImageResource() {
        return imageResource;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getPrice() {
        return price;
    }
}