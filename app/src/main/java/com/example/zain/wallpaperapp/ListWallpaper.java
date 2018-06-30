package com.example.zain.wallpaperapp;

import android.content.Intent;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.zain.wallpaperapp.Common.Common;
import com.example.zain.wallpaperapp.Interface.ItemClickListener;
import com.example.zain.wallpaperapp.Model.WallpaperItem;
import com.example.zain.wallpaperapp.ViewHolder.ListWallpaperViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

public class ListWallpaper extends AppCompatActivity {

    Query query;
    FirebaseRecyclerAdapter<WallpaperItem, ListWallpaperViewHolder> adapter;
    FirebaseRecyclerOptions<WallpaperItem> options;

    RecyclerView recyclerView;
    GridLayoutManager gridLayoutManager;
    int state;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_wallpaper);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(Common.CATEGORY_SELECTED);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        recyclerView = findViewById(R.id.recycler_list_wallpaper);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemViewCacheSize(20);
        recyclerView.setDrawingCacheEnabled(true);
        recyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        gridLayoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(gridLayoutManager);

        loadBackgroundList();

    }




    private void loadBackgroundList() {
        query = FirebaseDatabase.getInstance().getReference(Common.STR_WALLPAPER)
                .orderByChild("categoryId").equalTo(Common.CATEGORY_ID_SELECTED);

        options = new FirebaseRecyclerOptions.Builder<WallpaperItem>()
                .setQuery(query, WallpaperItem.class)
                .build();

        adapter = new FirebaseRecyclerAdapter<WallpaperItem, ListWallpaperViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull final ListWallpaperViewHolder holder, int position, @NonNull final WallpaperItem model) {
                Picasso.with(getBaseContext())
                        .load(model.getImageLink())
                        .networkPolicy(NetworkPolicy.OFFLINE)
                        .into(holder.wallpaper, new Callback() {
                            @Override
                            public void onSuccess() {
                                //Log.e("ERR", "ListWallpaperView 2  " + holder.wallpaper.getHeight());
                            }

                            @Override
                            public void onError() {
                                Picasso.with(getBaseContext())
                                        .load(model.getImageLink())
                                        .error(R.drawable.ic_terrain_black_24dp)
                                        .into(holder.wallpaper, new Callback() {
                                            @Override
                                            public void onSuccess() {
                                                //Log.e("ERR", "ListWallpaperView 2  " + holder.wallpaper.getMaxHeight());
                                            }

                                            @Override
                                            public void onError() {
                                                Picasso.with(getBaseContext())
                                                        .load(model.getImageLink())
                                                        .error(R.drawable.ic_terrain_black_24dp)
                                                        .into(holder.wallpaper, new Callback() {
                                                            @Override
                                                            public void onSuccess() {

                                                            }

                                                            @Override
                                                            public void onError() {
                                                                Picasso.with(getBaseContext())
                                                                        .load(model.getImageLink())
                                                                        .error(R.drawable.ic_terrain_black_24dp)
                                                                        .into(holder.wallpaper, new Callback() {
                                                                            @Override
                                                                            public void onSuccess() {

                                                                            }

                                                                            @Override
                                                                            public void onError() {
                                                                                Picasso.with(getBaseContext())
                                                                                        .load(model.getImageLink())
                                                                                        .error(R.drawable.icon)
                                                                                        .into(holder.wallpaper, new Callback() {
                                                                                            @Override
                                                                                            public void onSuccess() {

                                                                                            }

                                                                                            @Override
                                                                                            public void onError() {
                                                                                                //Log.e("OOPS", "Unable to load image in listwallpaper");
                                                                                            }
                                                                                        });
                                                                            }
                                                                        });
                                                            }
                                            });
                                            }
                                        });
                            }
                        });

                holder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position) {
                        Intent intent = new Intent(ListWallpaper.this, ViewWallpaper.class);
                        Common.select_background = model;
                        startActivity(intent);
                    }
                });
            }

            @Override
            public ListWallpaperViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View itemView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.layout_wallpaper_item, parent, false);

                int height = parent.getMeasuredHeight()/2;
                //Log.e("WOW", "onCreateViewHolder: " + height );
                itemView.setMinimumHeight(height);
                return  new ListWallpaperViewHolder(itemView);
            }
        };

        adapter.startListening();
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        Log.e("START", "");
        super.onStart();
        recyclerView.onScrollStateChanged(state);
        if(adapter != null)
            adapter.startListening();
    }

    @Override
    protected void onPause() {
        super.onPause();
        state = recyclerView.getScrollState();
    }

    @Override
    protected void onStop() {
        if (adapter != null)
            adapter.stopListening();
        super.onStop();
        state = recyclerView.getScrollState();
    }

    @Override
    protected void onResume() {
        recyclerView.onScrollStateChanged(state);
        if(adapter != null)
            adapter.startListening();
        super.onResume();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }
}
