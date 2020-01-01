package com.example.mc_week1_final;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;

import com.r0adkll.slidr.Slidr;
import com.r0adkll.slidr.model.SlidrConfig;
import com.r0adkll.slidr.model.SlidrInterface;
import com.r0adkll.slidr.model.SlidrPosition;

import java.util.ArrayList;

public class FullScreenActivity3 extends AppCompatActivity {

    ViewPager viewPager;

    private SlidrInterface slidr;
    private SlidrConfig config= new SlidrConfig.Builder()
            .position(SlidrPosition.TOP)
            .build();

    ArrayList<ImageItem> myImages;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen3);

        slidr=Slidr.attach(this,config);
        slidr.unlock();

        viewPager=(ViewPager)findViewById(R.id.myViewPager);

        Toolbar toolbar=findViewById(R.id.photo_toolBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(" ");

        Intent i=getIntent();
        Bundle bundle = i.getExtras();

        myImages = i.getParcelableArrayListExtra("myImages");
        id= i.getExtras().getInt("id");


        ViewPagerAdapter viewPagerAdapter=new ViewPagerAdapter(this, myImages);

        int ID = getIntent().getIntExtra("id",1);
        
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.setCurrentItem(ID);
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
                sharingIntent.setType("image/jpeg");

                String idColumn=myImages.get(id).getIdColum();
                Uri uri = Uri.parse("content://media/external/images/media/" + idColumn);

                sharingIntent.putExtra(Intent.EXTRA_STREAM, uri);

                startActivity(Intent.createChooser(sharingIntent,"Share Using"));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
