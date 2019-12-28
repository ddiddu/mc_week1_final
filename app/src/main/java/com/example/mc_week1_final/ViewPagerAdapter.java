package com.example.mc_week1_final;

import android.content.Context;
import android.content.Intent;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

public class ViewPagerAdapter extends PagerAdapter {

    private Context context;
    private LayoutInflater layoutInflater;
    private Integer[] images={
            R.drawable.____________ai________free_hotel_icon_, R.drawable.____________ai________free_launch_ship_, R.drawable.____________ai________free_macaron_ribbon_vector_,
            R.drawable.____________ai________free_macaroon_color_vector_, R.drawable._______ai________free_flower_icon_download_, R.drawable.____________ai________free_polar_bear_fishing_,
            R.drawable.____________ai________free_rocket_icon_vector_, R.drawable.____________ai________free_smart_factory_vector_, R.drawable.___________ai________free_bottle_ship_vector_,
            R.drawable.___________ai________free_drug_icon____urbanbrush, R.drawable.___________ai________free_lip_candy_vector_, R.drawable.___________ai________free_winter_coffee_vector_,
            R.drawable.__________ai________free_yoga_posture_vector_, R.drawable._________ai________free_cake_image_vector_, R.drawable._________ai________free_cake_vector_download_,
            R.drawable._________ai________free_hot_air_balloon_vector_, R.drawable.________ai________free_liquid_medicine_vector_
    };


    public ViewPagerAdapter(Context context) {
        this.context=context;
    }

    public int getCount() {
        return images.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
         return view==o;
     }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=layoutInflater.inflate(R.layout.custom_layout,null);

        ImageView imageView=(ImageView)view.findViewById(R.id.myImageView);
        imageView.setImageResource(images[position]);

        ViewPager viewPager=(ViewPager)container;
        viewPager.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        ViewPager viewPager=(ViewPager)container;
        View view=(View)object;
        viewPager.removeView(view);
    }
}
