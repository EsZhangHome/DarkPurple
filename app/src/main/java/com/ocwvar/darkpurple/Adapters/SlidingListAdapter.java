package com.ocwvar.darkpurple.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ocwvar.darkpurple.Bean.SongItem;
import com.ocwvar.darkpurple.R;

import java.util.ArrayList;

/**
 * Created by 区成伟
 * Package: com.ocwvar.darkpurple.Adapters
 * Data: 2016/8/3 1:12
 * Project: DarkPurple
 * 侧滑菜单适配器
 */
public final class SlidingListAdapter extends RecyclerView.Adapter {

    private ArrayList<SongItem> songItems;
    private OnSlidingMenuClickCallback callback;

    public void setCallback(OnSlidingMenuClickCallback callback) {
        this.callback = callback;
    }

    public void setSongItems(ArrayList<SongItem> songItems) {
        this.songItems = songItems;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SlideMusicViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_slide, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final SlideMusicViewHolder slideMusicViewHolder = (SlideMusicViewHolder) holder;
        final SongItem songItem = songItems.get(position);

        slideMusicViewHolder.title.setText(songItem.getTitle());
        slideMusicViewHolder.message.setText(songItem.getArtist());
    }

    @Override
    public int getItemCount() {
        if (songItems != null) {
            return songItems.size();
        } else {
            return 0;
        }
    }

    public interface OnSlidingMenuClickCallback {

        /**
         * 侧滑菜单点击事件
         *
         * @param songItem 歌曲对象
         * @param position 歌曲索引
         */
        void onSlidingMenuClick(@NonNull SongItem songItem, @NonNull int position);

    }

    private class SlideMusicViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        final TextView title, message;

        SlideMusicViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            message = itemView.findViewById(R.id.message);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            callback.onSlidingMenuClick(songItems.get(getAdapterPosition()), getAdapterPosition());
        }

    }

}
