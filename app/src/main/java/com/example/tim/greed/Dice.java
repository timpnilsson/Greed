package com.example.tim.greed;

import java.util.Random;

/**
 * Created by Tim on 2015-06-10.
 */
public class Dice {
    private int value;
    private boolean pressed;
    private boolean used;

    //creates a dice
    public Dice() {
        pressed = false;
        used = false;

    }

    //simulates a dice throw between value 1 and 6
    public void throwDice() {
        Random rand = new Random();
        value = rand.nextInt(6 - 1) + 1;
    }

    //returns the value of a dice at a specific time
    public int getValue() {
        return value;

    }

    //throws the dice and returns the current value
    public int diceRoll() {
        throwDice();
        return getValue();
    }

    public void changeState() {
        if (pressed == false) {
            pressed = true;
        } else {
            pressed = false;
        }
    }

    public boolean getState() {
        return pressed;
    }

    public void changeUsed() {
        if (used == false) {
            used = true;
        } else {
            used = false;
        }
    }


}

