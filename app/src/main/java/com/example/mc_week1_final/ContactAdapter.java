package com.example.mc_week1_final;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {
    private ArrayList<ContactListItem> mData = null;

    public ContactAdapter(ArrayList<ContactListItem> list) {
        mData = list;
    }

    // item 뷰 위한 ViewHolder 객체 생성, 리턴
    @Override
    public ContactAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_item, parent, false);
        ContactAdapter.ViewHolder vh = new ContactAdapter.ViewHolder(view);
        return vh;
    }

    // position에 해당하는 데이터를 Viewholder의 itemView에 표시.
    @Override
    public void onBindViewHolder(@NonNull ContactAdapter.ViewHolder holder , int position ) {

        ContactListItem item = mData.get(position);

        // holder.pics.setImageDrawable(item.getPics());
        holder.name.setText(item.getName());
        holder.phone.setText(item.getPhone());
}

    // 전체 개수 리턴
    @Override
    public int getItemCount() {
        return mData.size();
    }

    // item 뷰 저장하는 ViewHolder 클래스
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView pics;
        TextView name;
        TextView phone;

        ViewHolder(View itemView) {
            super(itemView);

            // 뷰 객체에 대한 참조
            pics = itemView.findViewById(R.id.contact_pics) ;
            name = itemView.findViewById(R.id.contact_name);
            phone = itemView.findViewById(R.id.contact_phone);
        }
    }
}
