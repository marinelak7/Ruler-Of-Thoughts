package com.example.masterminds;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.core.content.ContextCompat;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.HashMap;

import nl.dionsegijn.konfetti.core.Angle;
import nl.dionsegijn.konfetti.core.Party;
import nl.dionsegijn.konfetti.core.PartyFactory;
import nl.dionsegijn.konfetti.core.Position;
import nl.dionsegijn.konfetti.core.Spread;
import nl.dionsegijn.konfetti.core.emitter.Emitter;
import nl.dionsegijn.konfetti.core.emitter.EmitterConfig;
import nl.dionsegijn.konfetti.core.models.Shape;
import nl.dionsegijn.konfetti.core.models.Size;
import nl.dionsegijn.konfetti.xml.KonfettiView;

public class GameActivityWithSpinner extends AppCompatActivity {

    FloatingActionButton actionButton;


    private KonfettiView konfettiView = null;
    private Shape.DrawableShape drawableShape = null;
    TextView tvt;
    Pegs [][] pos = new Pegs[2][4];
    String []hidden_code = new String[4] ;
    String []users_answer = new String[4];
    int [] correct_color_wrong_position = new int[2];
    int [] correct_color_correct_position = new int[2];
    HashMap<String, Integer> unique_answers;
    HashMap<String, Integer> unique_codes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        pos[0][0] = new Pegs(R.id.position_29);
        pos[0][1] = new Pegs(R.id.position_30);
        pos[0][2] = new Pegs(R.id.position_31);
        pos[0][3] = new Pegs(R.id.position_32);
        pos[1][0] = new Pegs(R.id.position_33);
        pos[1][1] = new Pegs(R.id.position_34);
        pos[1][2] = new Pegs(R.id.position_35);
        pos[1][3] = new Pegs(R.id.position_36);
        unique_codes = new HashMap<>();
        hidden_code[0] = "Red";
        hidden_code[1] = "Blue";
        hidden_code[2] = "Green";
        hidden_code[3] = "Gray";
        unique_answers = new HashMap<>();
        correct_color_correct_position[0] = R.id.red_index_1;
        correct_color_correct_position[1] = R.id.red_index_2;

        correct_color_wrong_position[0] = R.id.white_index_1;
        correct_color_wrong_position[1] = R.id.white_index_2;

