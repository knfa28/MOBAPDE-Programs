package com.example.kurt.customdatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by Kurt on 3/10/2016.
 */
public class DatabaseOpenHelper extends SQLiteOpenHelper{
    public static final String SCHEMA = "unit";


    public DatabaseOpenHelper(Context context) {
        super(context, SCHEMA, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + SCHEMA + "("
                + Unit.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + Unit.COLUMN_TITLE + " TEXT,"
                + Unit.COLUMN_RATE + " INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public long addUnit(Unit unit){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(Unit.COLUMN_TITLE, unit.getTitle());
        values.put(Unit.COLUMN_RATE, unit.getRate());

        long id = db.insert(SCHEMA, null, values);
        db.close();

        return id;
    }

    public Unit getUnit(int id) {
        Unit unit;
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.query(SCHEMA,
                new String[]{Unit.COLUMN_ID, Unit.COLUMN_TITLE, Unit.COLUMN_RATE},
                " " + Unit.COLUMN_ID + "=?",
                new String[]{String.valueOf(id)},
                null, null, null, null);

        if (cursor.moveToFirst()) {
            unit = new Unit(Integer.parseInt(cursor.getString(0)),
                    cursor.getString(1),
                    Integer.parseInt(cursor.getString(2)));
        } else {
            unit = null;
        }

        db.close();
        cursor.close();

        return unit;
    }

    public ArrayList<Unit> getListUnits() {
        ArrayList<Unit> units = new ArrayList<>();

        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + SCHEMA, null);

        if (cursor.moveToFirst()) {
            do {
                units.add(new Unit(Integer.parseInt(cursor.getString(0)), cursor.getString(1), Integer.parseInt(cursor.getString(2))));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return units;
    }

    public Cursor getCursorUnits() {
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + SCHEMA, null);

        return cursor;
        //do not close db or cursor
    }

    public int deleteUnit(Unit unit) {
        SQLiteDatabase db = getWritableDatabase();

        int rowsAffected = db.delete(SCHEMA,
                Unit.COLUMN_ID + "=?",
                new String[]{String.valueOf(unit.getId())});

        db.close();

        return rowsAffected;
    }

    public int editUnit(Unit unit) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(Unit.COLUMN_TITLE, unit.getTitle());
        values.put(Unit.COLUMN_RATE, unit.getRate());

        int rowsAffected = db.update(SCHEMA,
                values,
                " " + Unit.COLUMN_ID + "=?",
                new String[] { String.valueOf(unit.getId()) });

        db.close();

        return rowsAffected;
    }
}