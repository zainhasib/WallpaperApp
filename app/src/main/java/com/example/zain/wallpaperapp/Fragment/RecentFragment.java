package com.example.zain.wallpaperapp.Fragment;


import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zain.wallpaperapp.Adapter.MyRecyclerAdapter;
import com.example.zain.wallpaperapp.Common.Common;
import com.example.zain.wallpaperapp.Database.DataSource.RecentRepository;
import com.example.zain.wallpaperapp.Database.LocalDatabase.LocalDatabase;
import com.example.zain.wallpaperapp.Database.LocalDatabase.RecentsDataSource;
import com.example.zain.wallpaperapp.Database.Recents;
import com.example.zain.wallpaperapp.R;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 */

public class RecentFragment extends Fragment {

    private static RecentFragment INSTANCE = null;

    RecyclerView recyclerView;

    Context context;

    List<Recents> recentList;
    MyRecyclerAdapter adapter;

    //ROOM DATABASE
    CompositeDisposable compositeDisposable;
    RecentRepository recentRepository;

    public RecentFragment() {
    }

    @SuppressLint("ValidFragment")
    public RecentFragment(Context context) {
        this.context = context;

        // INIT ROOM DB
        compositeDisposable = new CompositeDisposable();
        LocalDatabase localDatabase = LocalDatabase.getInstance(context);
        recentRepository = RecentRepository.getInstance(RecentsDataSource.getInstance(localDatabase.recentsDAO()));
    }


    public static RecentFragment getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new RecentFragment(context);
        }
        return INSTANCE;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_recent, container, false);
        recyclerView = view.findViewById(R.id.recycler_recent);
        recyclerView.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        recentList = new ArrayList<>();
        adapter = new MyRecyclerAdapter(context, recentList);
        recyclerView.setAdapter(adapter);

        loadRecents();

        return view;
    }

    private void loadRecents() {
        Disposable disposable = recentRepository.getAllRecents()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<List<Recents>>() {
                    @Override
                    public void accept(List<Recents> recents) throws Exception {
                        onGetAllRecentsSuccess(recents);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.e("ERROR", throwable.getMessage() );
                    }
                });

        compositeDisposable.add(disposable);
    }

    private void onGetAllRecentsSuccess(List<Recents> recents) {
        recentList.clear();
        recentList.addAll(recents);
        adapter.notifyDataSetChanged();

    }

    @Override
    public void onDestroyOptionsMenu() {
        compositeDisposable.clear();
        super.onDestroyOptionsMenu();
    }
}
