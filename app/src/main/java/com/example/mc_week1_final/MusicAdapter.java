package com.example.mc_week1_final;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.nio.file.LinkOption;
import java.util.ArrayList;
import java.util.List;

public class MusicAdapter extends RecyclerView.Adapter<MusicAdapter.MusicView> {

    List<Integer> imageList=new ArrayList<>();
    List<String> titleList=new ArrayList<>();
    List<String> nameList=new ArrayList<>();
    Context context;
    BottomSheetBehavior bottomSheetBehavior;


    public MusicAdapter(Context context, List<Integer> imageList,List<String> titleList, List<String> nameList) {
        this.imageList = imageList;
        this.titleList=titleList;
        this.nameList=nameList;
        this.context=context;
    }

    public class MusicView extends RecyclerView.ViewHolder{
        ImageView imageMusic;
        TextView musicTitle, musicName;
        RelativeLayout relativeLayout;
        LinearLayout linearLayout;

        public MusicView(@NonNull final View itemView){
            super(itemView);

            imageMusic=(ImageView)itemView.findViewById(R.id.image_music);
            musicTitle=(TextView)itemView.findViewById(R.id.music_title);
            musicName=(TextView)itemView.findViewById(R.id.music_name);

            relativeLayout=itemView.findViewById(R.id.relative_layout);

            linearLayout=itemView.findViewById(R.id.bottom_sheet_dialog);
        }
    }

    @NonNull
    @Override
    public MusicView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.row_music,parent,false);
        return new MusicView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MusicView holder, final int position) {
        holder.imageMusic.setImageResource(imageList.get(position));
        holder.musicTitle.setText(titleList.get(position));
        holder.musicName.setText(nameList.get(position));

        //set onClickListener
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String songName=titleList.get(position);

                BottomSheetDialog bottomSheetDialog=new BottomSheetDialog(context,R.style.BottomSheetDialogTheme);
                View bottomSheetView=LayoutInflater.from(context).inflate(R.layout.bottom_sheet_dialog, holder.linearLayout);
                bottomSheetDialog.setContentView(bottomSheetView);
                bottomSheetDialog.show();

                /*//create intent
                Intent intent=new Intent(context,PlayerActivity.class);
                //intent.putExtra("songs",mySongs);
                intent.putExtra("songname",songName)
                        .putExtra("pos",position);
                context.startActivity(intent);*/
            }
        });
    }

    @Override
    public int getItemCount() {
        return imageList.size();
    }
}
