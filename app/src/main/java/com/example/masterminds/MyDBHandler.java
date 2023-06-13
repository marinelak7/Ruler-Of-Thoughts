package com.example.masterminds;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;


// This class handles the database of the app
public class MyDBHandler extends SQLiteOpenHelper {


    // Current version of the database.
    private static final int DATABASE_VERSION = 4;


    // The name of this database.
    private static final String DATABASE_NAME = "effortDb.db";


    // The name of this table.
    public static final String TABLE_EFFORTS = "efforts";


    // Name of the column that holds the ids of the efforts.
    public static final String COLUMN_ID = "_id";


    // Name of the column that holds the names of the players.
    public static final String COLUMN_PLAYERNAME = "playername";



    // Name of the column that holds when the efforts were made.
    public static final String COLUMN_DATE = "date";


    // Name of the columb that holds the result (win or loss) of the efforts.
    public static final String COLUMN_RESULT = "result";

    // Name of the column that holds the points of the efforts.
    public static final String COLUMN_POINTS = "points";

    // Name of the columb that holds how much time it took for the efforts
    // to complete.
    public static final String COLUMN_TIME = "time";

    // Constructor of the class
    public MyDBHandler(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }



    // Creating our database.
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_EFFORTS_TABLE = "CREATE TABLE " +
                TABLE_EFFORTS + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY," +
                COLUMN_PLAYERNAME + " INTEGER," +
                COLUMN_RESULT + " INTEGER," +
                COLUMN_POINTS + " INTEGER," +
                COLUMN_TIME + " INTEGER," +
                COLUMN_DATE + " INTEGER)";

        db.execSQL(CREATE_EFFORTS_TABLE);
    }



    // The upgrade method for the database.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EFFORTS);
        onCreate(db);
    }

    // This method will be used to store new efforts, when the efforts
    // are completed.
    public void addEffort(String playerName, String result, String points, String time, String date)
    {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COLUMN_PLAYERNAME, playerName);
        values.put(COLUMN_RESULT, result);
        values.put(COLUMN_POINTS, points);
        values.put(COLUMN_TIME, time);
        values.put(COLUMN_DATE, date);



        db.insert(TABLE_EFFORTS, null, values);
        db.close();
    }

    // This method will return an ArrayList with the values of the database
    // being stored in Effort objects.
    public ArrayList<Effort> readEfforts(String sortType)
    {
        SQLiteDatabase db = this.getReadableDatabase();

        // Sort the efforts with the selected sort type from the user.
        String str = " ORDER BY " + MyDBHandler.COLUMN_ID + " DESC";
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_EFFORTS + sortType , null);
        //String str = + " ORDER BY " + COLUMN_DATE + " DESC";
        ArrayList<Effort> effortslist = new ArrayList<>();

        if (cursor.moveToFirst())
        {
            do {
                    effortslist.add(new Effort(
                            cursor.getString(1),
                            cursor.getString(2),
                            cursor.getString(3),
                            cursor.getString(4),
                            cursor.getString(5)));

            } while (cursor.moveToNext());
        }

        cursor.close();
        return effortslist;
    }



    // Method of the "DELETE ALL TRIES" button, to delete all efforts from
    // the database, if the player has agreed to the according message (dialog).
    public void deleteAll()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_EFFORTS);
    }
}
