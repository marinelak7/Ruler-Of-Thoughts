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



// The class that handles the representation of
// the database's values.
public class Statistics extends AppCompatActivity {


    // The values of our database will be stored in objects of the class Effort.
    private ArrayList<Effort> efforts;
    private MyDBHandler dbHandler;


    // Declaring an adapter for our efforts.
    private EffortAdapter effortAdapter;


    // Representing our data with recycler.
    private RecyclerView effortRecyclerView;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.statistics);

        // Spinner with the sort options.
        Spinner sort_spinner = findViewById(R.id.sort_type);


        // Check which sort option was selected and call
        // the printItems method.
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


    // Method for the "DELETE ALL TRIES" button.
    public void delete(View view)
    {

        // Dialog with two options, whether the user
        // wants to delete all the efforts or not.
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_delete_tries);

        Button button1 = dialog.findViewById(R.id.yes_delete);
        Button button2 = dialog.findViewById(R.id.no_delete);



        // The onClick ability of the "YES" button in the dialog.
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Deleting all our tries, and refreshing our statistics screen.
                dbHandler = new MyDBHandler(Statistics.this);
                dbHandler.deleteAll();
                Intent i = new Intent(Statistics.this, Statistics.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
            }
        });



        // If the player chooses not to delete all tries, the dialog
        // will close.
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();

    }


    // This method will show the values of our database in the screen
    // and also will sort them depending on the user's choice.
    protected void printItems(String sortType)
    {

        // The values of the database will be stored here.
        efforts = new ArrayList<>();
        // Creating a handled for the database.
        dbHandler = new MyDBHandler(Statistics.this);



        // Check which sort type was selected and sort
        // the values accordingly.
        String sort = "";
        if (sortType.equalsIgnoreCase("-Newest"))
        {
            sort = " ORDER BY " + MyDBHandler.COLUMN_ID + " DESC";
        }
        else if (sortType.equalsIgnoreCase("-Oldest"))
        {
            sort = " ORDER BY " + MyDBHandler.COLUMN_ID;
        }
        else if (sortType.equalsIgnoreCase("-Names A-Z"))
        {
            sort = " ORDER BY " + MyDBHandler.COLUMN_PLAYERNAME;
        }
        else if (sortType.equalsIgnoreCase("-Names Z-A"))
        {
            sort = " ORDER BY " + MyDBHandler.COLUMN_PLAYERNAME + " DESC";
        }
        else if (sortType.equalsIgnoreCase("-Most points"))
        {
            sort = " ORDER BY " + MyDBHandler.COLUMN_POINTS + " DESC";
        }

        // Initializing our effort arraylist with the database contents,
        // being send in an arraylist.
        efforts = dbHandler.readEfforts(sort);

        // Initializing the adapter for our recycler.
        effortAdapter = new EffortAdapter(efforts, Statistics.this);
        effortRecyclerView = findViewById(R.id.effortsRecycler);

        // Initializing a layout for the recycler.
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(Statistics.this, RecyclerView.VERTICAL, false);
        effortRecyclerView.setLayoutManager(linearLayoutManager);

        effortRecyclerView.setAdapter(effortAdapter);
    }


}





