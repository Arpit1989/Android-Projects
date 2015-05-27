package com.example.arpitkulshrestha.basicclasses;

import android.util.Log;

/**
 * Created by arpitkulshrestha on 14/04/15.
 */

public class Soldier {
        int health;
        String soldierType;

        void shootEnemy(){
            //lets print which type of soldier is shooting
            Log.i(soldierType, " is shooting");
        }

}
