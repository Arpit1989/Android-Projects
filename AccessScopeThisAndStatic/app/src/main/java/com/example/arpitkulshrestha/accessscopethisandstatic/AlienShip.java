package com.example.arpitkulshrestha.accessscopethisandstatic;

import android.util.Log;

/**
 * Created by arpitkulshrestha on 14/04/15.
 */
public abstract class AlienShip {
    private static int numShips;
    private int shieldStrength;
    public String shipName;

    public AlienShip(int shieldStrength){
        Log.i("Location: ", "AlienShip constructor");
        numShips++;
        setShieldStrength(shieldStrength);
    }

    public abstract void fireWeapon();//Ahh my body

    public static int getNumShips(){
        return numShips;
    }

    private void setShieldStrength(int shieldStrength){
        this.shieldStrength = shieldStrength;
    }


    public int getShieldStrength(){
        return this.shieldStrength;
    }

    public void hitDetected(){
        shieldStrength -=25;
        Log.i("Incomiming: ", "Bam!!");
        if (shieldStrength == 0){
            destroyShip();
        }

    }

    private void destroyShip(){
        numShips--;
        Log.i("Explosion: ", "" + this.shipName + " destroyed");
    }

}