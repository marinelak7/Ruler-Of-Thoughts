package com.example.masterminds;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;



public class ChooseDifficultyMenuVer1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_difficulty_menu_ver1);


    }



    public void startEasyGame(View view)
    {
        Pegs.setDifficulty(0);
        Intent i = new Intent(this, GameActivityWithSpinner.class);
        startActivity(i);
    }
    public void startNormalGame(View view)
    {
        Pegs.setDifficulty(1);
        Intent i = new Intent(this, GameActivityWithSpinner.class);
        startActivity(i);
    }
    public void startHardGame(View view)
    {
        Pegs.setDifficulty(2);
        Intent i = new Intent(this, GameActivityWithSpinner.class);
        startActivity(i);
    }

    public void viewRules(View view)
    {
        Intent i = new Intent(this, rules.class);
        startActivity(i);
    }

}

