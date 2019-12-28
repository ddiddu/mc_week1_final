package com.example.mc_week1_final;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.Menu;
import android.widget.TableLayout;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar, toolbartab;
    ViewPager viewPager;
    TabLayout tabLayout;

    PageAdapter pageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //toolbar=(Toolbar) findViewById(R.id.toolbar); //맨위에 toolbar 지정
        toolbartab=(Toolbar) findViewById(R.id.toolbartab);
        viewPager=(ViewPager)findViewById(R.id.viewpager);
        tabLayout=(TabLayout)findViewById(R.id.tablayout);

        setSupportActionBar(toolbar);

        pageAdapter=new PageAdapter(getSupportFragmentManager());
        pageAdapter.addFragment(new ContactFragment(), "연락처");
        pageAdapter.addFragment(new PhotoFragment(), "사진");
        pageAdapter.addFragment(new tab3(), "tab3");

        viewPager.setAdapter(pageAdapter);

        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }
}
