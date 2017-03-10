package com.baway.administrator.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final MyTextView myTextView = (MyTextView) findViewById(R.id.fdfed);
        //实例化补间动画
        final Animation animation = AnimationUtils.loadAnimation(this,R.anim.my_anim);
        //监听
        myTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //启动动画
                myTextView.startAnimation(animation);
            }
        });

    }
}
