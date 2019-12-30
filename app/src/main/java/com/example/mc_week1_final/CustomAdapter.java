package com.example.mc_week1_final;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends BaseAdapter{
    private LayoutInflater layoutInflater;
    private List<ItemObject> listStorage;
    private Context context;

    public CustomAdapter(Context context, List<ItemObject> customizedList){
        this.context=context;
        layoutInflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        listStorage=customizedList;
    }

    //itemObject-> viewHolder 객체
    /*public CustomAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        layoutInflater=LayoutInflater.from(context);
        View view=layoutInflater.inflate(R.layout.pop_music_list,parent,false);
        CustomAdapter.ViewHolder vh=new CustomAdapter.ViewHolder(view);
        return vh;
    }

    //position에 해당하는 데이터를 viewholder에 연결
    public void onBindViewHolder(@NonNull CustomAdapter.ViewHolder holder, int position){
        ItemObject itemObject=listStorage.get(position);
        holder.musicName.setText(itemObject.getMusicName());
        holder.musicAuthor.setText(itemObject.getMusicAuthor());
    }*/


    @Override
    public int getCount() {
        return listStorage.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    /*@Override
    public int getItemCount() {
        return listStorage.size();
    }*/

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder listViewHolder;
        View view = new View(context);;

        if(convertView==null){
            view=layoutInflater.inflate(R.layout.pop_music_list, parent, false);
            listViewHolder=new ViewHolder(view);
            listViewHolder.screenShot = (ImageView)view.findViewById(R.id.screen_shot);
            listViewHolder.musicName = (TextView)view.findViewById(R.id.music_name);
            listViewHolder.musicAuthor = (TextView)view.findViewById(R.id.music_author);

            view.setTag(listViewHolder);
        }
        else{
            listViewHolder=(ViewHolder)view.getTag();
        }
        listViewHolder.screenShot.setImageResource(listStorage.get(position).getScreenShot());
        listViewHolder.musicName.setText(listStorage.get(position).getMusicName());
        listViewHolder.musicAuthor.setText(listStorage.get(position).getMusicAuthor());

        return view;
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView screenShot;
        TextView musicName;
        TextView musicAuthor;

        ViewHolder(View itemView){
            super(itemView);

            screenShot=itemView.findViewById(R.id.screen_shot);
            musicName=itemView.findViewById(R.id.music_name);
            musicAuthor=itemView.findViewById(R.id.music_author);
        }
    }
}
