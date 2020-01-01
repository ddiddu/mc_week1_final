package com.example.mc_week1_final;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static com.example.mc_week1_final.MusicAdapter.getAlbumImage;

public class PlayerActivity extends AppCompatActivity {

    Button btn_next, btn_previous, btn_pause;
    ImageView albumImageLabel;
    TextView songTextLabel, artistTextLabel;
    SeekBar songSeekbar;

    MediaPlayer myMediaPlayer;
    int position;

    ArrayList<MusicItem> mySongs;
    Thread updateseekBar;

    // View 값
    String sname;
    String artist;
    String album_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        btn_next = (Button) findViewById(R.id.next);
        btn_previous = (Button) findViewById(R.id.previous);
        btn_pause = (Button) findViewById(R.id.pause);

        albumImageLabel = (ImageView) findViewById(R.id.albumLabel);
        songTextLabel = (TextView) findViewById(R.id.songLabel);
        artistTextLabel = (TextView) findViewById(R.id.artistLabel);
        songSeekbar = (SeekBar) findViewById(R.id.seekBar);

       // myMediaPlayer = new MediaPlayer();

        Intent i = getIntent();
        Bundle bundle = i.getExtras();

        String albumImage = i.getStringExtra("albumImage");
        String songName = i.getStringExtra("songName");
        String artistName = i.getStringExtra("artistName");
        String dataPath = i.getStringExtra("dataPath");
        mySongs = i.getParcelableArrayListExtra("mySongs");
        position = i.getExtras().getInt("pos");


        songTextLabel.setText(songName);
        songTextLabel.setSelected(true);
        artistTextLabel.setText(artistName);
        artistTextLabel.setSelected(true);

        // album_id로부터 사진 불러오기 (albumart)
        Bitmap album_image = getAlbumImage(getApplicationContext(), Integer.parseInt((albumImage)), 170);
        if (album_image != null) {
            albumImageLabel.setImageBitmap(album_image);
        } else {    // 이미지 없을 경우
            albumImageLabel.setImageResource(R.drawable.no_album_img);
        }

        // 선택된 노래 재생
        set_datapath(dataPath);
        play();

        // seekbar  설정
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


        songSeekbar.setMax(myMediaPlayer.getDuration());

        updateseekBar.start();

        songSeekbar.getProgressDrawable().setColorFilter(getResources().getColor(R.color.colorAccent), PorterDuff.Mode.MULTIPLY);
        songSeekbar.getThumb().setColorFilter(getResources().getColor(R.color.colorAccent),PorterDuff.Mode.SRC_IN);



        // seekbar change 리스너
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



        // 멈춤, 재생 클릭
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

        // next 클릭
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myMediaPlayer.stop();
                myMediaPlayer.release();

                btn_pause.setBackgroundResource(R.drawable.ic_pause);

                position = ((position + 1) % mySongs.size());
                String dataPath = mySongs.get(position).getDatapath();

                set_datapath(dataPath);
                play();

                // View 값
                sname = mySongs.get(position).getTitle();
                songTextLabel.setText(sname);
                artist = mySongs.get(position).getArtist();
                artistTextLabel.setText(artist);
                album_id = mySongs.get(position).getAlbum_id();
                Bitmap album_image = getAlbumImage(getApplicationContext(), Integer.parseInt((album_id)), 170);
                if (album_image != null) {
                    albumImageLabel.setImageBitmap(album_image);
                } else {    // 이미지 없을 경우
                    albumImageLabel.setImageResource(R.drawable.no_album_img);
                }
            }
        });


        // previous 클릭
        btn_previous.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                btn_pause.setBackgroundResource(R.drawable.ic_pause);

                myMediaPlayer.stop();
                myMediaPlayer.release();

                position = ((position - 1) < 0) ? (mySongs.size() - 1) : (position - 1);
                String dataPath = mySongs.get(position).getDatapath();

                set_datapath(dataPath);
                play();
                //myMediaPlayer.start();

                // View 값
                sname = mySongs.get(position).getTitle();
                songTextLabel.setText(sname);
                artist = mySongs.get(position).getArtist();
                artistTextLabel.setText(artist);
                album_id = mySongs.get(position).getAlbum_id();
                Bitmap album_image = getAlbumImage(getApplicationContext(), Integer.parseInt((album_id)), 170);
                if (album_image != null) {
                    albumImageLabel.setImageBitmap(album_image);
                } else {    // 이미지 없을 경우
                    albumImageLabel.setImageResource(R.drawable.no_album_img);
                }
            }
        });



/*
        // 재생 완료 후 다음곡 자동 재생
        myMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                btn_next.performClick();
            }
        });


        // 재생 완료 후 다음곡 자동 재생
        myMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                System.out.println("HIHIHIHIHIIHI");
                System.out.println("끝!");


                position=((position+1)%mySongs.size());
                String dataPath = mySongs.get(position).getDatapath();

                play(dataPath);

                // View 값
                sname=mySongs.get(position).getTitle();
                songTextLabel.setText(sname);
                artist=mySongs.get(position).getArtist();
                artistTextLabel.setText(artist);
                album_id=mySongs.get(position).getAlbum_id();
                Bitmap album_image = getAlbumImage(getApplicationContext(), Integer.parseInt((album_id)), 170);
                if (album_image != null) {
                    albumImageLabel.setImageBitmap(album_image);
                } else {    // 이미지 없을 경우
                    albumImageLabel.setImageResource(R.drawable.no_album_img);
                }
            }


        });
*/


    } // oncreate 끝



    @Override
    public void onStop(){
        super.onStop();
        myMediaPlayer.release();

    }



    // 음악 play 함수
    public void set_datapath(String dataPath) {
        myMediaPlayer = new MediaPlayer();

        // 음악 play
        try {
            myMediaPlayer.setDataSource(dataPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void play(){
        // prepare 후 start 해야함 (-38,0) 오류 안나게
        myMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.start();
            }
        });
        myMediaPlayer.prepareAsync();
    }


}
