package com.example.arpitkulshrestha.accessscopethisandstatic;

import android.util.Log;

/**
 * Created by arpitkulshrestha on 14/04/15.
 */
public class Fighter extends AlienShip{

    public Fighter(){
        super(400);
        //Strong shields for a fighter
        Log.i("Location: ", "Fighter constructor");
    }

    public void fireWeapon(){
        Log.i("Firing weapon: ", "lasers firing");
    }

}