package com.example.tim.greed;

import java.util.ArrayList;

/**
 * Created by Tim on 2015-07-07.
 */
public class CheckRound {
    private Rules rules;
    ArrayList<Dice>dices;


    //construct a CheckRound-object that takes an ArrayList with Dice-objects as param
    public CheckRound(ArrayList<Dice> dices){
        this.dices=dices;
        rules=new Rules(dices);

    }
    //summarizes and returns the outcome of the six dices
    public int returnPointsAllSix(){
        if(rules.ladder()>0){
            return 1000;
        }else{
            int triplets= rules.threeOfAKind();
            int oneOrFive= rules.isOneOrFive();
            int k=triplets+oneOrFive;
            return k;
        }
    }
    //summarizes and returns the outcome of the a set of dices smaller then 6
    public int returnPointsCertain(ArrayList<Dice> d){
        Rules r2=new Rules(d);
        if(r2.ladder()>0){
            return 1000;
        }else{
            int triplets= r2.threeOfAKind();
            int oneOrFive= r2.isOneOrFive();
            int k=triplets+oneOrFive;
            return k;
        }
    }




    //controls that the points after the first throw is at least 300
    public boolean reachedFirstMinimumLimit(int points){
        if(points<300){
            return false;
        }
        return true;
    }

    //controls that the points after second throw or higher in a round meets the criteria
    public boolean reachedHigherMinimumLimit(int points){
        if(points<=0){
            return false;
        }
        return true;
    }

    //resets the dices and prepares them for a new round
    public void resetNewRound(){
        for(int i=0;i<dices.size();i++){
            dices.get(i).notPointGiving();
        }
    }

    //checks if all dices has been used
    public boolean checkIfAllUsed(){
       for(int i=0;i<dices.size();i++){
           if(!dices.get(i).diceGivesPoints()){
               return false;
           }
       }
       return true;
    }
    //returns the points from a subset of Dices smaller then 6
    public int checkSubSet(){
        ArrayList<Dice> d =new ArrayList<Dice>();
        for(int i=0;i<dices.size();i++){
            if(!dices.get(i).diceGivesPoints()){
                d.add(dices.get(i));
            }
        }
        return returnPointsCertain(d);
    }



}

