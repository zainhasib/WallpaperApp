package com.example.zain.wallpaperapp.Model;

/**
 * Created by Zain on 5/15/2018.
 */

public class WallpaperItem {
    public String imageLink;
    public String categoryId;

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public WallpaperItem() {

    }

    public WallpaperItem(String imageLink, String categoryId) {

        this.imageLink = imageLink;
        this.categoryId = categoryId;
    }
}
