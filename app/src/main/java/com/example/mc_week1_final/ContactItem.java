package com.example.mc_week1_final;

// 연락처 클래스
public class ContactItem {
    private String phone_num, name;
    private long photo_id=0, person_id=0;   // 프로필 사진
    private int id;

    public void setName(String Name) { name = Name; }
    public void setPhone_num(String Phone) { phone_num = Phone; }
    public void setPhoto_id(long Photo_id) { photo_id = Photo_id; }
    public void setPerson_id(long Person_id) { person_id = Person_id; }

    public String  getName() { return this.name; }
    public String  getPhone_num() { return this.phone_num; }
    public long  getPhoto_id() { return this.photo_id; }
    public long  getPerson_id() { return this.person_id; }

    public void setId(int id) {
        this.id = id;
    }

}
