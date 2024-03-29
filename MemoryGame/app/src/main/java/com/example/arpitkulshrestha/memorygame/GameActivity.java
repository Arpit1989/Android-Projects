package com.example.arpitkulshrestha.memorygame;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.util.Random;

import android.os.Handler;
import android.widget.Toast;


public class GameActivity extends Activity implements View.OnClickListener {
    int sample1 = -1;
    int sample2 = -1;
    int sample3 = -1;
    int sample4 = -1;

    //for our hiscore (phase 4)
    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    String dataName = "MyData";
    String intName = "MyInt";
    int defaultInt = 0;
    int hiScore;

    private SoundPool soundPool;
    TextView textScore;
    TextView textDifficulty;
    TextView textWatchGo;

    Button button4;
    Button button1;
    Button button2;
    Button button3;
    Button buttonReplay;

    int difficultyLevel =3;
    int[] sequenceToCopy = new int[100];
    private Handler myHandler;

    boolean playSequence;
    int elementToPlay =0;
    int playerResponses;
    int playerScore;
    boolean isResponding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        //phase 4
        //initialize our two SharedPreferences objects
        prefs = getSharedPreferences(dataName,MODE_PRIVATE);
        editor = prefs.edit();
        hiScore = prefs.getInt(intName, defaultInt);

        soundPool = new SoundPool(10, AudioManager.STREAM_MUSIC,0);
        try{
            AssetManager assetManager = getAssets();
            AssetFileDescriptor descriptor;

            descriptor = assetManager.openFd("Default.ogg");
            sample1 = soundPool.load(descriptor,0);

            descriptor = assetManager.openFd("Explosion3.ogg");
            sample2 = soundPool.load(descriptor,0);

            descriptor = assetManager.openFd("Randomize3.ogg");
            sample3 = soundPool.load(descriptor,0);

            descriptor = assetManager.openFd("Randomize24.ogg");
            sample4 = soundPool.load(descriptor,0);

            //Reference all the elements of our UI
            //First the TextViews
            textScore = (TextView)findViewById(R.id.textHiScore);
            textScore.setText("Score: " + playerScore);
            textDifficulty = (TextView)findViewById(R.id.textDifficulty);

            textDifficulty.setText("Level: " + difficultyLevel);
            textWatchGo = (TextView)findViewById(R.id.textWatchGo);

            //Now the buttons
            button1 = (Button)findViewById(R.id.button1);
            button2 = (Button)findViewById(R.id.button2);
            button3 = (Button)findViewById(R.id.button3);
            button4 = (Button)findViewById(R.id.button4);
            buttonReplay = (Button)findViewById(R.id.buttonReplay);

            //Now set all the buttons to listen for clicks
            button1.setOnClickListener(this);
            button2.setOnClickListener(this);
            button3.setOnClickListener(this);
            button4.setOnClickListener(this);
            buttonReplay.setOnClickListener(this);

            //This is the code which will define our thread
            myHandler = new Handler() {
                public void handleMessage(Message msg) {
                    super.handleMessage(msg);

                    if (playSequence) {
                        //All the thread action will go here
                        //make sure all the buttons are made visible
                        button1.setVisibility(View.VISIBLE);
                        button2.setVisibility(View.VISIBLE);
                        button3.setVisibility(View.VISIBLE);
                        button4.setVisibility(View.VISIBLE);

                        switch (sequenceToCopy[elementToPlay]){
                            case 1:
                                //hide a button
                                button1.setVisibility(View.INVISIBLE);
                                //play a sound
                                soundPool.play(sample1, 1, 1, 0, 0, 1);
                                break;

                            case 2:
                                //hide a button
                                button2.setVisibility(View.INVISIBLE);
                                //play a sound
                                soundPool.play(sample2, 1, 1, 0, 0, 1);
                                break;

                            case 3:
                                //hide a button button3.setVisibility(View.INVISIBLE);
                                //play a sound
                                soundPool.play(sample3, 1, 1, 0, 0, 1);
                                break;

                            case 4:
                                //hide a button
                                button4.setVisibility(View.INVISIBLE);
                                //play a sound
                                soundPool.play(sample4, 1, 1, 0, 0, 1);
                                break;
                        }

                        elementToPlay++;
                        if(elementToPlay == difficultyLevel){
                            sequenceFinished();
                        }
                    }

                    myHandler.sendEmptyMessageDelayed(0, 900);
                }

            };


            myHandler.sendEmptyMessage(0);

            playASequence();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    @Override
    public void onClick(View v) {
        if(!playSequence) {//only accept input if sequence not playing
            switch (v.getId()) {
                case R.id.button1:
                    //play a sound
                    soundPool.play(sample1, 1, 1, 0, 0, 1);
                    checkElement(1);
                    break;
                case R.id.button2:
                    //play a sound
                    soundPool.play(sample2, 1, 1, 0, 0, 1);
                    checkElement(2);
                    break;

                case R.id.button3:
                    //play a sound
                    soundPool.play(sample3, 1, 1, 0, 0, 1);
                    checkElement(3);
                    break;

                case R.id.button4:
                    //play a sound
                    soundPool.play(sample4, 1, 1, 0, 0, 1);
                    checkElement(4);
                    break;
                case R.id.buttonReplay:
                    difficultyLevel = 3;
                    playerScore = 0;
                    textScore.setText("Score: " + playerScore);
                    playASequence();
                    break;
            }
        }

    }
    public void checkElement(int thisElement){

        if(isResponding) {
            playerResponses++;
            if (sequenceToCopy[playerResponses-1] == thisElement) { //Correct
                playerScore = playerScore + ((thisElement + 1) * 2);
                textScore.setText("Score: " + playerScore);
                if (playerResponses == difficultyLevel) {//got the whole sequence
                    //don't checkElement anymore
                    isResponding = false;
                    //now raise the difficulty
                    difficultyLevel++;
                    //and play another sequence
                    playASequence();
                }

            } else {//wrong answer
                textWatchGo.setText("FAILED!");
                //don't checkElement anymore
                isResponding = false;
                //for our high score (phase 4)
                if(playerScore > hiScore) {
                    hiScore = playerScore;
                    editor.putInt(intName, hiScore);
                    editor.commit();
                    Toast.makeText(getApplicationContext(), "New High Score yay!", Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    public void createSequence(){
        //For choosing a random button
        Random randInt = new Random();
        int ourRandom;
        for(int i = 0; i < difficultyLevel; i++){
            //get a random number between 1 and 4
            ourRandom = randInt.nextInt(4);
            ourRandom ++;//make sure it is not zero
            //Save that number to our array
            sequenceToCopy[i] = ourRandom;
        }

    }
    public void playASequence(){
        createSequence();
        isResponding = false;
        elementToPlay = 0;
        playerResponses = 0;
        textWatchGo.setText("WATCH!");
        playSequence = true;
    }
    public void sequenceFinished(){
        playSequence = false;
        //make sure all the buttons are made visible
        button1.setVisibility(View.VISIBLE);
        button2.setVisibility(View.VISIBLE);
        button3.setVisibility(View.VISIBLE);
        button4.setVisibility(View.VISIBLE);
        textWatchGo.setText("GO!");
        isResponding = true;
    }
}
