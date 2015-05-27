package com.example.arpitkulshrestha.mathgamechapter2;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;


public class GameActivity extends Activity implements View.OnClickListener {
    int correctAnswer;
    Button buttonObjectChoice1;
    Button buttonObjectChoice2;
    Button buttonObjectChoice3;

    TextView textObjectOperator;
    TextView textObjectPartA;
    TextView textObjectPartB;
    TextView textObjectScore;
    TextView textObjectLevel;

    int currentScore = 0;
    int currentLevel = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //The next line loads our UI design to the screen
        setContentView(R.layout.activity_game);


        /*Here we get a working object based on either the button
          or TextView class and base as well as link our new objects
          directly to the appropriate UI elements that we created previously*/
        textObjectPartA = (TextView)findViewById(R.id.textPartA);

        textObjectOperator = (TextView)findViewById(R.id.textOperator);

        textObjectPartB = (TextView)findViewById(R.id.textPartB);

        textObjectScore = (TextView)findViewById(R.id.textScore);

        textObjectLevel = (TextView)findViewById(R.id.textLevel);

        buttonObjectChoice1 = (Button)findViewById(R.id.btnChoice1);

        buttonObjectChoice2 = (Button)findViewById(R.id.btnChoice2);

        buttonObjectChoice3 = (Button)findViewById(R.id.btnChoice3);

        buttonObjectChoice1.setOnClickListener(this);
        buttonObjectChoice2.setOnClickListener(this);
        buttonObjectChoice3.setOnClickListener(this);
        setQuestion();

    }//onCreate ends here

    void setQuestion(){
        //generate the parts of the question
        int numberRange = currentLevel * 3;
        Random randInt = new Random();

        int partA = randInt.nextInt(numberRange);
        partA++;//don't want a zero value

        int partB = randInt.nextInt(numberRange);
        partB++;//don't want a zero value





        String[] operators = new String[4];

        operators[0] = "+";
        operators[1] = "-";
        operators[2] = "*";
        operators[3] = "/";



        textObjectPartA.setText(""+partA);
        textObjectPartB.setText(""+partB);

        // Random Operators
        int randOpr = randInt.nextInt(4);
        switch (randOpr){
            case 0:
                textObjectOperator.setText("+");
                correctAnswer = partA + partB;

                break;
            case 1:
                textObjectOperator.setText("-");
                correctAnswer = partA - partB;

                break;
            case 2:
                textObjectOperator.setText("*");
                correctAnswer = partA * partB;

                break;
            case 3:
                textObjectOperator.setText("/");
                correctAnswer = partA / partB;

                break;

        }
        int wrongAnswer1 = correctAnswer-2;
        int wrongAnswer2 = correctAnswer+2;

        Log.i("info","operator" + operators[randOpr]);
        Log.i("info","part A" + partA);
        Log.i("info","part B "+ partB);
        Log.i("info"," correct ans"+ correctAnswer);


        //set the multi choice buttons
        //A number between 0 and 2
        int buttonLayout = randInt.nextInt(3);
        switch (buttonLayout){

            case 0:
                buttonObjectChoice1.setText(""+correctAnswer);
                buttonObjectChoice2.setText(""+wrongAnswer1);
                buttonObjectChoice3.setText(""+wrongAnswer2);
                break;


            case 1:

                buttonObjectChoice2.setText(""+correctAnswer);
                buttonObjectChoice3.setText(""+wrongAnswer1);
                buttonObjectChoice1.setText(""+wrongAnswer2);
                break;


            case 2:
                buttonObjectChoice3.setText(""+correctAnswer);
                buttonObjectChoice1.setText(""+wrongAnswer1);
                buttonObjectChoice2.setText(""+wrongAnswer2);
                break;
        }

    }

    void updateScoreAndLevel(int answerGiven){
        if(isCorrect(answerGiven)){
            for(int i = 1; i <= currentLevel; i++){
                currentScore = currentScore + i;
            }

            currentLevel++;
        }else{
            currentScore = 0;
            currentLevel = 1;
        }//Actually update the two TextViews
        textObjectScore.setText("Score: " + currentScore);
        textObjectLevel.setText("Level: " + currentLevel);
    }

    boolean isCorrect(int answerGiven){
        boolean correctTrueOrFalse;
        if(answerGiven == correctAnswer){//YAY!
            Toast.makeText(getApplicationContext(), "Well done!", Toast.LENGTH_LONG).show();
            correctTrueOrFalse=true;
        }else{//Uh-oh!
            Toast.makeText(getApplicationContext(), "Sorry", Toast.LENGTH_LONG).show();
            correctTrueOrFalse=false;
        }
        return correctTrueOrFalse;
    }

    @Override
    public void onClick(View v) {
        int answerGiven = 0;
        switch (v.getId()) {
            case R.id.btnChoice1:
                //button 1 stuff goes here
                answerGiven = Integer.parseInt("" + buttonObjectChoice1.getText());
                break;
            case R.id.btnChoice2:
                // 2 btn stuff
                answerGiven = Integer.parseInt("" + buttonObjectChoice2.getText());
                break;
            case R.id.btnChoice3:
                // 3 btn stuff
                answerGiven = Integer.parseInt("" + buttonObjectChoice3.getText());
                break;
        }
        updateScoreAndLevel(answerGiven);
        setQuestion();
    }
}
