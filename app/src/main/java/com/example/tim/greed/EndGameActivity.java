package com.example.tim.greed;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class EndGameActivity extends ActionBarActivity {
    private TextView t1,t2;
    private Button b1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_game2);
        t1 =(TextView) findViewById(R.id.nbrOfRounds);
        t2=(TextView) findViewById(R.id.totScore);
        b1=(Button) findViewById(R.id.newGame);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EndGameActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        Intent i = getIntent();

        StringBuilder sb = new StringBuilder();
        String tP = i.getStringExtra("total points");
        sb.append(tP);

        StringBuilder sn = new StringBuilder();
        String no = i.getStringExtra("number of rounds");
        sn.append(no);

        String nR = i.getStringExtra("number of rounds");
        t1.setText(sb.toString());
        t2.setText(sn.toString());




    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_end_game, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
