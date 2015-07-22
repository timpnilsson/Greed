package com.example.tim.greed;

import java.util.ArrayList;

/**
 * Created by Tim on 2015-06-11.
 */
public class Rules {
    private ArrayList<Dice> dices;

    //construct a Rules-object with an ArrayList of Dice-object as param
    public Rules(ArrayList<Dice> dices){
        this.dices=dices;
    }

   //checks if the dices constructs a ladder
    public int ladder(){
       int[]count=returnCount();
       int ladderCounter=0;
        for(int i=0;i<count.length;i++){
            for(int j=1; j<=6;j++) {
                if (count[i]==j){
                    ladderCounter++;
                }
            }
        }
        if(ladderCounter==6){
            return 1000;
        }else{
            return 0;
        }
    }

    //checks if the dices consist of any triplet of any kind
    public int threeOfAKind(){
        int[]count=returnCount();
        int points=0;
        for(int i=0; i<count.length;i++){
            if(count[i]>=3){
                if(i==0){
                    points=(i+1)*1000;
                }else {
                    points = points + ((i+1) * 100);
                }
                int k=i+1;
                setPointGiving(dices,k);

            }
        }
        return points;
    }

    //checks if there is any ones or fives among the remaining dices
    public int isOneOrFive(){
        int one=0;
        int five=0;
      for(int i=0; i<dices.size();i++){
          if(dices.get(i).getValue()==1 && !dices.get(i).diceGivesPoints()){
              one++;
              dices.get(i).isPointGiving();
          }else if(dices.get(i).getValue()==5 && !dices.get(i).diceGivesPoints() ){
              five++;
              dices.get(i).isPointGiving();
          }
      }
        int j= (one*100)+(five*50);
        return j;
    }

    //help-method that summarizes how many dices of each number
    private int[] returnCount(){
        int[]count=new int[6];
        int temp;
        for(int i=0; i<dices.size();i++) {
            temp=dices.get(i).getValue();
            if (temp==1){
                count[0]++;
            }else if(temp==2){
                count[1]++;
            }else if(temp==3){
                count[2]++;
            }else if(temp==4){
                count[3]++;
            }else if(temp==5){
                count[4]++;
            }else if(temp==6){
                count[5]++;
            }
        }
        return count;
    }
    //help-method to set which dices that gives points
    private void setPointGiving(ArrayList<Dice> d, int i) {
        int threeFirst = 0;
        while (threeFirst < 3) {
            for (int j = 0; j < d.size(); j++) {
                if(d.get(j).getValue()==i){
                    d.get(j).isPointGiving();
                    threeFirst++;
                }
            }
        }
    }

}