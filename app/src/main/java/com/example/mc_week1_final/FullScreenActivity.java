package com.example.mc_week1_final;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.davemorrissey.labs.subscaleview.ImageSource;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;

public class FullScreenActivity extends AppCompatActivity {

    //ImageView imageView;
    SubsamplingScaleImageView imageView;

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen);

        imageView=(SubsamplingScaleImageView) findViewById(R.id.image_view);

        Intent i=getIntent();

        int position=i.getExtras().getInt("id");

        ImageAdapter imageAdapter=new ImageAdapter(this);

        imageView.setImage(ImageSource.resource(imageAdapter.imageArray[position]));
     }
}
