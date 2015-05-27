package com.example.arpitkulshrestha.mathgamechapter2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstancePhase) {
        super.onCreate(savedInstancePhase);
        setContentView(R.layout.activity_main);
        final Button playBtn = (Button) findViewById(R.id.playBtn);
        playBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent i;
        i = new Intent(this,GameActivity.class);
        startActivity(i);
    }
}
