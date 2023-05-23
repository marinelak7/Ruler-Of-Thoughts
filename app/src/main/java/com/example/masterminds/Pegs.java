package com.example.masterminds;


import android.graphics.Color;

public class Pegs {



    private int id;
    private String color;
    private int intColor;
    public Pegs()
    {
        id = 0;
        color = "Black";
        intColor = Color.BLACK;
    }
    public Pegs(int _id){

        id = _id;
        color = "Black";
        intColor = Color.BLACK;
    }

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
    static private int difficulty = -1;
    static public void setDifficulty(int _difficulty)
    {
        difficulty = _difficulty;
    }
    static public int getDifficulty()
    {
        return difficulty;
    }
}
