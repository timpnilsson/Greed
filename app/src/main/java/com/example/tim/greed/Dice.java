package com.example.tim.greed;

import java.util.Random;

/**
 * Created by Tim on 2015-06-10.
 */
public class Dice {
    private int value;
    private boolean pressed;
    private boolean pointGiving;

    //creates a dice
    public Dice() {
        pressed=false;
        pointGiving=false;

    }

    //simulates a dice throw between value 1 and 6
    public void throwDice() {
        Random rand = new Random();
        value = rand.nextInt(6 - 1) + 1;
    }



    //throws the dice and returns the current value
    public int diceRoll() {
        throwDice();
        return getValue();
    }


    //returns an integer, used in diceRoll()
    public int getValue() {
        return value;

    }


    //sets the value of the dice to val
    public void setValue(int val){
        value=val;
    }



    //methods for checking and changing if a dice gives points
    public boolean diceGivesPoints(){
        return pointGiving;
    }
    public void isPointGiving(){
        pointGiving=true;
    }
    public void notPointGiving(){
        pointGiving=false;
    }













}

