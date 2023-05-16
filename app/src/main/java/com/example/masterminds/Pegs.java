package com.example.masterminds;



public class Pegs {



    private int id;
    private String color;

    public Pegs()
    {
        id = 0;
        color = "Black";
    }
    public Pegs(int _id){

        id = _id;
        color = "Black";
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
    static private int difficulty = -1;
    static public void setDifficulty(int _difficulty)
    {
        difficulty = _difficulty;
    }
    public int getDifficulty()
    {
        return difficulty;
    }
}
