package com.example.masterminds;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
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


        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_delete_tries);

        Button button1 = dialog.findViewById(R.id.yes_delete);
        Button button2 = dialog.findViewById(R.id.no_delete);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHandler = new MyDBHandler(Statistics.this);
                dbHandler.deleteAll();
                Intent i = new Intent(Statistics.this, Statistics.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();

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





