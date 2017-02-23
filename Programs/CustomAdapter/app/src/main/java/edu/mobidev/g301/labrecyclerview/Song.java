package edu.mobidev.g301.labrecyclerview;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ngoc on 1/27/2016.
 */
public class Song implements Parcelable {
    private String title;
    private String lyrics;
    private int resourceId;



    public Song(){

    }

    public Song(String title, String lyrics, int resourceId) {
        this.title = title;
        this.lyrics = lyrics;
        this.resourceId = resourceId;
    }

    protected Song(Parcel in) {
        title = in.readString();
        lyrics = in.readString();
        resourceId = in.readInt();
    }

    public static final Creator<Song> CREATOR = new Creator<Song>() {
        @Override
        public Song createFromParcel(Parcel in) {
            return new Song(in);
        }

        @Override
        public Song[] newArray(int size) {
            return new Song[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLyrics() {
        return lyrics;
    }

    public void setLyrics(String lyrics) {
        this.lyrics = lyrics;
    }

    public int getResourceId() {
        return resourceId;
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(lyrics);
        dest.writeInt(resourceId);
    }
}
