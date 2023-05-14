package com.example.masterminds;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioGroup;

public class GameActivityWithRadioGroup extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
    }

    int n = -1;
    public void colorChoiceRadioGroup(View view)
    {


        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.color_choice_radiogroup);
        RadioGroup colour = dialog.findViewById(R.id.colorGroup);
        Button ok = dialog.findViewById(R.id.ok_button_radiogroup);
        ImageButton im = findViewById(R.id.position1);

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                n = colour.getCheckedRadioButtonId();

                if (n == R.id.red_radiobutton)
                {
                    im.setColorFilter(Color.RED);
                }
                else if (n == R.id.blue_radiobutton)
                {
                    im.setColorFilter(Color.BLUE);
                }
                else if (n == R.id.green_radiobutton)
                {
                    im.setColorFilter(Color.GREEN);
                }
                else if (n == R.id.yellow_radiobutton)
                {
                    im.setColorFilter(Color.YELLOW);
                }
                else if (n == R.id.orange_radiobutton)
                {
                    im.setColorFilter(Color.argb(200,200, 200, 200));
                }
                else if (n == R.id.white_radiobutton)
                {
                    im.setColorFilter(Color.WHITE);
                }
                else if (n == R.id.grey_radiobutton)
                {
                    im.setColorFilter(Color.GRAY);
                }
                else if (n == R.id.purple_radiobutton)
                {
                    im.setColorFilter(Color.argb(100, 100, 100, 100));
                }
                else
                {
                    im.setColorFilter(Color.BLACK);
                }
                dialog.dismiss();

            }
        });

        dialog.show();
    }
}