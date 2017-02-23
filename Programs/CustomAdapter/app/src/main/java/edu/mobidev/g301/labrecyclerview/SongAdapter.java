package edu.mobidev.g301.labrecyclerview;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by G301 on 1/26/2016.
 */
public class SongAdapter
        extends RecyclerView.Adapter<SongAdapter.SongHolder>{

    ArrayList<Song> songs;

    public SongAdapter(ArrayList<Song> songs){
        this.songs = songs;
    }

    public class SongHolder extends RecyclerView.ViewHolder{
        TextView tvTitle;
        ImageView ivGenre;
        View container;
        public SongHolder(View itemView) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_songtitle);
            ivGenre = (ImageView) itemView.findViewById(R.id.iv_genre);
            container = itemView.findViewById(R.id.container);
        }
    }

    @Override
    public SongHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.list_item, null);

        return new SongHolder(v);
    }

    @Override
    public void onBindViewHolder(SongHolder songHolder, int position) {
        // TODO: change textView's text to current song
        Song song = songs.get(position);
        songHolder.tvTitle.setText(song.getTitle());
        songHolder.ivGenre.setImageResource(song.getResourceId());
        songHolder.container.setTag(R.id.key_item_holder, songHolder);
        songHolder.container.setTag(R.id.key_item_song, song);

        songHolder.container.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                int position = ((SongHolder)v.getTag(R.id.key_item_holder)).getAdapterPosition();
                songs.remove(position);
                notifyItemRemoved(position);
                return false;
            }
        });

        songHolder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Song s = (Song) v.getTag(R.id.key_item_song);
//                v.getContext().startActivity(new Intent(v.getContext(),ViewSongActivity.class)
//                        .putExtra(MainActivity.KEY_TITLE, s.getTitle())
//                        .putExtra(MainActivity.KEY_LYRICS, s.getLyrics()));
                v.getContext().startActivity(new Intent(v.getContext(), ViewSongActivity.class)
                    .putExtra(MainActivity.KEY_SONG, s));
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.songs.size();
    }

    public void addSong(Song song){
        songs.add(song);
        notifyItemInserted(getItemCount()-1);
    }

}
