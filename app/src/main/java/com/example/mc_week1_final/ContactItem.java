package com.example.mc_week1_final;

// 연락처 클래스
public class ContactItem {
    private String phone_num, name;
    private long photo_id=0, person_id=0;   // 사진
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
    public ContactItem() {}

    @Override
    public String toString() {
        return this.phone_num;
    }

    @Override
    public int hashCode() {
        return getPhone_numChanged().hashCode();

    }


    // ????
    public String getPhone_numChanged() {
        return phone_num.replace("-", "");
    }
    @Override
    public boolean equals(Object o) {
        if (o instanceof ContactItem)
            return getPhone_numChanged().equals(((ContactItem) o).getPhone_numChanged());
        return false;
    }

}
