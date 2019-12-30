package com.example.mc_week1_final;

import android.content.ContentResolver;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {

    private ArrayList<ContactItem> mData;
    private Context mContext;
    private LayoutInflater inflater;

    // 생성자에서 Context, 데이터 List 객체 전달받음
    public ContactAdapter(Context context, ArrayList<ContactItem> list) {
        mContext = context;
        mData = list;
    }

    // contact_item뷰 -> ViewHolder 객체
    @Override
    public ContactAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.contact_item, parent, false);
        ContactAdapter.ViewHolder vh = new ContactAdapter.ViewHolder(view);
        return vh;
    }

    // position에 해당하는 데이터를 Viewholder에 연결.
    @Override
    public void onBindViewHolder(@NonNull ContactAdapter.ViewHolder holder , int position ) {
        ContactItem item = mData.get(position);
        holder.name.setText(item.getName());
        holder.phone.setText(item.getPhone_num());
    }

    // 전체 개수 리턴
    @Override
    public int getItemCount() {
        return mData.size();
    }

    // ViewHolder 클래스
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView pics;
        TextView name;
        TextView phone;

        ViewHolder(View itemView) {
            super(itemView);

            pics = itemView.findViewById(R.id.contact_pics) ;
            name = itemView.findViewById(R.id.contact_name);
            phone = itemView.findViewById(R.id.contact_phone);
        }
    }
}
