package com.example.masterminds;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import nl.dionsegijn.konfetti.core.Angle;
import nl.dionsegijn.konfetti.core.PartyFactory;
import nl.dionsegijn.konfetti.core.Position;
import nl.dionsegijn.konfetti.core.Spread;
import nl.dionsegijn.konfetti.core.emitter.Emitter;
import nl.dionsegijn.konfetti.core.emitter.EmitterConfig;
import nl.dionsegijn.konfetti.core.models.Shape;
import nl.dionsegijn.konfetti.xml.KonfettiView;


// If the player has won, they will transferred to the
// result screen, and the code will continue to Results class.
public class Results extends AppCompatActivity {


    private KonfettiView konfettiView = null;
    private Shape.DrawableShape drawableShape = null;


    EditText playerName;
    Intent i;


    // The time that took for the player to finish the game.
    long game_time;

    // The points of this game that depend on which turn the
    // game was finished.
    int total_points;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_screen);




        playerName = findViewById(R.id.playersName);


        // Getting the values of the player's stats that were sent from
        // the GameActivitySpinner class.
        Bundle extras = getIntent().getExtras();
        game_time = extras.getLong("ending");


        total_points = extras.getInt("points");

        // Showing the results to the player.
        TextView points_gained = findViewById(R.id.points_gained_text);
        points_gained.setText("Points gained: " + Integer.toString(total_points));


        Button ok = findViewById(R.id.submit_button);
        i = new Intent(this, MainActivity.class);

        // A confetti animation to celebrate the player's victory.
        // See dependecies for more.
        //


        final Drawable drawable = ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_heart);
        drawableShape = new Shape.DrawableShape(drawable, true);
        konfettiView = findViewById(R.id.konfettiView2);


        EmitterConfig emitterConfig = new Emitter(5, TimeUnit.SECONDS).perSecond(100);
        konfettiView.start(
                new PartyFactory(emitterConfig)
                        .angle(Angle.BOTTOM)
                        .spread(Spread.ROUND)
                        .shapes(Arrays.asList(Shape.Square.INSTANCE, Shape.Circle.INSTANCE, drawableShape))
                        .colors(Arrays.asList(0xfce18a, 0xff726d, 0xf4306d, 0xb48def))
                        .setSpeedBetween(0f, 15f)
                        .position(new Position.Relative(0.0, 0.0).between(new Position.Relative(1.0, 0.0)))
                        .build()
        );
    }


    // Pressing the "SUMBIT" button will add a new effort
    // to our database.
    public void newEffort(View view)
    {
        MyDBHandler dbHandler = new MyDBHandler(Results.this);
        String playersName = playerName.getText().toString();


        // Check if the player's name is blank.
        if (!playersName.equalsIgnoreCase(""))
        {

            // Find the current time and date that the game was played.
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Date date = new Date();
            String dt = formatter.format(date);


            // Convert the game's time to minutes and seconds.
            long minutes_game_time = game_time / 60;
            long seconds_game_time = game_time % 60;

            // Store the data to an Effort object, and add the values to the database.
            Effort effort = new Effort(playersName, "WIN", Integer.toString(total_points),
                    Long.toString(minutes_game_time) + ":" + Long.toString(seconds_game_time) + " mins",dt);
            dbHandler.addEffort(effort.getPlayersName(), effort.getResult(), effort.getPoints(), effort.getTime(), effort.getDate());
            playerName.setText("");
            //}

            // The player will be send to the main menu screen,
            // removing the previous activities.
            Intent i = new Intent(this, MainActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(i);
        }


    }




}