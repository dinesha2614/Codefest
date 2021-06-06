package com.example.codefestfinalapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class Splashscreen extends AppCompatActivity {
    ImageView top_wave,developer,bottom_wave;
    TextView title;
    Handler handle=new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);

        top_wave=findViewById(R.id.top_view);
        bottom_wave=findViewById(R.id.bottom_view);
        developer=findViewById(R.id.main_img);
        title=findViewById(R.id.title);


        //Animation
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Animation animation1= AnimationUtils.loadAnimation(this,R.anim.top_wave);
        top_wave.setAnimation(animation1);


        ObjectAnimator animator=ObjectAnimator.ofPropertyValuesHolder(title, PropertyValuesHolder.ofFloat("scaleX",1.2f),PropertyValuesHolder.ofFloat("scaleY",1.2f));
        animator.setDuration(500);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setRepeatMode(ValueAnimator.REVERSE);
        animator.start();


        Animation animation2= AnimationUtils.loadAnimation(this,R.anim.bottom_wave);
        bottom_wave.setAnimation(animation2);

        handle.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent =new Intent(Splashscreen.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        },4000);



    }
}