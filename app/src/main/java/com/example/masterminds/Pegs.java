package com.example.masterminds;


import android.graphics.Color;



// This class represent the positions of the game.
public class Pegs {



    // The id of this particular imagebutton.
    private int id;

    // Store the colour of this choice.
    private String color;

    // Store the integer value of this colour.
    private int intColor;


    // Default constructor.
    public Pegs()
    {
        id = 0;
        color = "Black";
        intColor = Color.BLACK;
    }


    // Constructor of the class
    public Pegs(int _id){

        id = _id;
        color = "Black";
        intColor = Color.BLACK;
    }


    // Setter and getter for the members.
    public int getId()
    {
        return id;
    }
    public void setId(int _id)
    {
        id = _id;
    }
    public String getColor()
    {
        return color;
    }
    public void setColor(String _color)
    {
        color = _color;
    }

    public int getIntColor () {return intColor;}
    public void setIntColor(int _int_color) { intColor = _int_color;}


}