        for (int i = 0; i < 4; i++)
        {
            if (unique_codes.containsKey(hidden_code[i]))
            {
                unique_codes.put(hidden_code[i], unique_codes.get(hidden_code[i]) + 1);
                continue;
            }
            unique_codes.put(hidden_code[i], 1);
        }
        //Toast toast = Toast.makeText(getApplicationContext(), "HELLO", Toast.LENGTH_SHORT);
        //toast.show();
        tvt = findViewById(R.id.numberOfTurns);
        tvt.setText("Turn 1");
    }
    int current_turn = 1;
    int turns = 9;
    String str = "";
    Pegs temp = null;

    public void colorChoiceSpinner(View view)
    {

       // Toast toast = Toast.makeText(GameActivityWithSpinner.this, "Hey there", Toast.LENGTH_SHORT);
       // toast.show();
        
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.color_choice_spinner);
        tvt = findViewById(R.id.textView5);

        if (!checkId(view.getId()))
        {
            tvt.setText("This is not the correct turn.");
        }
        else
        {


        for (int i = 0; i < 4; i++)
        {
            if (view.getId() == pos[current_turn - 1][i].getId())
            {
                temp = pos[current_turn - 1][i];
                break;
            }
        }



        Button ok = dialog.findViewById(R.id.ok_button_spinner);
        ImageButton im = findViewById(temp.getId());
        Spinner sp = dialog.findViewById(R.id.color_spinner);
        if (temp != null)
        {
            ok.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    str = sp.getSelectedItem().toString();
                    if (str.equalsIgnoreCase("-Red"))
                    {
                        im.setColorFilter(Color.RED);
                        temp.setColor("Red");
                    }
                    else if (str.equalsIgnoreCase("-Blue"))
                    {
                        im.setColorFilter(Color.BLUE);
                        temp.setColor("Blue");
                    }
                    else if (str.equalsIgnoreCase("-Yellow"))
                    {
                        im.setColorFilter(Color.YELLOW);
                        temp.setColor("Yellow");
                    }
                    else if (str.equalsIgnoreCase("-Green"))
                    {
                        im.setColorFilter(Color.GREEN);
                        temp.setColor("Green");
                    }
                    else if (str.equalsIgnoreCase("-Gray"))
                    {
                        im.setColorFilter(Color.GRAY);
                        temp.setColor("Gray");
                    }
                    else if (str.equalsIgnoreCase("-White"))
                    {
                        im.setColorFilter(Color.WHITE);
                        temp.setColor("White");
                    }
                    else if (str.equalsIgnoreCase("-Magenta"))
                    {
                        im.setColorFilter(Color.MAGENTA);
                        temp.setColor("Magenta");
                    }
                    else if (str.equalsIgnoreCase("-Cyan"))
                    {
                        im.setColorFilter(Color.CYAN);
                        temp.setColor("Cyan");
                    }
                    else
                    {
                        im.setColorFilter(Color.BLACK);
                        temp.setColor("Black");
                    }

                    checkAnswer();

                    dialog.dismiss();

                }

            });

        }



        dialog.show();

        }
    }


    private boolean checkId(int _id)
    {
        for (int i = 0; i < 4; i++)
        {
            if (pos[current_turn - 1][i].getId() == _id)
            {
                return true;
            }
        }
        return false;
    }
    private boolean checkAnswer()
    {
        int flag = 1;
        tvt = findViewById(R.id.textView5);
        for (int i = 0; i < 4; i++)
        {
            if (pos[current_turn - 1][i].getColor().equalsIgnoreCase("Black"))
            {
                flag = 0;
                break;
            }
        }
        FloatingActionButton choose = findViewById(R.id.checkAnswer);
        if (flag == 1)
        {
            choose.setVisibility(View.VISIBLE);
            tvt.setText("No clear positions.");
        }
        else
        {
            choose.setVisibility(View.INVISIBLE);
            tvt.setText("Some positions are clear.");
        }

        return false;
    }

    public void checkAnswerMethod(View v) {

        int red_index = 0, white_index = 0;

        tvt = findViewById(R.id.textView5);


        for (int i = 0; i < 4; i++)
        {
            if (unique_answers.containsKey(pos[current_turn - 1][i].getColor()))
            {
                unique_answers.put(pos[current_turn - 1][i].getColor(), unique_answers.get(pos[current_turn - 1][i].getColor()) + 1);
                continue;
            }
            unique_answers.put(pos[current_turn - 1][i].getColor(), 1);
        }


        /*String str = "";
        for (String item:unique_answers.keySet())
        {
            str += item + " " + unique_answers.get(item) + " ";
        }
        tvt.setText(str);*/


        HashMap<String, Integer> copy_code = new HashMap<>(unique_codes);

        for (int i = 0; i < 4; i++)
        {
            if (hidden_code[i].equalsIgnoreCase(pos[current_turn - 1][i].getColor()))
            {
                if (!unique_answers.isEmpty()) {
                    red_index++;
                    unique_answers.put(hidden_code[i], unique_answers.get(hidden_code[i]) - 1);
                    copy_code.put(hidden_code[i], copy_code.get(hidden_code[i]) - 1);
                }
            }
        }
        tvt = findViewById(R.id.numberOfTurns);
        for (String item : copy_code.keySet())
        {
            if (copy_code.get(item) != 0)
            {
                if (unique_answers.containsKey(item))
                {
                    if (unique_answers.get(item) <= copy_code.get(item))
                    {
                        white_index += unique_answers.get(item);
                    }
                    else
                    {
                        white_index += copy_code.get(item);
                    }
                }
            }
        }


/*
        tvt = findViewById(R.id.textView5);
        String str1 = "";
        for (String item:copy_code.keySet())
        {
            str1 += item + " " + copy_code.get(item) + " ";
        }
        tvt.setText(str1);



        for (String item : copy_code.keySet())
        {
            if (unique_answers.containsKey(item))
            {
                if (unique_answers.get(item) <= copy_code.get(item))
                {
                    white_index += unique_answers.get(item);
                }
                else
                {
                    white_index += unique_answers.get(item);
                }
            }
        }

*/
       /* for (int i = 0; i < 4; i++)
        {
            for (int j = 0; j < 4; j++)
            {
                if (pos[current_turn - 1][i].getColor().equalsIgnoreCase(hidden_code[j]))
                {
                    if (i == j)
                    {
                        red_index++;
                        break;
                    }
                    else
                    {
                        if (pos[current_turn - 1][i].getColor().equalsIgnoreCase(hidden_code[i]))
                        {
                            red_index++;
                            break;
                        }

                        if (pos[current_turn - 1][j].getColor().equalsIgnoreCase(hidden_code[j]))
                        {
                            red_index++;
                            break;
                        }



                        white_index++;
                        break;
                    }
                }
            }
        }
*/
        if (red_index == 4)
        {




            Intent i = new Intent(this, Results.class);


            startActivity(i);







        }


       // tvt.setText(hidden_code[0] + hidden_code[1] + hidden_code[2] + hidden_code[3]);

        tvt = findViewById(correct_color_correct_position[current_turn - 1]);
        tvt.setText(Integer.toString(red_index));
        tvt = findViewById(correct_color_wrong_position[current_turn - 1]);
        tvt.setText(Integer.toString(white_index));

        current_turn++;
        FloatingActionButton check = findViewById(R.id.checkAnswer);
        tvt = findViewById(R.id.numberOfTurns);
        tvt.setText("Turn " + current_turn);
        check.setVisibility(View.INVISIBLE);


        unique_answers.clear();
    }
}
