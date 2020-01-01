package com.example.mc_week1_final;

import java.util.ArrayList;
import java.util.List;

// 음악 클래스
public class MusicItem {

    private String id, title, artist, album_id;
    private int item_id;

    public void setTitle(String Title) {
        this.title = Title;
    }
    public void setArtist(String Artist) {
        this.artist = Artist;
    }
    public void setAlbum_id(String Album_id) {
        this.album_id = Album_id;
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

    public void setItem_id(int Item_id) {
        this.item_id = Item_id;
    }


}