package com.example.masterminds;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;


import nl.dionsegijn.konfetti.core.models.Shape;

import nl.dionsegijn.konfetti.xml.KonfettiView;


// The class that holds the logic of the game
//
public class GameActivityWithSpinner extends AppCompatActivity {

    TextView tvt;

    // This two dimension array will hold the positions of
    // all the tries and the colour that the player chooses.
    Pegs [][] pos = new Pegs[9][4];

    // The player must find the hidden colours and their according positions,
    // that are being held in this array.
    String []hidden_code = new String[4] ;

    // The player's choice of colours and their positions.
    String []users_answer = new String[4];

    // This array holds the ids of the textviews that display
    // the number of colours that exist in the hidden code, but
    // are in different positions, for each turn (9 ,in total, turns).
    int [] correct_color_wrong_position = new int[9];

    // This array holds the ids of the textviews that display
    // the number of colours that exist in the hidden code and
    // are in the right position, for each turn (9 ,in total, turns).
    int [] correct_color_correct_position = new int[9];

    // The total turns of the game.
    int turns;

    // The ids of the floating action buttons that check
    // the player's choices of this turn, are being held in this array.
    int [] checkAnswers = new int[9];

    // The unique colours selected by the player,
    // along with the number of their appearances in this turn
    // will be stored in this HashMap.
    HashMap<String, Integer> unique_answers;

    // The unique colours of the hidden code,
    // along with the number of their appearances
    // will be stored in this HashMap.
    HashMap<String, Integer> unique_codes;


    // The start of calculating the user's game time.
    long beginning;

