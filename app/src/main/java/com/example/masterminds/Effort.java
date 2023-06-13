package com.example.masterminds;



// The Effort class will be used to store the database values so
// that we can handle them with an easier way.
public class Effort {



    // The id of this database entry.
    private int _id;


    // The name that the player chose.
    private String _players_name;


    // Store whether the player won or lost.
    private String _result;


    // Store when was the game played.
    private String _date;


    // Store the number of points the player gained.
    private String _points;


    // Store how much did the player took to finish the game.
    private String _time;


    // Empty constructor.
    public Effort()
    {

    }


    // The constructor of the class.
    public Effort(String players_name, String result, String points, String time, String date)
    {
        _players_name = players_name;
        _result = result;
        _points = points;
        _time = time;
        _date = date;
    }


    // Setters and getter for our members.
    //
    public void setID(int id) {_id = id;}

    public int getID() {return _id;}

    public void setPlayersName(String playersName) {_players_name = playersName;}
    public String getPlayersName() {return _players_name;}

    public void setDate(String date) {_date = date;}
    public String getDate() {return _date;}

    public void setResult(String result) {_result = result;}
    public String getResult() {return _result;}


    public void setPoints(String points) {_points = points;}
    public String getPoints() {return _points;}

    public void setTime(String time) {_time = time;}
    public String getTime() {return _time;}
}
