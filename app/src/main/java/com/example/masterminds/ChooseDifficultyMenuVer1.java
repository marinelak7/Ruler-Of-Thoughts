package com.example.masterminds;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ChooseDifficultyMenuVer1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_difficulty_menu_ver1);
    }


    public void startGame(View view)
    {
        Intent i = new Intent(this, GameActivityWithSpinner.class);
        startActivity(i);
    }
}

