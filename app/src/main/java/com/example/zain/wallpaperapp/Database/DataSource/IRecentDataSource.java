package com.example.zain.wallpaperapp.Database.DataSource;

import com.example.zain.wallpaperapp.Database.Recents;

import java.util.List;

import io.reactivex.Flowable;

/**
 * Created by Zain on 5/17/2018.
 */

public interface IRecentDataSource {
    Flowable<List<Recents>> getAllRecents();
    void insertRecents(Recents... recents);
    void updateRecents(Recents... recents);
    void deleteRecents(Recents... recents);
    void deleteAllRecents();
}
