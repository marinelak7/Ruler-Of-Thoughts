package com.example.masterminds;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class Statistics extends AppCompatActivity {

    private ArrayList<Effort> efforts;
    private MyDBHandler dbHandler;
    private EffortAdapter effortAdapter;
    private RecyclerView effortRecyclerView;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.statistics);


        Spinner sort_spinner = findViewById(R.id.sort_type);

        sort_spinner.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                        Object item = parent.getItemAtPosition(position);
                        printItems(item.toString());
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                }
        );



    }

    public void delete(View view)
    {
        dbHandler = new MyDBHandler(Statistics.this);
        dbHandler.deleteAll();
        Intent i = new Intent(this, Statistics.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);

    }

    protected void printItems(String sortType)
    {
        efforts = new ArrayList<>();
        dbHandler = new MyDBHandler(Statistics.this);

        String sort = "";
        if (sortType.equalsIgnoreCase("-Newest"))
        {
            sort = " ORDER BY " + MyDBHandler.COLUMN_ID + " DESC";
        }
        else if (sortType.equalsIgnoreCase("-Oldest"))
        {
            sort = " ORDER BY " + MyDBHandler.COLUMN_ID;
        }


        efforts = dbHandler.readEfforts(sort);

        effortAdapter = new EffortAdapter(efforts, Statistics.this);
        effortRecyclerView = findViewById(R.id.effortsRecycler);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(Statistics.this, RecyclerView.VERTICAL, false);
        effortRecyclerView.setLayoutManager(linearLayoutManager);

        effortRecyclerView.setAdapter(effortAdapter);
    }


}





