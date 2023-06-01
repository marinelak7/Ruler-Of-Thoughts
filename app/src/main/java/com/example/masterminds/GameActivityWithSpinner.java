package com.example.masterminds;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;


import nl.dionsegijn.konfetti.core.models.Shape;

import nl.dionsegijn.konfetti.xml.KonfettiView;

public class GameActivityWithSpinner extends AppCompatActivity {





    TextView tvt;
    Pegs [][] pos = new Pegs[9][4];
    String []hidden_code = new String[4] ;
    String []users_answer = new String[4];
    int [] correct_color_wrong_position = new int[9];
    int [] correct_color_correct_position = new int[9];
    int turns;
    int [] checkAnswers = new int[9];
    HashMap<String, Integer> unique_answers;
    HashMap<String, Integer> unique_codes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        turns = 9; // Player can try that many times.



        ImageButton bt;

            pos[0][0] = new Pegs(R.id.position__1);
            pos[0][1] = new Pegs(R.id.position__2);
            pos[0][2] = new Pegs(R.id.position__3);
            pos[0][3] = new Pegs(R.id.position__4);

            pos[1][0] = new Pegs(R.id.position__5);
            pos[1][1] = new Pegs(R.id.position__6);
            pos[1][2] = new Pegs(R.id.position__7);
            pos[1][3] = new Pegs(R.id.position__8);

            pos[2][0] = new Pegs(R.id.position__9);
            pos[2][1] = new Pegs(R.id.position__10);
            pos[2][2] = new Pegs(R.id.position__11);
            pos[2][3] = new Pegs(R.id.position__12);

            pos[3][0] = new Pegs(R.id.position__13);
            pos[3][1] = new Pegs(R.id.position__14);
            pos[3][2] = new Pegs(R.id.position__15);
            pos[3][3] = new Pegs(R.id.position__16);

            pos[4][0] = new Pegs(R.id.position__17);
            pos[4][1] = new Pegs(R.id.position__18);
            pos[4][2] = new Pegs(R.id.position__19);
            pos[4][3] = new Pegs(R.id.position__20);

            pos[5][0] = new Pegs(R.id.position__21);
            pos[5][1] = new Pegs(R.id.position__22);
            pos[5][2] = new Pegs(R.id.position__23);
            pos[5][3] = new Pegs(R.id.position__24);

            pos[6][0] = new Pegs(R.id.position__25);
            pos[6][1] = new Pegs(R.id.position__26);
            pos[6][2] = new Pegs(R.id.position__27);
            pos[6][3] = new Pegs(R.id.position__28);

            pos[7][0] = new Pegs(R.id.position__29);
            pos[7][1] = new Pegs(R.id.position__30);
            pos[7][2] = new Pegs(R.id.position__31);
            pos[7][3] = new Pegs(R.id.position__32);

            pos[8][0] = new Pegs(R.id.position__33);
            pos[8][1] = new Pegs(R.id.position__34);
            pos[8][2] = new Pegs(R.id.position__35);
            pos[8][3] = new Pegs(R.id.position__36);


            createCode();


            unique_codes = new HashMap<>();

            unique_answers = new HashMap<>();


            correct_color_correct_position[0] = R.id.red_index_1;
            correct_color_correct_position[1] = R.id.red_index_2;
            correct_color_correct_position[2] = R.id.red_index_3;
            correct_color_correct_position[3] = R.id.red_index_4;
            correct_color_correct_position[4] = R.id.red_index_5;
            correct_color_correct_position[5] = R.id.red_index_6;
            correct_color_correct_position[6] = R.id.red_index_7;
            correct_color_correct_position[7] = R.id.red_index_8;
            correct_color_correct_position[8] = R.id.red_index_9;


            correct_color_wrong_position[0] = R.id.white_index_1;
            correct_color_wrong_position[1] = R.id.white_index_2;
            correct_color_wrong_position[2] = R.id.white_index_3;
            correct_color_wrong_position[3] = R.id.white_index_4;
            correct_color_wrong_position[4] = R.id.white_index_5;
            correct_color_wrong_position[5] = R.id.white_index_6;
            correct_color_wrong_position[6] = R.id.white_index_7;
            correct_color_wrong_position[7] = R.id.white_index_8;
            correct_color_wrong_position[8] = R.id.white_index_9;

            checkAnswers[0] = R.id.checkAnswer_1;
            checkAnswers[1] = R.id.checkAnswer_2;
            checkAnswers[2] = R.id.checkAnswer_3;
            checkAnswers[3] = R.id.checkAnswer_4;
            checkAnswers[4] = R.id.checkAnswer_5;
            checkAnswers[5] = R.id.checkAnswer_6;
            checkAnswers[6] = R.id.checkAnswer_7;
            checkAnswers[7] = R.id.checkAnswer_8;
            checkAnswers[8] = R.id.checkAnswer_9;

