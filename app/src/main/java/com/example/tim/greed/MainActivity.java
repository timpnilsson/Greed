package com.example.tim.greed;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {
    private ArrayList<Dice> dices;
    private CheckRound checkRound;
    private int nbrOfRounds;
    private int totPoints;
    private static final int endOfRound =10000;
    private TextView t1;

    protected void onSaveInstanceState(Bundle outState) {
        // Save the values you need from your textview into "outState"-object
        super.onSaveInstanceState(outState);

    }



    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            //do something
        } else {

            setContentView(R.layout.activity_main);
            nbrOfRounds = 0;
            totPoints = 0;



            final GridView gridview = (GridView) findViewById(R.id.gridview);
            gridview.setAdapter(new ImageAdapter(this));
            dices = new ArrayList<Dice>();
            for (int i = 0; i < 6; i++) {
                dices.add(new Dice());
            }


            gridview.setOnItemClickListener(new OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View v,
                                        int position, long id) {
                    if (position < 6) {
                        gridview.getAdapter().getView(dices.get(position).getValue() - 1, v, parent);
                        switchPressed(position);
                    } else if (position == 6) {
                      //save
                    } else if (position == 7) {
                       //score


                    } else {
                        for (int j = 0; j < dices.size(); j++) {
                            Dice temp = dices.get(j);
                            if (!temp.getState()) {
                                temp.diceRoll();
                                System.out.println(temp.getValue());

                            }


                        }
                        checkRound = new CheckRound(dices);
                        for (int i = 0; i < dices.size(); i++) {
                            View vv = parent.getChildAt(i);
                            gridview.getAdapter().getView(dices.get(i).getValue() - 1, vv, parent);


                        }
                        int roundPoints = checkRound.reachedMinimun();
                        totPoints = totPoints + roundPoints;
                        Toast.makeText(MainActivity.this, "" + totPoints,
                                Toast.LENGTH_SHORT).show();
                        nbrOfRounds++;
                        if(totPoints>=10000){

                            Intent intent = new Intent(MainActivity.this, EndGameActivity.class);
                            String s = Integer.toString(totPoints);
                            String t = Integer.toString(nbrOfRounds);
                            intent.putExtra("total points",s);
                            intent.putExtra("number of rounds", t);
                            startActivity(intent);
                        }else {
                            System.out.println("Current number of rounds: " + nbrOfRounds);
                            System.out.println("Current total amount of points: " + totPoints);
                        }
                    }


                }
            });


        }
    }

    public void switchPressed(int i) {
        dices.get(i).changeState();


    }
}








