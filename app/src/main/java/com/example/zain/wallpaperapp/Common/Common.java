package com.example.zain.wallpaperapp.Common;

import android.content.res.ColorStateList;
import android.graphics.Color;

import com.example.zain.wallpaperapp.Model.WallpaperItem;

/**
 * Created by Zain on 5/14/2018.
 */

public class Common {
    public static final String STR_CATEGORY_BACKGROUND = "CategoryBackground";
    public static final String STR_WALLPAPER = "Backgrounds";
    public static String CATEGORY_SELECTED;
    public static String CATEGORY_ID_SELECTED;
    public static final int PERMISSION_REQUEST_CODE = 1000;

    public static WallpaperItem select_background = new WallpaperItem();

    public static ColorStateList getColorStateList() {
        int[][] states = new int[][] {
                new int[] { android.R.attr.state_enabled}, // enabled
                new int[] {-android.R.attr.state_enabled}, // disabled
                new int[] {-android.R.attr.state_checked}, // unchecked
                new int[] { android.R.attr.state_pressed}  // pressed
        };

        int[] colors = new int[] {
                Color.WHITE,
                Color.RED,
                Color.GREEN,
                Color.BLUE
        };


        return new ColorStateList(states, colors);
    }
}
