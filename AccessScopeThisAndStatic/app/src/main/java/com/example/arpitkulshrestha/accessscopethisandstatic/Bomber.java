package com.example.arpitkulshrestha.accessscopethisandstatic;

import android.util.Log;

/**
 * Created by arpitkulshrestha on 14/04/15.
 */
public class Bomber extends AlienShip {

    public Bomber(){
        super(100);
        //Weak shields for a bomber
        Log.i("Location: ", "Bomber constructor");
    }

    public void fireWeapon(){
        Log.i("Firing weapon: ", "bombs away");
    }
}
