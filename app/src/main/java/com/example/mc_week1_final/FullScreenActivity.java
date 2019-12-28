package com.example.mc_week1_final;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.widget.ToolbarWidgetWrapper;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.davemorrissey.labs.subscaleview.ImageSource;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;

public class FullScreenActivity extends AppCompatActivity {

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

        /*Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);*/
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
                String shareBody="Your Body Here";
                String shareSubject="Your Subject Here";

                sharingIntent.putExtra(Intent.EXTRA_TEXT,shareBody);
                sharingIntent.putExtra(Intent.EXTRA_SUBJECT,shareSubject);

                startActivity(Intent.createChooser(sharingIntent,"Share Using"));
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
