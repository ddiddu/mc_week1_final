package com.example.mc_week1_final;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

// 음악 클래스
public class MusicItem implements Parcelable {

    private String id, title, artist, album_id , datapath;
    private int item_id;

    // data 직렬화
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(title);
        dest.writeString(artist);
        dest.writeString(album_id);
        dest.writeString(datapath);
    }

    public MusicItem() {
    }

    // PlayerActivity가 객체 받았을 때 직렬화 풀어줌
    public MusicItem(Parcel in) {
        this.id = in.readString();
        this.title = in.readString();
        this.artist = in.readString();
        this.album_id = in.readString();
        this.datapath = in.readString();
    }

    // Creator
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        @Override
        public MusicItem createFromParcel(Parcel in) {
            return new MusicItem(in);
        }

        @Override
        public MusicItem[] newArray(int size) {
            return new MusicItem[size];
        }

    };

    @Override
    public int describeContents() {
        return 0;
    }

    public void setTitle(String Title) {
        this.title = Title;
    }
    public void setArtist(String Artist) {
        this.artist = Artist;
    }
    public void setAlbum_id(String Album_id) {
        this.album_id = Album_id;
    }
    public void setDatapath(String Datapath) {
        this.datapath = Datapath;
    }
    public void setId(String Id) {
        this.id = Id;
    }


    public String  getTitle() {
        return this.title;
    }
    public String  getArtist() {
        return this.artist;
    }
    public String  getAlbum_id() {
        return this.album_id;
    }
    public String  getId() {
        return this.id;
    }
    public  String  getDatapath() {
        return this.datapath;
    }

    public void setItem_id(int Item_id) {
        this.item_id = Item_id;
    }



}