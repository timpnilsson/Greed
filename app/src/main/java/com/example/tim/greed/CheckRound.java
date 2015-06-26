package com.example.tim.greed;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Tim on 2015-06-11.
 */
public class CheckRound {
    private ArrayList<Integer> sorted;
    private ArrayList<Dice> dices;
    private boolean allPoints;
    private boolean isOneOrFive;

    public CheckRound(ArrayList<Dice> dices) {
        this.dices = dices;
        sorted = new ArrayList<Integer>();
        allPoints=false;
        isOneOrFive=false;
    }
    //checks if the dices constructs a ladder and returns true or false
    public boolean containsLadder() {
        for (int i = 1; i <= dices.size(); i++) {
            if (!dices.contains(i)) {
                return false;
            }
        }
        return true;
    }
    //checks if there exists any kind of triplets of any number and returns the corresponding points
    public int threeOfAKind() {
        sort();
        for (int i = 0; i < sorted.size() - 2; i++) {
            int temp = sorted.get(i);
            if (temp == sorted.get(i + 1) && temp == sorted.get(i + 2)) {
                if (temp != 1) {
                    if(temp==5){
                        isOneOrFive=true;
                    }
                    return (temp + 1) * 100;
                } else {
                    isOneOrFive=true;
                    return 1000;
                }

            }
        }
        return 0;

    }
    //checks the amount of ones and fives and returns the corresponding points
    public int oneOrFive() {
        int nbrOfOnes = 0;
        int nbrOfFives = 0;
        int temp;
        for (int i = 0; i < dices.size(); i++) {
            temp = dices.get(i).getValue();
            if (temp == 1) {
                nbrOfOnes++;

            } else if (temp == 5) {
                nbrOfFives++;
            }
        }
        return nbrOfOnes * 100 + nbrOfFives * 50;
    }

    public void sort() {
        for (int i = 0; i < dices.size(); i++) {
            sorted.add(dices.get(i).getValue());

        }
        Collections.sort(sorted);
    }
    //returns the points of a round in total
    public int returnPoints() {
        if (containsLadder()) {
            allDicesPointGiving();
            return 1000;
        } else if (threeOfAKind() > 0) {
            return threeOfAKind();
        } else if (oneOrFive() > 0) {
            if (!isOneOrFive) {
                return oneOrFive();
            }
        }
         return 0;

    }
    public void allDicesPointGiving(){
        allPoints=true;
    }
    public int reachedMinimun(){
        if(returnPoints()>=300){
            return returnPoints();
        }
        return 0;
    }

}
