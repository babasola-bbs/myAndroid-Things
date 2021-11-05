package com.scientist.timestable;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SeekBar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView timesTableListView;

    public void generateTimesTable(int timesProgress){
        ArrayList<String> timesTable = new ArrayList<String>();

        for (int i = 1; i <= 10; i++){
            timesTable.add(Integer.toString(i * timesProgress));
//            timesTable.add(String.valueOf(i * timesProgress));
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, timesTable);
        timesTableListView.setAdapter(arrayAdapter);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timesTableListView = (ListView) findViewById(R.id.timesTableListView);

        final SeekBar seekBar = (SeekBar) findViewById(R.id.timesTableSeekBar);
        seekBar.setMax(20);
        seekBar.setProgress(10);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int min = 1;
                int timesProgress;

                if (progress < min){
                    timesProgress = min;
                    seekBar.setProgress(min);
                }
                else{
                    timesProgress = progress;
                }
                generateTimesTable(timesProgress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }

        });
    }
}