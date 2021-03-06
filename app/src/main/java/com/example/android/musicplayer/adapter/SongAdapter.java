package com.example.android.musicplayer.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.musicplayer.R;
import com.example.android.musicplayer.Song;

import java.util.List;

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.SongViewHolder> {

    /**
     * An on-click handler that we've defined to make it easy for an Activity to interface with our RecyclerView
     */
    final private ItemClickListener mItemClickListener;

    private Context mContext;
    private List<Song> mSongs;

    /**
     * The interface that receives onClick messages.
     */
    public interface ItemClickListener {
        void onItemClickListener(int itemId);
    }

    /**
     * Constructor for SongAdapter
     *
     * @param context
     * @param listener Listener for list item clicks
     */
    public SongAdapter(Context context, List<Song> songs, ItemClickListener listener) {
        mContext = context;
        mSongs = songs;
        mItemClickListener = listener;
    }

    @NonNull
    @Override
    public SongViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.list_item, parent, false);
        return new SongViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SongViewHolder holder, int position) {
        Song song = mSongs.get(position);
        holder.songTitleView.setText(song.getSongTitle());
        holder.artistNameView.setText(song.getArtistName());
        holder.songLengthView.setText(song.getSongLength());
        // Check if an album art is provided for this song or not
        if(song.hasAlbumArtId()) {
            // If an album art is available, display the provided album art based on the resource ID
            holder.albumArtImageView.setImageResource(song.getAlbumArtId());
        } else {
            // Otherwise display alternate image
            holder.albumArtImageView.setImageResource(R.drawable.notes);
        }
    }

    @Override
    public int getItemCount() {
        if (mSongs == null) {
            return 0;
        }
        return mSongs.size();
    }

    class SongViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView songTitleView;
        private TextView artistNameView;
        private TextView songLengthView;
        private ImageView albumArtImageView;

        public SongViewHolder(View itemView) {
            super(itemView);

            songTitleView = itemView.findViewById(R.id.song_title_text_view);
            artistNameView = itemView.findViewById(R.id.artist_text_view);
            songLengthView = itemView.findViewById(R.id.song_length_text_view);
            albumArtImageView = itemView.findViewById(R.id.image);
            itemView.setOnClickListener(this);
        }

        /**
         * Called whenever a user clicks on a item in the list
         * @param v The View that was clicked
         */
        @Override
        public void onClick(View v) {
            int itemId = getAdapterPosition();
            mItemClickListener.onItemClickListener(itemId);
        }
    }
}
