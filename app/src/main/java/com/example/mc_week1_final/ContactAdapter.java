package com.example.mc_week1_final;

import android.content.ContentResolver;
import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> implements Filterable {

    private Context mContext;
    private LayoutInflater inflater;
    private ArrayList<ContactItem> filteredList;    // 연락처 데이터 리스트(filter 입력값 없으면 전체)
    private ArrayList<ContactItem> unfilteredList;

    // 생성자에서 Context, 데이터 List 객체 전달받음
    public ContactAdapter(Context context, ArrayList<ContactItem> list) {
        mContext = context;
        filteredList = list;
        unfilteredList = list;
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
        ContactItem item = filteredList.get(position);
        holder.name.setText(item.getName());
        holder.phone.setText(item.getPhone_num());
    }

    // 전체 개수 리턴
    @Override
    public int getItemCount() {
        return filteredList.size();
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

    // 검색 getFilter 함수
    @Override
    public Filter getFilter() {
        return new Filter() {       // performFiltering, publishResults 필수
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String charString = constraint.toString();      // 입력받은 값 string으로 변경
                if (charString.isEmpty()) {
                    filteredList = unfilteredList;
                }             // 검색값 없으면, 전체 연락처
                else {
                    ArrayList<ContactItem> filteringList = new ArrayList<>();   // 필터링 중, 검색된 연락처 저장할 변수
                    for (ContactItem name : unfilteredList) {                    // 반복문으로 전체 필터 체크
                        if (name.getName().toLowerCase().contains(charString.toLowerCase())) {
                            filteringList.add(name);
                        }
                    }
                    filteredList = filteringList;       // 검색된 리스트ㄱ
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = filteredList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                filteredList = (ArrayList<ContactItem>) results.values;
                notifyDataSetChanged();
            }
        };
    }

}
