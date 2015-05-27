package com.example.arpitkulshrestha.filereadwrite;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Random;


public class MainActivity extends Activity implements View.OnClickListener {
    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    String dataName = "MyData";
    String stringName = "MyString";
    String defaultString = ":-(";
    String currentString = "";
    Button button1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //initialise the preferences
        prefs = getSharedPreferences(dataName,MODE_PRIVATE);
        editor = prefs.edit();

        currentString = prefs.getString(stringName,defaultString);

        button1 = (Button) findViewById(R.id.button);
        button1.setOnClickListener(this);
        button1.setText(currentString);
    }


    @Override
    public void onClick(View v) {
        //we don't need to switch here!
        //There is only one button
        //so only the code that actually does stuff

        //Get a random number between 0 and 9
        Random randInt = new Random();
        int ourRandom = randInt.nextInt(10);

        //Add the random number to the end of currentString
        currentString = currentString + ourRandom;

        //Save currentString to a file in case the user
        //suddenly quits or gets a phone call
        editor.putString(stringName, currentString);
        editor.commit();

        //update the button text
        button1.setText(currentString);

    }
}
