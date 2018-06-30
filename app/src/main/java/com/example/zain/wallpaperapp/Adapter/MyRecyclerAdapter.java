package com.example.zain.wallpaperapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zain.wallpaperapp.Common.Common;
import com.example.zain.wallpaperapp.Database.Recents;
import com.example.zain.wallpaperapp.Interface.ItemClickListener;
import com.example.zain.wallpaperapp.ListWallpaper;
import com.example.zain.wallpaperapp.Model.WallpaperItem;
import com.example.zain.wallpaperapp.R;
import com.example.zain.wallpaperapp.ViewHolder.ListWallpaperViewHolder;
import com.example.zain.wallpaperapp.ViewWallpaper;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Zain on 5/19/2018.
 */

public class MyRecyclerAdapter extends RecyclerView.Adapter<ListWallpaperViewHolder> {

    private Context context;
    private List<Recents> recents;

    public MyRecyclerAdapter(Context context, List<Recents> recents) {
        this.context = context;
        this.recents = recents;
    }

    @Override
    public ListWallpaperViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_wallpaper_item, parent, false);

        int height = parent.getMeasuredHeight()/2;
        Log.e("WOW", "onCreateViewHolder: " + height );
        itemView.setMinimumHeight(height);
        return  new ListWallpaperViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ListWallpaperViewHolder holder, int position) {
        Picasso.with(context)
                .load(recents.get(position).getImageLink())
                .networkPolicy(NetworkPolicy.OFFLINE)
                .into(holder.wallpaper, new Callback() {
                    @Override
                    public void onSuccess() {
                        Log.e("ERR", "ListWallpaperView 2  " + holder.wallpaper.getHeight());
                    }

                    @Override
                    public void onError() {
                        Picasso.with(context)
                                .load(recents.get(holder.getAdapterPosition()).getImageLink())
                                .error(R.drawable.ic_terrain_black_24dp)
                                .into(holder.wallpaper, new Callback() {
                                    @Override
                                    public void onSuccess() {
                                        //Log.e("ERR", "ListWallpaperView 2  " + holder.wallpaper.getMaxHeight());
                                    }

                                    @Override
                                    public void onError() {
                                        Picasso.with(context)
                                                .load(recents.get(holder.getAdapterPosition()).getImageLink())
                                                .error(R.drawable.ic_terrain_black_24dp)
                                                .into(holder.wallpaper, new Callback() {
                                                    @Override
                                                    public void onSuccess() {

                                                    }

                                                    @Override
                                                    public void onError() {
                                                        Log.e("ERR", "Help me!");
                                                    }
                                                });
                                    }
                                });
                    }
                });

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                Intent intent = new Intent(context, ViewWallpaper.class);
                WallpaperItem wallpaperItem = new WallpaperItem();
                wallpaperItem.setCategoryId(recents.get(position).getCategoryId());
                wallpaperItem.setImageLink(recents.get(position).getImageLink());
                Common.select_background = wallpaperItem;
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return recents.size();
    }
}
