package com.scientist.animations1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    ImageView babs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        babs = (ImageView) findViewById(R.id.babs);
    }

    public void doStuffs(View view){
        babs.animate()
                .translationX(-1000f)
                .translationY(-1000f)
                .rotationBy(1800)
                .scaleXBy(1f)
                .scaleY(1f)
                .setDuration(7000);


    }
}