package com.example.mc_week1_final;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.GridView;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.SpinnerAdapter;
import android.widget.ViewSwitcher;

public class FullScreenActivity2 extends Activity implements ViewSwitcher.ViewFactory {

    Context context;
    int imageItem;
    Integer[] images={
            R.drawable.____________ai________free_hotel_icon_, R.drawable.____________ai________free_launch_ship_, R.drawable.____________ai________free_macaron_ribbon_vector_,
            R.drawable.____________ai________free_macaroon_color_vector_, R.drawable._______ai________free_flower_icon_download_, R.drawable.____________ai________free_polar_bear_fishing_,
            R.drawable.____________ai________free_rocket_icon_vector_, R.drawable.____________ai________free_smart_factory_vector_, R.drawable.___________ai________free_bottle_ship_vector_,
            R.drawable.___________ai________free_drug_icon____urbanbrush, R.drawable.___________ai________free_lip_candy_vector_, R.drawable.___________ai________free_winter_coffee_vector_,
            R.drawable.__________ai________free_yoga_posture_vector_, R.drawable._________ai________free_cake_image_vector_, R.drawable._________ai________free_cake_vector_download_,
            R.drawable._________ai________free_hot_air_balloon_vector_, R.drawable.________ai________free_liquid_medicine_vector_
    };
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen2);

        context=FullScreenActivity2.this;
        /*Gallery gallery=findViewById(R.id.gallery);

        final ImageSwitcher imageSwitcher=findViewById(R.id.imageSwitcher);
        imageSwitcher.setFactory(FullScreenActivity2.this);
        imageSwitcher.setInAnimation(AnimationUtils.loadAnimation(FullScreenActivity2.this, android.R.anim.fade_in));
        imageSwitcher.setOutAnimation(AnimationUtils.loadAnimation(FullScreenActivity2.this, android.R.anim.fade_out));
        imageSwitcher.setAlpha(Float.parseFloat("50.0"));

        gallery.setAdapter(new ImageAdapter2(this));

        gallery.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                imageItem=position;
                imageSwitcher.setImageResource(images[position]);
            }
        });*/
        viewPager=(ViewPager)findViewById(R.id.myViewPager);

        final ImageSwitcher imageSwitcher=findViewById(R.id.imageSwitcher);
        imageSwitcher.setFactory(FullScreenActivity2.this);
        imageSwitcher.setInAnimation(AnimationUtils.loadAnimation(FullScreenActivity2.this, android.R.anim.fade_in));
        imageSwitcher.setOutAnimation(AnimationUtils.loadAnimation(FullScreenActivity2.this, android.R.anim.fade_out));
        imageSwitcher.setAlpha(Float.parseFloat("50.0"));

        ViewPagerAdapter viewPagerAdapter=new ViewPagerAdapter(this);

        int ID = getIntent().getIntExtra("id",1);

        //viewPager.setAdapter(new ImageAdapter2(getApplicationContext()));
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.setCurrentItem(ID);
        viewPager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageSwitcher.setImageResource(images[imageItem]);
            }
        });
    }

    @Override
    public View makeView() {
        ImageView imageView=new ImageView(context);

        Intent i=getIntent();
        int position=i.getExtras().getInt("id");

        imageView.setImageResource(images[position]);
        return imageView;
    }

    private class ImageAdapter2 extends BaseAdapter{
        Context mContext;

        ImageAdapter2(Context mContext) {
                this.mContext=mContext;
            }

            public int getCount() {
                return images.length;
            }

            @Override
            public Object getItem(int position) {
                return images[position];
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                ImageView imageView=new ImageView(mContext);
                imageView.setImageResource(images[position]);
                //imageView.setLayoutParams(new Gallery.LayoutParams(200,150));
                imageView.setLayoutParams(new ViewPager.LayoutParams(mContext,null));
                return imageView;
            }
        }

    }

