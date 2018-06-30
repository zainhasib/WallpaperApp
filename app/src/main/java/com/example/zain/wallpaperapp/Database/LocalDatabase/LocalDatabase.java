package com.example.zain.wallpaperapp.Database.LocalDatabase;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.zain.wallpaperapp.Database.Recents;

import static com.example.zain.wallpaperapp.Database.LocalDatabase.LocalDatabase.DATABASE_VERSION;

/**
 * Created by Zain on 5/17/2018.
 */


@Database(entities = Recents.class, version = DATABASE_VERSION)
public abstract class LocalDatabase extends RoomDatabase {
    public static  final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "ZainAnimeWallpaper";

    public abstract RecentsDAO recentsDAO();

    private static LocalDatabase instance;

    public static LocalDatabase getInstance(Context context)
    {
        if (instance == null)
        {
            instance = Room.databaseBuilder(context, LocalDatabase.class, DATABASE_NAME)
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
