package com.example.masterminds;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class MyDBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 4;

    private static final String DATABASE_NAME = "effortDb.db";

    public static final String TABLE_EFFORTS = "efforts";

    public static final String COLUMN_ID = "_id";

    public static final String COLUMN_PLAYERNAME = "playername";

    public static final String COLUMN_DATE = "date";

    public static final String COLUMN_RESULT = "result";


    public static final String COLUMN_POINTS = "points";

    public static final String COLUMN_TIME = "time";

    public MyDBHandler(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }



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



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EFFORTS);
        onCreate(db);
    }

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

    public Effort findEffort(String playername) {
        String query = "SELECT * FROM " + TABLE_EFFORTS + " WHERE " +
                COLUMN_PLAYERNAME + " = '" + playername + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Effort effort = new Effort();
        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            effort.setID(Integer.parseInt(cursor.getString(0)));
            effort.setPlayersName(cursor.getString(1));
            effort.setResult(cursor.getString(2));
            cursor.close();
        } else {
            effort = null;
        }
        db.close();
        return effort;
    }


    public boolean deleteEffort(String playername)
    {
        boolean res = false;
        Effort effort = findEffort(playername);
        if (effort != null)
        {
            SQLiteDatabase db = this.getWritableDatabase();
            db.delete(TABLE_EFFORTS, COLUMN_ID + " = ?",
                    new String[] { String.valueOf(effort.getID())});
            res = true;
            db.close();
        }
        return res;
    }

    public ArrayList<Effort> readEfforts(String sortType)
    {
        SQLiteDatabase db = this.getReadableDatabase();


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


    public void deleteAll()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_EFFORTS);
    }
}