    // Player's total points of this game will be stored here.
    int points;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);


        // Uncomment these four lines below, so that the buttons
        // of "SHOW ANSWER" and "FORCE DEFEAT" will appear.
        // You can see the hidden code's answer if you press the
        // show answer button and you can also instantly lose if
        // you press the force defeat button. See the methods
        // showAnswer, forceDefeat at the end of the file.

        //Button show_answer = findViewById(R.id.show_answer_button);
        //show_answer.setVisibility(View.VISIBLE);
        //Button force_defeat = findViewById(R.id.force_defeat);
        //force_defeat.setVisibility(View.VISIBLE);

        // The default normal difficulty.
        turns = 9;

        // Start of calculating game time.
        beginning= System.currentTimeMillis();
        //Toast.makeText(this , Long.toString(System.currentTimeMillis()) , Toast.LENGTH_LONG).show(); ;

        ImageButton bt;


            // The ids of the imagebuttons that represent the
            // game will be stored here, in the next lines, for all 9 turns.
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


            // Method to form the hidden code.
            createCode();


            unique_codes = new HashMap<>();

            unique_answers = new HashMap<>();


            // The arrays that hold the ids of the textviews that appear
            // a turn has been completed will be initialized in the lines.
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

            // Initialize the array that holds the ids of the floating buttons
            checkAnswers[0] = R.id.checkAnswer_1;
            checkAnswers[1] = R.id.checkAnswer_2;
            checkAnswers[2] = R.id.checkAnswer_3;
            checkAnswers[3] = R.id.checkAnswer_4;
            checkAnswers[4] = R.id.checkAnswer_5;
            checkAnswers[5] = R.id.checkAnswer_6;
            checkAnswers[6] = R.id.checkAnswer_7;
            checkAnswers[7] = R.id.checkAnswer_8;
            checkAnswers[8] = R.id.checkAnswer_9;


            // Find the unique colours and the number of times they appear.
            for (int i = 0; i < 4; i++) {
                if (unique_codes.containsKey(hidden_code[i])) {
                    unique_codes.put(hidden_code[i], unique_codes.get(hidden_code[i]) + 1);
                    continue;
                }
                unique_codes.put(hidden_code[i], 1);
            }



        }



    // The current turn that the game is at.
    int current_turn = 1;

    String str = "";
    Pegs temp = null;


    // Method, for the player to choose a colour for this specific position.
    public void colorChoiceSpinner(View view)
    {



        // Create a dialog that holds a spinner.
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.color_choice_spinner);
        tvt = findViewById(R.id.textView5);


        // If the player chooses a position from a turn that is not
        // the current, a toast message will appear with a corresponding message,
        // differently the dialog will appear.
        if (!checkId(view.getId()))
        {
            Toast.makeText(this, "This is not the correct turn." , Toast.LENGTH_SHORT ).show();
        }
        else
        {

        // Find the id of the selected position (imagebutton).
        for (int i = 0; i < 4; i++)
        {
            if (view.getId() == pos[current_turn - 1][i].getId())
            {
                temp = pos[current_turn - 1][i];
                break;
            }
        }


        // Initialize the elements of the dialog.
        Button ok = dialog.findViewById(R.id.ok_button_spinner);
        ImageButton im = findViewById(temp.getId());
        Spinner sp = dialog.findViewById(R.id.color_spinner);



        if (temp != null)
        {

            // Set the onClick ability of the dialog's ok button.
            ok.setOnClickListener(new View.OnClickListener() {



                @Override
                public void onClick(View v) {

                    // Get the player's choice of colour from the spinner.
                    str = sp.getSelectedItem().toString();


                    // Check which colour was selected, and set the values
                    // in the objects of the Pegs class.
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

                    // This method will check what the player has chosen.
                    checkAnswer();


                    // Close the dialog after the player has pressed ok.
                    dialog.dismiss();

                }

            });

        }


        // The dialog will appear.
        dialog.show();

        }
    }



    // If the selected position is from the row that is in this current turn
    // this method returns "true", differently "false".
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


        // If the player has selected the default option "BLACK",
        // nothing should happen.
        for (int i = 0; i < 4; i++)
        {
            if (pos[current_turn - 1][i].getColor().equalsIgnoreCase("Black"))
            {
                flag = 0;
                break;
            }
        }
        // The action button of this particular turn.
        FloatingActionButton choose = findViewById(checkAnswers[current_turn - 1]);


        // If the player has chosen a colour that is not "BLACK",
        // for all positions the floating action button will appear for this turn.
        if (flag == 1)
        {
            choose.setVisibility(View.VISIBLE);
            //tvt.setText("No clear positions.");
        }
        else
        {
            // If the player chooses "BLACK", while all positions are not
            // the floating action button will hide.
            choose.setVisibility(View.INVISIBLE);
            //tvt.setText("Some positions are clear.");
        }

        return false;
    }



    // This method is called when the check answer button has appeared,
    // and the player chooses to see their progress of this game.
    public void checkAnswerMethod(View v) {

        // Red index will hold the number of the correct colours-positions.
        // White index will hold the number of the correct colours-wrong positions.
        int red_index = 0, white_index = 0;

        tvt = findViewById(R.id.textView5);

        // Initialize the hash table of the player's answer.
        for (int i = 0; i < 4; i++)
        {
            if (unique_answers.containsKey(pos[current_turn - 1][i].getColor()))
            {
                unique_answers.put(pos[current_turn - 1][i].getColor(), unique_answers.get(pos[current_turn - 1][i].getColor()) + 1);
                continue;
            }
            unique_answers.put(pos[current_turn - 1][i].getColor(), 1);
        }


        // Make a copy of the hidden code, so that you can make comparisons
        // with the user's one, and also change values.
        HashMap<String, Integer> copy_code = new HashMap<>(unique_codes);


        // Check for correct colours-positions of the user's answers
        // with the hidden code.
        for (int i = 0; i < 4; i++)
        {
            if (hidden_code[i].equalsIgnoreCase(pos[current_turn - 1][i].getColor()))
            {
                // If the colours chosen by the player appear in the same positions
                // as in the hidden code, make some changes and reduce the number that has
                // appeared in the hidden code's hash table.
                if (!unique_answers.isEmpty()) {
                    red_index++;
                    unique_answers.put(hidden_code[i], unique_answers.get(hidden_code[i]) - 1);
                    copy_code.put(hidden_code[i], copy_code.get(hidden_code[i]) - 1);
                }
            }
        }


        // Check if there are still colour that appear in both these hash tables,
        // and add the smallest number of appearances to the white_index variable,
        // so that more appearances will not be counted.
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




        // Check if the player has won, and go to the results screen,
        // while sending their stats of this game.
        if (red_index == 4)
        {

            points = 100 - (current_turn-1)*10 ;




            long ending= (System.currentTimeMillis() - beginning)/1000 ;
            Intent i = new Intent(this, Results.class);
            i.putExtra("ending", ending);
            i.putExtra("points", points);
            startActivity(i);








        }



        // Show the numbers of the correct colours-positions
        // and the nuumber of the correct colour wrong positions,
        // to the screen, for this particular turn.
        tvt = findViewById(correct_color_correct_position[current_turn - 1]);
        tvt.setText(Integer.toString(red_index));
        tvt = findViewById(correct_color_wrong_position[current_turn - 1]);
        tvt.setText(Integer.toString(white_index));

        // Hide this particular's turn check answer button.
        FloatingActionButton check = findViewById(checkAnswers[current_turn - 1]);

        check.setVisibility(View.INVISIBLE);



        // Go to the next turn.
        current_turn++;


        // See if the player has lost.
        checkDefeat();

        // Clear the hash table of the player's answers,
        // for the next turn.
        unique_answers.clear();
    }




    // This method will check if the player has no more turns
    // and has lost.
    void checkDefeat()
    {


        // If no more turns are left a dialog will appear,
        // setting the player's stats and asking for their
        // names so that the effort will enter the app's database.
        if (current_turn > turns)
        {
            points = 0 ;

            Dialog dialog = new Dialog(this);
            dialog.setContentView(R.layout.results_loss);

            EditText playerName = dialog.findViewById(R.id.players_name_loss);

            // Create a database handler.
            MyDBHandler dbHandler = new MyDBHandler(GameActivityWithSpinner.this);

            Button ok_loss = dialog.findViewById(R.id.ok_button_loss);

            Intent i = new Intent(this, MainActivity.class);

            ok_loss.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String playersName = playerName.getText().toString();


                    // Check if the player's name is blank, and
                    // wait till a not blank has been given.
                    if (!playersName.equalsIgnoreCase(""))
                    {


                        // Find the time and date of the system that particular moment.
                        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                        Date date = new Date();
                        String dt = formatter.format(date);

                        // Store the player's stats, and add the entry to the database.
                        Effort effort = new Effort(playersName, "LOSS", Integer.toString(0),
                                " - ",dt);
                        dbHandler.addEffort(effort.getPlayersName(), effort.getResult(), effort.getPoints(), effort.getTime(), effort.getDate());
                        playerName.setText("");
                        //}

                        // Clear all previous activities and go to the menu.
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(i);
                    }
                }
            });


            dialog.show();
        }
    }

    // The hidden code will be created in this method.
    void createCode()
    {
        // All 8 different colours will be stored here.
        ArrayList<String> list = new ArrayList<>();

        // Add the colours to the array list.
        list.add("Red"); list.add("Blue");
        list.add("Yellow"); list.add("Green");
        list.add("Cyan"); list.add("White");
        list.add("Magenta"); list.add("Gray");

        // Use the shuffle method of Collections to randomize the
        // turns of apppearing.
        Collections.shuffle(list);


        // Initialize the array of the hidden code.
        for (int i = 0; i < 4; i++)
        {
            hidden_code[i] = list.get(i);
        }


    }


    // Method where a toast message appears that shows the colour
    // and their positions of the hidden code. This method was created
    // to check the functionality of the game and it can be used if there is
    // no time to play the game.
    public void showAnswer(View view)
    {
        Toast.makeText(this, hidden_code[0] + " " + hidden_code[1] + " " +
                hidden_code[2] + " " + hidden_code[3], Toast.LENGTH_LONG).show();
    }



    // This method will instantly make the player lose. It was created to
    // check the functionality of the game. Also if you do not want to play
    // all the 9 turns so that you can lose, you press the button "FORCE DEFEAT".
    public void forceDefeat(View view)
    {
        current_turn = 10;
        checkDefeat();
    }

}