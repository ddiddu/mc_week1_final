package com.example.mc_week1_final;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;

import static com.example.mc_week1_final.MusicAdapter.getAlbumImage;

public class PlayerActivity extends AppCompatActivity {

    Button btn_next, btn_previous, btn_pause;
    ImageView albumImageLabel;
    TextView songTextLabel, artistTextLabel;
    SeekBar songSeekbar;

    static MediaPlayer myMediaPlayer;
    int position;

    ArrayList<File> mySongs;
    Thread updateseekBar;

    String sname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        btn_next = (Button)findViewById(R.id.next);
        btn_previous = (Button)findViewById(R.id.previous);
        btn_pause = (Button)findViewById(R.id.pause);

        albumImageLabel=(ImageView)findViewById(R.id.albumLabel);
        songTextLabel = (TextView)findViewById(R.id.songLabel);
        artistTextLabel = (TextView)findViewById(R.id.artistLabel);
        songSeekbar = (SeekBar)findViewById(R.id.seekBar);

        updateseekBar = new Thread() {
            @Override
            public void run() {
                int totalDuration = myMediaPlayer.getDuration();
                int currentPosition = 0;

                while (currentPosition < totalDuration) {
                    try {
                        sleep(500);
                        currentPosition = myMediaPlayer.getCurrentPosition();
                        songSeekbar.setProgress(currentPosition);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        if (myMediaPlayer != null) {
            myMediaPlayer.stop();
            myMediaPlayer.release();
        }

        Intent i = getIntent();
        Bundle bundle = i.getExtras();

        String albumImage=i.getStringExtra("albumImage");
        String songName = i.getStringExtra("songName");
        String artistName = i.getStringExtra("artistName");

        // album_id로부터 사진 불러오기 (albumart)
        Bitmap album_image = getAlbumImage(getApplicationContext(), Integer.parseInt((albumImage)),170);
        if(album_image != null) {
            albumImageLabel.setImageBitmap(album_image);
        }
        else {    // 이미지 없을 경우
            albumImageLabel.setImageResource(R.drawable.no_album_img);
        }

        songTextLabel.setText(songName);
        songTextLabel.setSelected(true);
        artistTextLabel.setText(artistName);
        artistTextLabel.setSelected(true);

        mySongs=(ArrayList)bundle.getParcelableArrayList("songs");

        /*sname=mySongs.get(position).getName().toString();

        position= bundle.getInt("pos",0);

        Uri u=Uri.parse(mySongs.get(position).toString());

        myMediaPlayer=MediaPlayer.create(getApplicationContext(),u);

        myMediaPlayer.start();
        songSeekbar.setMax(myMediaPlayer.getDuration());

        updateseekBar.start();

        songSeekbar.getProgressDrawable().setColorFilter(getResources().getColor(R.color.colorAccent), PorterDuff.Mode.MULTIPLY);
        songSeekbar.getThumb().setColorFilter(getResources().getColor(R.color.colorAccent),PorterDuff.Mode.SRC_IN);

        songSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                myMediaPlayer.seekTo(seekBar.getProgress());
            }
        });

        btn_pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                songSeekbar.setMax(myMediaPlayer.getDuration());

                if(myMediaPlayer.isPlaying()){
                    btn_pause.setBackgroundResource(R.drawable.ic_play);
                    myMediaPlayer.pause();
                }
                else{
                    btn_pause.setBackgroundResource(R.drawable.ic_pause);
                    myMediaPlayer.start();
                }
            }
        });

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myMediaPlayer.stop();
                myMediaPlayer.release();
                position=((position+1)%mySongs.size());

                Uri u=Uri.parse(mySongs.get(position).toString());

                myMediaPlayer=MediaPlayer.create(getApplicationContext(),u);

                sname=mySongs.get(position).getName().toString();
                songTextLabel.setText(sname);

                myMediaPlayer.start();
            }
        });

        btn_previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myMediaPlayer.stop();
                myMediaPlayer.release();

                position=((position-1)<0)?(mySongs.size()-1):(position-1);
                Uri u=Uri.parse(mySongs.get(position).toString());
                myMediaPlayer=MediaPlayer.create(getApplicationContext(),u);

                sname=mySongs.get(position).getName().toString();
                songTextLabel.setText(sname);

                myMediaPlayer.start();
            }
        });*/
    }
    }

