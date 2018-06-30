package com.example.zain.wallpaperapp.Model;

/**
 * Created by Zain on 5/14/2018.
 */

public class CategoryItem {
    public String name;
    public String imageLink;

    public CategoryItem(String name, String imageLink) {
        this.name = name;
        this.imageLink = imageLink;
    }

    public CategoryItem() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }
}
