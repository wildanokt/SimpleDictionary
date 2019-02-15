package com.wildanokt.dictionarymade.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static android.provider.BaseColumns._ID;
import static com.wildanokt.dictionarymade.db.DatabaseContract.DictionaryCollums.CONTENT_EI;
import static com.wildanokt.dictionarymade.db.DatabaseContract.DictionaryCollums.CONTENT_IE;
import static com.wildanokt.dictionarymade.db.DatabaseContract.DictionaryCollums.HEADER_EI;
import static com.wildanokt.dictionarymade.db.DatabaseContract.DictionaryCollums.HEADER_IE;
import static com.wildanokt.dictionarymade.db.DatabaseContract.TABLE_NAME_EI;
import static com.wildanokt.dictionarymade.db.DatabaseContract.TABLE_NAME_IE;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static String DATABASE_NAME = "dbdictionary";
    private static final int DATABASE_VERSION = 1;

    public static String CREATE_TABLE_DICTIONARY_IE =
            "CREATE TABLE " +TABLE_NAME_IE + " ("
                    + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + HEADER_IE + " TEXT NOT NULL,"
                    + CONTENT_IE + " TEXT NOT NULL);";

    public static String CREATE_TABLE_DICTIONARY_EI =
            "CREATE TABLE "+TABLE_NAME_EI + " ("
                    + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + HEADER_EI + " TEXT NOT NULL,"
                    + CONTENT_EI + " TEXT NOT NULL);";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_DICTIONARY_IE);
        db.execSQL(CREATE_TABLE_DICTIONARY_EI);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME_IE);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME_EI);
        onCreate(db);
    }
}
