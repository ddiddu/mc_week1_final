package com.example.mc_week1_final;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.MotionEvent;

import com.r0adkll.slidr.Slidr;
import com.r0adkll.slidr.model.SlidrConfig;
import com.r0adkll.slidr.model.SlidrInterface;
import com.r0adkll.slidr.model.SlidrPosition;

public class FullScreenActivity3 extends AppCompatActivity {

    ViewPager viewPager;

    private SlidrInterface slidr;
    private SlidrConfig config= new SlidrConfig.Builder()
            .position(SlidrPosition.TOP)
            .build();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen3);

        slidr=Slidr.attach(this,config);
        slidr.unlock();

        viewPager=(ViewPager)findViewById(R.id.myViewPager);

        ViewPagerAdapter viewPagerAdapter=new ViewPagerAdapter(this);

        int ID = getIntent().getIntExtra("id",1);
        
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.setCurrentItem(ID);
    }

}
