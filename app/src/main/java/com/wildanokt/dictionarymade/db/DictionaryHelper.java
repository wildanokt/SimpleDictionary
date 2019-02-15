package com.wildanokt.dictionarymade.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import com.wildanokt.dictionarymade.model.EIDictionaryModel;
import com.wildanokt.dictionarymade.model.IEDictionaryModel;

import java.util.ArrayList;

import static android.provider.BaseColumns._ID;
import static com.wildanokt.dictionarymade.db.DatabaseContract.DictionaryCollums.CONTENT_EI;
import static com.wildanokt.dictionarymade.db.DatabaseContract.DictionaryCollums.CONTENT_IE;
import static com.wildanokt.dictionarymade.db.DatabaseContract.DictionaryCollums.HEADER_EI;
import static com.wildanokt.dictionarymade.db.DatabaseContract.DictionaryCollums.HEADER_IE;
import static com.wildanokt.dictionarymade.db.DatabaseContract.TABLE_NAME_EI;
import static com.wildanokt.dictionarymade.db.DatabaseContract.TABLE_NAME_IE;

public class DictionaryHelper {

    private Context context;
    private DatabaseHelper helper;
    private SQLiteDatabase database;

    public DictionaryHelper(Context context) {
        this.context = context;
    }

    public DictionaryHelper open() throws SQLException {
        helper = new DatabaseHelper(context);
        database = helper.getWritableDatabase();
        return this;
    }
    public void close(){
        helper.close();
    }

    public ArrayList<IEDictionaryModel> getDataIE(String key){
        Cursor cursor = database.query(
                TABLE_NAME_IE,
                null,
                HEADER_IE+" LIKE '"+key+"%'",
                null,
                null,
                null,
                _ID+" ASC",
                null
        );
        cursor.moveToFirst();
        ArrayList<IEDictionaryModel> arrayList = new ArrayList<>();

        IEDictionaryModel model;

        if (cursor.getCount()>0){
            do {
                model = new IEDictionaryModel();
                model.setId(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
                model.setHeader(cursor.getString(cursor.getColumnIndexOrThrow(HEADER_IE)));
                model.setContent(cursor.getString(cursor.getColumnIndexOrThrow(CONTENT_IE)));

                arrayList.add(model);
                cursor.moveToNext();
            }while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }
    public ArrayList<EIDictionaryModel> getDataEI(String key){
        Cursor cursor = database.query(
                TABLE_NAME_EI,
                null,
                HEADER_EI+" LIKE '"+key+"%'",
                null,
                null,
                null,
                _ID+" ASC",
                null
        );
        cursor.moveToFirst();
        ArrayList<EIDictionaryModel> arrayList = new ArrayList<>();

        EIDictionaryModel model;

        if (cursor.getCount()>0){
            do {
                model = new EIDictionaryModel();
                model.setId(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
                model.setHeader(cursor.getString(cursor.getColumnIndexOrThrow(HEADER_EI)));
                model.setContent(cursor.getString(cursor.getColumnIndexOrThrow(CONTENT_EI)));

                arrayList.add(model);
                cursor.moveToNext();
            }while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }

    public ArrayList<IEDictionaryModel> getAllDataIE(){
        Cursor cursor = database.query(
                TABLE_NAME_IE,
                null,
                null,
                null,
                null,
                null,
                _ID+" ASC",
                null
        );
        cursor.moveToFirst();

        ArrayList<IEDictionaryModel> arrayList = new ArrayList<>();
        IEDictionaryModel model;
        if (cursor.getCount()>0){
            do {
                model = new IEDictionaryModel();
                model.setId(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
                model.setHeader(cursor.getString(cursor.getColumnIndexOrThrow(HEADER_IE)));
                model.setContent(cursor.getString(cursor.getColumnIndexOrThrow(CONTENT_IE)));

                arrayList.add(model);
                cursor.moveToNext();
            }while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }
    public ArrayList<EIDictionaryModel> getAllDataEI(){
        Cursor cursor = database.query(
                TABLE_NAME_EI,
                null,
                null,
                null,
                null,
                null,
                _ID+" ASC",
                null
        );
        cursor.moveToFirst();

        ArrayList<EIDictionaryModel> arrayList = new ArrayList<>();
        EIDictionaryModel model;
        if (cursor.getCount()>0){
            do {
                model = new EIDictionaryModel();
                model.setId(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
                model.setHeader(cursor.getString(cursor.getColumnIndexOrThrow(HEADER_EI)));
                model.setContent(cursor.getString(cursor.getColumnIndexOrThrow(CONTENT_EI)));

                arrayList.add(model);
                cursor.moveToNext();
            }while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }

    //transaction
    public void beginTransaction(){
        database.beginTransaction();
    }
    public void endTransaction(){
        database.endTransaction();
    }
    public void setTransactionSuccess(){
        database.setTransactionSuccessful();
    }

    public void insertTransactionIE(IEDictionaryModel model){
        String sql = "INSERT INTO "+TABLE_NAME_IE+" ("+HEADER_IE+", "+CONTENT_IE+") VALUES(?,?)";
        SQLiteStatement statement = database.compileStatement(sql);
        statement.bindString(1, model.getHeader());
        statement.bindString(2, model.getContent());
        statement.execute();
        statement.clearBindings();
    }
    public void insertTransactionEI(EIDictionaryModel model){
        String sql = "INSERT INTO "+TABLE_NAME_EI+" ("+HEADER_EI+", "+CONTENT_EI+") VALUES(?,?)";
        SQLiteStatement statement = database.compileStatement(sql);
        statement.bindString(1, model.getHeader());
        statement.bindString(2, model.getContent());
        statement.execute();
        statement.clearBindings();
    }
}
