package com.example.mc_week1_final;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.r0adkll.slidr.Slidr;
import com.r0adkll.slidr.model.SlidrConfig;
import com.r0adkll.slidr.model.SlidrInterface;
import com.r0adkll.slidr.model.SlidrPosition;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static com.example.mc_week1_final.MusicAdapter.getAlbumImage;

public class PlayerActivity extends AppCompatActivity {

    Button btn_next, btn_previous, btn_pause;
    ImageView albumImageLabel;
    TextView songTextLabel, artistTextLabel;
    SeekBar songSeekbar, volumeSeekBar;

    MediaPlayer myMediaPlayer;
    int position;

    ArrayList<MusicItem> mySongs;

    // View 값
    String sname;
    String artist;
    String album_id;

    private SlidrInterface slidr;
    private SlidrConfig config= new SlidrConfig.Builder()
            .position(SlidrPosition.TOP)
            .build();


    // seekbar -> class로 thread
    boolean isPlaying = false;   // thread 플래그(시작, 정지 구분)

    class updateseekBar extends Thread {
        @Override
        public void run() {
            int currentPosition;

            while (isPlaying) {
                try {
                    sleep(100);
                    currentPosition = myMediaPlayer.getCurrentPosition();
                    songSeekbar.setProgress(currentPosition);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        slidr=Slidr.attach(this,config);
        slidr.unlock();

        btn_next = (Button)findViewById(R.id.next);
        btn_previous = (Button)findViewById(R.id.previous);
        btn_pause = (Button)findViewById(R.id.pause);

        albumImageLabel=(ImageView)findViewById(R.id.albumLabel);
        songTextLabel = (TextView)findViewById(R.id.songLabel);
        artistTextLabel = (TextView)findViewById(R.id.artistLabel);
        songSeekbar = (SeekBar)findViewById(R.id.seekBar);
        //volumeSeekBar=(SeekBar)findViewById(R.id.volumeBar);

        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Now Playing");

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
        Bitmap album_image = getAlbumImage(getApplicationContext(), Integer.parseInt((albumImage)));
        if (album_image != null) {
            albumImageLabel.setImageBitmap(album_image);
        } else {    // 이미지 없을 경우
            albumImageLabel.setImageResource(R.drawable.no_album_img);
        }

        // 선택된 노래 재생
        set_datapath(dataPath);
        play();

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

            // 드래그 멈추면
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                isPlaying = true;
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
                    isPlaying = false;
                }
                else{
                    btn_pause.setBackgroundResource(R.drawable.ic_pause);
                    myMediaPlayer.start();
                    isPlaying = true;
                    new updateseekBar().start();
                }
            }
        });

        // next 클릭
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myMediaPlayer.stop();
                myMediaPlayer.reset();
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
                Bitmap album_image = getAlbumImage(getApplicationContext(), Integer.parseInt((album_id)));
                if (album_image != null) {
                    albumImageLabel.setImageBitmap(album_image);
                } else {    // 이미지 없을 경우
                    albumImageLabel.setImageResource(R.drawable.no_album_img);
                }
                new updateseekBar().start();
            }
        });


        // previous 클릭
        btn_previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_pause.setBackgroundResource(R.drawable.ic_pause);

                myMediaPlayer.stop();
                myMediaPlayer.reset();
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
                Bitmap album_image = getAlbumImage(getApplicationContext(), Integer.parseInt((album_id)));
                if (album_image != null) {
                    albumImageLabel.setImageBitmap(album_image);
                } else {    // 이미지 없을 경우
                    albumImageLabel.setImageResource(R.drawable.no_album_img);
                }
                new updateseekBar().start();
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
        if(myMediaPlayer.isPlaying()){
            myMediaPlayer.pause();
            isPlaying = false;
        }
        isPlaying = false;

        myMediaPlayer.reset();
        myMediaPlayer.release();
        updateseekBar.interrupted();
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

                // seekbar 시작
                songSeekbar.setMax(myMediaPlayer.getDuration());
                new updateseekBar().start();
                isPlaying = true;
            }
        });
        myMediaPlayer.prepareAsync();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch(item.getItemId()){

            case R.id.share_button:
                Intent sharingIntent=new Intent(Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody=mySongs.get(position).getArtist().toString();
                String shareSubject=mySongs.get(position).getTitle().toString();

                sharingIntent.putExtra(Intent.EXTRA_TEXT,shareBody);
                sharingIntent.putExtra(Intent.EXTRA_SUBJECT,shareSubject);

                startActivity(Intent.createChooser(sharingIntent,"Share Using"));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
