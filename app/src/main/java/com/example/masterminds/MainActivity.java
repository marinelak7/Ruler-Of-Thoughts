package com.example.masterminds;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toolbar;


// Main Menu Activity
//
public class MainActivity extends AppCompatActivity {


    Button quitbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);


        quitbtn = findViewById(R.id.quitbtn);


        // Selecting the "QUIT" button, will terminate the app.
        quitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }




    // Selecting the "NEW GAME" option, the application will start
    // a new screen where the game will take place.
    public void newGame(View view)
    {
        Intent i = new Intent(MainActivity.this, GameActivityWithSpinner.class);
        startActivity(i);
    }



    // Selecting the "VIEW STATISTICS" option, the application will start
    // a new screen, where the player can view previous tries and also, sort them.

    public void viewStatistics(View view) {

        Intent i = new Intent(MainActivity.this, Statistics.class);
        startActivity(i);
    }



    // Selecting the question mark on the top right of the screen,
    // the player can view the rules of the game.
    public void viewRules(View view)
    {
        Intent i = new Intent(this, rules.class);
        startActivity(i);
    }





}