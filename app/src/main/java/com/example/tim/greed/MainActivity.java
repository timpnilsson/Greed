package com.example.tim.greed;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {
    private static final int endRound = 10000;
    private ArrayList<Dice> dices;
    private int totPoints;
    private int roundPoints;
    private int saveDicesPoints;
    private int nbrOfRounds;
    private boolean savedPressed;
    private CheckRound cR;


    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final GridView gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(new ImageAdapter(this));
        super.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        // the useful data types
        dices = new ArrayList<Dice>();
        for (int i = 0; i < 6; i++) {
            dices.add(new Dice());
        }
        cR = new CheckRound(dices);
        totPoints = 0;
        roundPoints = 0;
        nbrOfRounds = 1;
        saveDicesPoints = 0;
        savedPressed = false;

        //controlling the input to the gridview
        gridview.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {

            //displays the dices and their current value
                if (position < 6) {
                    gridview.getAdapter().getView(dices.get(position).getValue() - 1, v, parent);


            //saves the available dices  and saves the current points temporarely
                } else if (position == 6) {
                    savedPressed = true;
                    if(cR.checkIfAllUsed()){
                        saveDicesPoints=saveDicesPoints+roundPoints;
                        cR.resetNewRound();
                    }
            //scores and saves the current points and starts a new round
                } else if (position == 7) {
                        totPoints = totPoints + saveDicesPoints;
                        if (totPoints >= endRound) {
                            Intent i = new Intent(MainActivity.this, EndGameActivity.class);
                            i.putExtra("totPoints", Integer.toString(totPoints));
                            i.putExtra("nbrOfRounds", Integer.toString(nbrOfRounds));
                            startActivity(i);

                        }else {
                            Toast.makeText(getApplicationContext(), "NbrOfRounds: " + nbrOfRounds + " TotPoints: " + totPoints, Toast.LENGTH_SHORT).show();
                            cR.resetNewRound();
                            nbrOfRounds++;
                            roundPoints = 0;
                        }


            //throws the dices which do not give points
                } else if (position == 8) {
                    if (!savedPressed) {
                        saveDicesPoints = 0;
                        for (int i = 0; i < dices.size(); i++) {

                            if (!dices.get(i).diceGivesPoints()) {
                                dices.get(i).diceRoll();
                            }
                            View vv = parent.getChildAt(i);
                            gridview.getAdapter().getView(dices.get(i).getValue() - 1, vv, parent);


                        }

                        roundPoints = cR.returnPointsAllSix();


                        if (!cR.reachedFirstMinimumLimit(roundPoints)) {
                            Toast.makeText(getApplicationContext(), "Did not reach minimum, new round started", Toast.LENGTH_SHORT).show();
                            cR.resetNewRound();
                            nbrOfRounds++;
                            roundPoints=0;

                        } else {
                            saveDicesPoints = saveDicesPoints + roundPoints;
                            if (cR.checkIfAllUsed()) {
                                cR.resetNewRound();
                            }
                        }


                    } else if(savedPressed) {


                        for (int i = 0; i < dices.size(); i++) {

                            if (!dices.get(i).diceGivesPoints()) {
                                dices.get(i).diceRoll();
                            }
                            View vv = parent.getChildAt(i);
                            gridview.getAdapter().getView(dices.get(i).getValue() - 1, vv, parent);


                        }

                        roundPoints = cR.checkSubSet();





                        if (!cR.reachedHigherMinimumLimit(roundPoints)) {
                            cR.resetNewRound();
                            saveDicesPoints = 0;
                            Toast.makeText(getApplicationContext(), "Did not reach minimum, new round started", Toast.LENGTH_LONG).show();
                            nbrOfRounds++;
                            roundPoints=0;
                        } else {
                            saveDicesPoints = saveDicesPoints + roundPoints;


                        }
                    }
                    savedPressed = false;
                }

            }


            //disables the user from using the back-button
            public void onBackPressed() {
                //do nothing
            }

        });

    }


}


