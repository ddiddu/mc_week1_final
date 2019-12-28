package com.example.mc_week1_final;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter {

    private Context mContext;

    public int[] imageArray={
        R.drawable.____________ai________free_hotel_icon_, R.drawable.____________ai________free_launch_ship_, R.drawable.____________ai________free_macaron_ribbon_vector_,
            R.drawable.____________ai________free_macaroon_color_vector_, R.drawable._______ai________free_flower_icon_download_, R.drawable.____________ai________free_polar_bear_fishing_,
            R.drawable.____________ai________free_rocket_icon_vector_, R.drawable.____________ai________free_smart_factory_vector_, R.drawable.___________ai________free_bottle_ship_vector_,
            R.drawable.___________ai________free_drug_icon____urbanbrush, R.drawable.___________ai________free_lip_candy_vector_, R.drawable.___________ai________free_winter_coffee_vector_,
            R.drawable.__________ai________free_yoga_posture_vector_, R.drawable._________ai________free_cake_image_vector_, R.drawable._________ai________free_cake_vector_download_,
            R.drawable._________ai________free_hot_air_balloon_vector_, R.drawable.________ai________free_liquid_medicine_vector_
    };

    public ImageAdapter(Context mContext) {
        this.mContext=mContext;
    }

    public int getCount() {
        return imageArray.length;
    }

    @Override
    public Object getItem(int position) {
        return imageArray[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView=new ImageView(mContext);
        imageView.setImageResource(imageArray[position]);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setLayoutParams(new GridView.LayoutParams(340, 350));
        return imageView;
    }
}
