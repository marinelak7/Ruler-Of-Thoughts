package com.example.masterminds;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.text.method.ScrollingMovementMethod;

import android.view.View;
import android.widget.TextView;



// The class that handles the concept of the rules.
public class rules extends AppCompatActivity{
    // Declaring TextView from the Layout
    TextView textview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rules);

        // initializing the TextView
        textview = findViewById(R.id.textView);

        // Creating a string that contains the information to be displayed
        String para = "RULES" + "\n" + "\n" +
                "1.The codemaker (game system)  will select a code automatically and the game starts \n"+
                "2.The codebreaker (player)  begins to guess what the hidden code is.\n"+
                "The player can pick four colored pegs and place them in the nearest “guess row”.\n"+
                "3.The player asks the game to give him feedback next to each “guess row” will appear white and red squares (Hints)\n"+
                "•	Each white peg means that one of the guessed pegs is correct, but is in the wrong position\n"+
                "•Each red peg means that one of the guessed pegs is correct, and it is in the right position\n"+
                "4.The codebreaker (player) continues with the next “guess row” until his tries ends or until he right the code. \n"  ;

        // set value to the given TextView
        textview.setText(para);

        // to perform the movement action
        // Moves the cursor or scrolls to the
        // top or bottom of the document
        textview.setMovementMethod(new ScrollingMovementMethod());
    }



    // The "SKIP" button will send the player to the main menu screen,
    // deleting previous activities.
    public void Skip (View view) {
        Intent i = new Intent(this , MainActivity.class) ;
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
    }




}


