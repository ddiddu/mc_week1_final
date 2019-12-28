package com.example.mc_week1_final;

import android.graphics.drawable.Drawable;

public class ContactListItem {
    private Drawable pics;
    private String name;
    private String phone;

    // set
    public void setPics(Drawable Pics) {
        pics = Pics; }

    public void setName(String Name) {
        name = Name; }

    public void setPhone(String Phone) {
        phone = Phone; }

    // get
    public Drawable  getPics() {
        return this.pics; }

    public String  getName() {
        return this.name; }

    public String getPhone() {
        return this.phone; }


}


