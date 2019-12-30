package com.example.mc_week1_final;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MusicAdapter extends RecyclerView.Adapter<MusicAdapter.MusicView> {

    List<Integer> imageList=new ArrayList<>();
    List<String> titleList=new ArrayList<>();
    List<String> nameList=new ArrayList<>();

    public MusicAdapter(List<Integer> imageList,List<String> titleList, List<String> nameList) {
        this.imageList = imageList;
        this.titleList=titleList;
        this.nameList=nameList;
    }

    public class MusicView extends RecyclerView.ViewHolder{
        ImageView imageMusic;
        TextView musicTitle, musicName;

        public MusicView(@NonNull View itemView){
            super(itemView);

            imageMusic=(ImageView)itemView.findViewById(R.id.image_music);
            musicTitle=(TextView)itemView.findViewById(R.id.music_title);
            musicName=(TextView)itemView.findViewById(R.id.music_name);
        }
    }

    @NonNull
    @Override
    public MusicView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.row_music,parent,false);

        return new MusicView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MusicView holder, int position) {
        holder.imageMusic.setImageResource(imageList.get(position));
        holder.musicTitle.setText(titleList.get(position));
        holder.musicName.setText(nameList.get(position));
    }

    @Override
    public int getItemCount() {
        return imageList.size();
    }
}
