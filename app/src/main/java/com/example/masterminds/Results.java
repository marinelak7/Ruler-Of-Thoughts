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

public class Results extends AppCompatActivity {


    private KonfettiView konfettiView = null;
    private Shape.DrawableShape drawableShape = null;


    EditText playerName;
    Intent i;

    long game_time;

    int total_points;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_screen);




        playerName = findViewById(R.id.playersName);

        Bundle extras = getIntent().getExtras();
        game_time = extras.getLong("ending");
        //Toast.makeText(this , Long.toString(userText) , Toast.LENGTH_LONG).show();

        total_points = extras.getInt("points");


        TextView points_gained = findViewById(R.id.points_gained_text);
        points_gained.setText("Points gained: " + Integer.toString(total_points));

        //final Drawable drawable = ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_heart);
        //drawableShape = new Shape.DrawableShape(drawable, true);
        Button ok = findViewById(R.id.submit_button);
        i = new Intent(this, MainActivity.class);
       /* ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                startActivity(i);
            }
        });*/

        final Drawable drawable = ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_heart);
        drawableShape = new Shape.DrawableShape(drawable, true);
        konfettiView = findViewById(R.id.konfettiView2);

        // 1ST WAY
        //


        /*

        EmitterConfig emitterConfig = new Emitter(5L, TimeUnit.SECONDS).perSecond(50);
        Party party = new PartyFactory(emitterConfig)
                .angle(270)
                .spread(90)
                .setSpeedBetween(1f, 5f)
                .timeToLive(2000L)
                .shapes(new Shape.Rectangle(0.2f), drawableShape)
                .sizes(new Size(12, 5f, 0.2f))
                .position(0.0, 0.0, 1.0, 0.0)
                .build();
        //konfettiView.setOnClickListener(view ->
        konfettiView.start(party);
*/

        // 2ND WAY
        //


        /*EmitterConfig emitterConfig = new Emitter(100L, TimeUnit.MILLISECONDS).max(100);
        konfettiView.start(
                new PartyFactory(emitterConfig)
                        .spread(360)
                        .shapes(Arrays.asList(Shape.Square.INSTANCE, Shape.Circle.INSTANCE, drawableShape))
                        .colors(Arrays.asList(0xfce18a, 0xff726d, 0xf4306d, 0xb48def))
                        .setSpeedBetween(0f, 30f)
                        .position(new Position.Relative(0.5, 0.3))
                        .build()
        );*/



        // 3RD WAY
        //

        /*
        EmitterConfig emitterConfig = new Emitter(5, TimeUnit.SECONDS).perSecond(30);
        konfettiView.start(
                new PartyFactory(emitterConfig)
                        .angle(Angle.RIGHT - 45)
                        .spread(Spread.SMALL)
                        .shapes(Arrays.asList(Shape.Square.INSTANCE, Shape.Circle.INSTANCE, drawableShape))
                        .colors(Arrays.asList(0xfce18a, 0xff726d, 0xf4306d, 0xb48def))
                        .setSpeedBetween(10f, 30f)
                        .position(new Position.Relative(0.0, 0.5))
                        .build(),
                new PartyFactory(emitterConfig)
                        .angle(Angle.LEFT + 45)
                        .spread(Spread.SMALL)
                        .shapes(Arrays.asList(Shape.Square.INSTANCE, Shape.Circle.INSTANCE, drawableShape))
                        .colors(Arrays.asList(0xfce18a, 0xff726d, 0xf4306d, 0xb48def))
                        .setSpeedBetween(10f, 30f)
                        .position(new Position.Relative(1.0, 0.5))
                        .build()
        );*/


        // 4TH WAY
        //


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

    public void newEffort(View view)
    {
        MyDBHandler dbHandler = new MyDBHandler(Results.this);
        String playersName = playerName.getText().toString();

        if (!playersName.equalsIgnoreCase(""))
        {
          //  Effort found = dbHandler.findEffort(playersName);
            //if (found == null)
            //{
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Date date = new Date();
            String dt = formatter.format(date);

            long minutes_game_time = game_time / 60;
            long seconds_game_time = game_time % 60;
            Effort effort = new Effort(playersName, "WIN", Integer.toString(total_points),
                    Long.toString(minutes_game_time) + ":" + Long.toString(seconds_game_time) + " mins",dt);
            dbHandler.addEffort(effort.getPlayersName(), effort.getResult(), effort.getPoints(), effort.getTime(), effort.getDate());
            playerName.setText("");
            //}

            Intent i = new Intent(this, MainActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(i);
        }


    }



    public void removeEffort (View view)
    {
        MyDBHandler dbHandler = new MyDBHandler(Results.this);
        boolean result = dbHandler.deleteEffort(playerName.getText().toString());
        if (result)
        {
            Toast.makeText(this, "Deleted", Toast.LENGTH_LONG).show();
            playerName.setText("");

        }
        else{
            Toast.makeText(this, "Not found", Toast.LENGTH_LONG).show();
            playerName.setText("");
        }
    }
    private ArrayList<Effort> effortArrayList;
  /*  public void showEffort(View view)
    {

        effortArrayList = new ArrayList<>();

        MyDBHandler dbHandler = new MyDBHandler(Results.this);

        effortArrayList = dbHandler.readEfforts();


        Toast.makeText(this, effortArrayList.get(0).getPlayersName() + " "
                 + effortArrayList.get(0).getResult(), Toast.LENGTH_LONG).show();

    }*/
}