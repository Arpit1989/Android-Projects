package com.example.arpitkulshrestha.methodexplore;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.util.Random;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int[] ourArray;
        ourArray = new int[5];

        ourArray[0] = 25;
        ourArray[1] = 50;
        ourArray[2] = 125;
        ourArray[3] = 68;
        ourArray[4] = 47;

        //Output all the stored values
        Log.i("info", "Here is ourArray:");
        Log.i("info", "[0] = "+ourArray[0]);
        Log.i("info", "[1] = "+ourArray[1]);
        Log.i("info", "[2] = "+ourArray[2]);
        Log.i("info", "[3] = "+ourArray[3]);
        Log.i("info", "[4] = "+ourArray[4]);

        //We can do any calculation with an array element
        //As long as it is appropriate to the contained type
        //Like this:
        int answer = ourArray[0] +
                ourArray[1] +
                ourArray[2] +
                ourArray[3] +
                ourArray[4];

        Log.i("info", "Answer = "+ answer);

        int[] ourArr = new int[1000];

        for(int i=0;i<1000;i++){
            ourArr[i] = i*5;
            Log.i("info", "i = " + i);
            Log.i("info", "ourArray[i] = " + ourArr[i]);
        }



        //A Random object for generating question numbers later
        Random randInt = new Random();
        //And a variable to hold the random value generated
        int questionNumber;

        //We declare and allocate in separate stages for clarity
        //but we don't have to
        String[][] countriesAndCities;
        //Here we have a 2 dimensional array

        //Specifically 5 arrays with 2 elements each
        //Perfect for 5 "What's the capital city" questions
        countriesAndCities = new String[5][2];

        //Now we load the questions and answers into our arrays
        //You could do this with less questions to save typing
        //But don't do more or you will get an exception
        countriesAndCities [0][0] = "United Kingdom";
        countriesAndCities [0][1] = "London";

        countriesAndCities [1][0] = "USA";
        countriesAndCities [1][1] = "Washington";

        countriesAndCities [2][0] = "India";
        countriesAndCities [2][1] = "New Delhi";

        countriesAndCities [3][0] = "Brazil";
        countriesAndCities [3][1] = "Brasilia";

        countriesAndCities [4][0] = "Kenya";
        countriesAndCities [4][1] = "Nairobi";

        //Now we know that the country is stored at element 0
        //The matching capital at element 1
        //Here are two variables that reflect this
        int country = 0;
        int capital = 1;

        //A quick for loop to ask 3 questions
        for(int i = 0; i < 3; i++){
            //get a random question number between 0 and 4
            questionNumber = randInt.nextInt(5);

            //and ask the question and in this case just
            //give the answer for the sake of brevity
            Log.i("info", "The capital of " +countriesAndCities[questionNumber][country]);

            Log.i("info", "is " +countriesAndCities[questionNumber][capital]);

        }//end of for loop






        //all the Log.i lines print to the Android console
        Log.i("info", "I am in the onCreate method");

        //Call guessANumber with three values
        //and if true is returned output - Found it!
        if(guessANumber( 1,2,3 )) {
            Log.i("info", "Found It!");
        }else{//guessANumber returned false -didn't find it
            Log.i ("info", "Can't find it");
        }

        //continuing with the rest of the program now
        Log.i("info", "Back in onCreate");

        //declare and initialize a String and an int
        int anInt = 10;
        String aString = "I am a string";

        //Now call the different versions of printStuff
        //The name stays the same, only the parameters vary
        printStuff(anInt);
        printStuff(aString);
        printStuff(anInt, aString);

    }

    void printStuff(int myInt){
        Log.i("info", "This is the int only version");
        Log.i("info", "myInt = "+ myInt);
    }

    void printStuff(String myString){
        Log.i("info", "This is the String only version");
        Log.i("info", "myString = "+ myString);
    }

    void printStuff(int myInt, String myString){
        Log.i("info", "This is the combined int and String version");
        Log.i("info", "myInt = "+ myInt);
        Log.i("info", "myString = "+ myString);
    }

    boolean guessANumber(int try1, int try2, int try3){
        //all the Log.i lines print to the Android console
        Log.i("info", "Hi there, I am in the method body");
        //prove our parameters have arrived in the method
        //By printing them in the console
        Log.i("info", "try1 = " + try1);
        Log.i("info", "try2 = " + try2);
        Log.i("info", "try3 = " + try3);

        //we use the found variable to store our true or false
        //setting it to false to begin with
        boolean found = false;

        //Create an object of the Random class so we can use it
        Random randInt = new Random();
        //Generate a random number between 0 and 5
        int randNum = randInt.nextInt(6);
        //show our random number in the console
        Log.i("info", "Our random number = " + randNum);

        //Check if any of our guesses are the same as randNum

        if(try1 == randNum || try2 == randNum || try3 == randNum){
            found = true;
            Log.i("info", "aha!");
        }else{
            Log.i("info", "hmmm");
        }

        return found;

    }
}
