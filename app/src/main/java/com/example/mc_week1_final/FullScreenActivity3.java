package com.example.mc_week1_final;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

public class FullScreenActivity3 extends AppCompatActivity {

    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen3);

        viewPager=(ViewPager)findViewById(R.id.myViewPager);

        ViewPagerAdapter viewPagerAdapter=new ViewPagerAdapter(this);

        int ID = getIntent().getIntExtra("id",1);
        
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.setCurrentItem(ID);
    }
}
