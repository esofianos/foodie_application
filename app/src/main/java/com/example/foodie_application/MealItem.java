package com.example.foodie_application;

public class MealItem {

    private String title;
    private String image;
    private String desc;
    private String calories;
    private String url;
    private String ingredients;

    public MealItem(String title, String image, String desc, String calories, String url, String ingredients) {
        this.title = title;
        this.image = image;
        this.desc = desc;
        this.calories = calories;
        this.url = url;
        this.ingredients = ingredients;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getCalories() {
        return calories;
    }

    public void setCalories(String calories) {
        this.calories = calories;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }
}