            for (int i = 0; i < 4; i++) {
                if (unique_codes.containsKey(hidden_code[i])) {
                    unique_codes.put(hidden_code[i], unique_codes.get(hidden_code[i]) + 1);
                    continue;
                }
                unique_codes.put(hidden_code[i], 1);
            }
            //Toast toast = Toast.makeText(getApplicationContext(), "HELLO", Toast.LENGTH_SHORT);
            //toast.show();


        }

    int current_turn = 1;

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
                        temp.setIntColor(Color.RED);
                    }
                    else if (str.equalsIgnoreCase("-Blue"))
                    {
                        im.setColorFilter(Color.BLUE);
                        temp.setColor("Blue");
                        temp.setIntColor(Color.BLUE);
                    }
                    else if (str.equalsIgnoreCase("-Yellow"))
                    {
                        im.setColorFilter(Color.YELLOW);
                        temp.setColor("Yellow");
                        temp.setIntColor(Color.YELLOW);
                    }
                    else if (str.equalsIgnoreCase("-Green"))
                    {
                        im.setColorFilter(Color.GREEN);
                        temp.setColor("Green");
                        temp.setIntColor(Color.GREEN);
                    }
                    else if (str.equalsIgnoreCase("-Gray"))
                    {
                        im.setColorFilter(Color.GRAY);
                        temp.setColor("Gray");
                        temp.setIntColor(Color.GRAY);
                    }
                    else if (str.equalsIgnoreCase("-White"))
                    {
                        im.setColorFilter(Color.WHITE);
                        temp.setColor("White");
                        temp.setIntColor(Color.WHITE);
                    }
                    else if (str.equalsIgnoreCase("-Magenta"))
                    {
                        im.setColorFilter(Color.MAGENTA);
                        temp.setColor("Magenta");
                        temp.setIntColor(Color.MAGENTA);
                    }
                    else if (str.equalsIgnoreCase("-Cyan"))
                    {
                        im.setColorFilter(Color.CYAN);
                        temp.setColor("Cyan");
                        temp.setIntColor(Color.CYAN);
                    }
                    else
                    {
                        im.setColorFilter(Color.BLACK);
                        temp.setColor("Black");
                        temp.setIntColor(Color.BLACK);
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
        FloatingActionButton choose = findViewById(checkAnswers[current_turn - 1]);
        if (flag == 1)
        {
            choose.setVisibility(View.VISIBLE);
            //tvt.setText("No clear positions.");
        }
        else
        {
            choose.setVisibility(View.INVISIBLE);
            //tvt.setText("Some positions are clear.");
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


        FloatingActionButton check = findViewById(checkAnswers[current_turn - 1]);

        check.setVisibility(View.INVISIBLE);

        current_turn++;

        if (current_turn > turns)
        {
            Dialog dialog = new Dialog(this);
            dialog.setContentView(R.layout.results_loss);

            Button ok = dialog.findViewById(R.id.ok_button_loss);
            Intent i = new Intent(this, ChooseDifficultyMenuVer1.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            ok.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(i);
                }
            });


            dialog.show();
        }

        unique_answers.clear();
    }


    void createCode()
    {
        ArrayList<String> list = new ArrayList<>();

        list.add("Red"); list.add("Blue");
        list.add("Yellow"); list.add("Green");
        list.add("Cyan"); list.add("White");
        list.add("Magenta"); list.add("Gray");

        Collections.shuffle(list);

        for (int i = 0; i < 4; i++)
        {
            hidden_code[i] = list.get(i);
        }


    }

    public void showAnswer(View view)
    {
        Toast.makeText(this, hidden_code[0] + " " + hidden_code[1] + " " +
                hidden_code[2] + " " + hidden_code[3], Toast.LENGTH_LONG).show();
    }



  /*  @Override
    protected void onSaveInstanceState(Bundle outState)
    {


        for (int i = 0; i < turns; i++)
        {
            for (int j = 0; j < 4; j++)
            {
                outState.putCharSequence("position_" + 4*i + j + 1 + "_color", pos[i][j].getColor());
                outState.putInt("position__" + 4*i + j + 1 + "_intColor", pos[i][j].getIntColor());
            }
        }
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle outState)
    {
        super.onRestoreInstanceState(outState);
        ImageButton bt;
        if (outState != null)
        {
            for (int i = 0; i < turns; i++)
            {
                for (int j = 0; j < 4; j++)
                {
                    bt = findViewById(pos[i][j].getId());
                    bt.setColorFilter(outState.getInt("position__" + 4*i + j + 1 + "_intColor"));
                }
            }
        }

    }*/
}
