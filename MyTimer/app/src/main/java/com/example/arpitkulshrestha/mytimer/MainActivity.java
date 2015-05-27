package com.example.arpitkulshrestha.mytimer;

import android.app.Activity;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;

import android.os.Handler;


public class MainActivity extends Activity {
    private Handler myHandler;
    boolean gameOn;
    long startTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("info","in create");

            //How many milliseconds is it since the UNIX epoch
            startTime = System.currentTimeMillis();

            myHandler = new Handler() {
                public void handleMessage(Message msg) {
                    super.handleMessage(msg);

                    if (gameOn) {
                        long seconds = ((System.currentTimeMillis() - startTime)) / 1000;
                        Log.i("info", "seconds = " + seconds);
                    }

                    myHandler.sendEmptyMessageDelayed(0, 1000);
                }

            };

            gameOn = true;
            myHandler.sendEmptyMessage(0);


    }



}
