package com.example.masterminds;

public class Effort {



    private int _id;

    private String _players_name;

    private String _result;

    private String _date;
    public Effort()
    {

    }

    public Effort(String players_name, String result, String date)
    {
        _players_name = players_name;
        _result = result;
        _date = date;
    }

    public void setID(int id) {_id = id;}

    public int getID() {return _id;}

    public void setPlayersName(String playersName) {_players_name = playersName;}

    public String getPlayersName() {return _players_name;}

    public void setDate(String date) {_date = date;}

    public String getDate() {return _date;}

    public void setResult(String result) {_result = result;}

    public String getResult() {return _result;}
}
