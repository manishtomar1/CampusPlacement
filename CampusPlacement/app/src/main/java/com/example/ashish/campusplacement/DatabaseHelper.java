package com.example.ashish.campusplacement;

/**
 * Created by ashish on 28/2/18.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ProgrammingKnowledge on 4/3/2015.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    //Database helper object
    private static DatabaseHelper databaseHelper;
    //Database Name
    public static final String DATABASE_NAME = "Placement.db";

    //Define all Tables
    //Student Table
    public static final String student_table = "student_table";
    public static final String col_1_student_table = "Roll_No.";
    public static final String col_2_student_table = "Name";
    public static final String col_3_student_table = "Degree";
    public static final String col_4_student_table = "Branch";


    //Create queries for all tables
    //Student table create query
    public static final String create_student_table="create table " + student_table +" ("+col_1_student_table+" TEXT PRIMARY KEY ,"+col_2_student_table+" TEXT,"+col_3_student_table+" TEXT,"+col_4_student_table+" TEXT)";


    //private constructor for singleton pattern
    private DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    public static synchronized DatabaseHelper getInstance(Context context) {

        if (databaseHelper == null) {
            databaseHelper = new DatabaseHelper(context.getApplicationContext());
        }
        return databaseHelper;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //execute create table query
        db.execSQL(create_student_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //drop all prevous tables
        db.execSQL("DROP TABLE IF EXISTS "+student_table);

        //create new database
        onCreate(db);
    }

    public boolean insertData(String roll_no,String name,String degree,String branch) {
        //open database
        SQLiteDatabase db = this.getWritableDatabase();
        //Insert data
        ContentValues contentValues = new ContentValues();
        contentValues.put(col_1_student_table,roll_no);
        contentValues.put(col_2_student_table,name);
        contentValues.put(col_3_student_table,degree);
        contentValues.put(col_4_student_table,branch);
        long result = db.insert(student_table,null ,contentValues);
        //close database
        db.close();
        if(result == -1)
            return false;
        else
            return true;
    }
    //Cursor is a object that stores the resultset
    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+student_table,null);
        return res;
    }


}

