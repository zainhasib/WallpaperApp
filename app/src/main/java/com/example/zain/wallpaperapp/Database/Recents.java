package com.example.zain.wallpaperapp.Database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;

import android.support.annotation.NonNull;

/**
 * Created by Zain on 5/17/2018.
 */

@Entity(tableName = "recents", primaryKeys = {"imageLink"})
public class Recents {

    @ColumnInfo(name = "imageLink")
    @NonNull
    private String imageLink;

    @ColumnInfo(name = "categoryId")
    @NonNull
    private String categoryId;

    @ColumnInfo(name = "saveTime")
    private String saveTime;

    public Recents(@NonNull String imageLink, @NonNull String categoryId, String saveTime) {
        this.imageLink = imageLink;
        this.categoryId = categoryId;
        this.saveTime = saveTime;
    }


    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(@android.support.annotation.NonNull String imageLink) {
        this.imageLink = imageLink;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(@NonNull String categoryId) {
        this.categoryId = categoryId;
    }

    public String getSaveTime() {
        return saveTime;
    }

    public void setSaveTime(String saveTime) {
        this.saveTime = saveTime;
    }
}
