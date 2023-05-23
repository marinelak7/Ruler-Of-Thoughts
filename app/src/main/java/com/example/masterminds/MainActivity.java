package com.example.masterminds;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toolbar;

public class MainActivity extends AppCompatActivity {


    Button quitbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);


        quitbtn = findViewById(R.id.quitbtn);
        quitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }




    public void newGame(View view)
    {
        Intent i = new Intent(MainActivity.this, ChooseDifficultyMenuVer1.class);
        startActivity(i);
    }

    public void viewStatistics(View view) {

        Intent i = new Intent(this, Statistics.class);
        startActivity(i);
    }


    public void viewRules(View view)
    {
        Intent i = new Intent(this, rules.class);
        startActivity(i);
    }




}