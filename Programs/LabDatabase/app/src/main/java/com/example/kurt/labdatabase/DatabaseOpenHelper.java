package com.example.kurt.labdatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.SQLClientInfoException;
import java.util.ArrayList;
import java.util.List;

public class DatabaseOpenHelper extends SQLiteOpenHelper{
    public static final String SCHEMA = "note";


    public DatabaseOpenHelper(Context context) {
        super(context, SCHEMA, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + Note.TABLE_NAME + "("
                + Note.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + Note.COLUMN_TITLE + " TEXT,"
                + Note.COLUMN_CONTENT + " TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public long addNote(Note note){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(Note.COLUMN_TITLE, note.getTitle());
        values.put(Note.COLUMN_CONTENT, note.getContent());

        long id = db.insert(Note.TABLE_NAME, null, values);
        db.close();

        return id;
    }

    public Note getNote(int id) {
        Note note;
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.query(Note.TABLE_NAME,
                new String[]{Note.COLUMN_ID, Note.COLUMN_TITLE, Note.COLUMN_CONTENT},
                " " + Note.COLUMN_ID + "=?",
                new String[]{String.valueOf(id)},
                null, null, null, null);

        if (cursor.moveToFirst()) {
            note = new Note(Integer.parseInt(cursor.getString(0)),
                    cursor.getString(1),
                    cursor.getString(2));
        } else {
            note = null;
        }

        db.close();
        cursor.close();

        return note;
    }

    public ArrayList<Note> getListNotes() {
        ArrayList<Note> notes = new ArrayList<>();

        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + Note.TABLE_NAME, null);

        if (cursor.moveToFirst()) {
            do {
                notes.add(new Note(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2)));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return notes;
    }

    public Cursor getCursorNotes() {
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + Note.TABLE_NAME, null);

        return cursor;
        //do not close db or cursor
    }

    public int deleteNote(Note note) {
        SQLiteDatabase db = getWritableDatabase();

        int rowsAffected = db.delete(Note.TABLE_NAME,
                Note.COLUMN_ID + "=?",
                new String[]{String.valueOf(note.getId())});

        db.close();

        return rowsAffected;
    }

    public int editNote(Note note) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(Note.COLUMN_TITLE, note.getTitle());
        values.put(Note.COLUMN_CONTENT, note.getContent());

        int rowsAffected = db.update(Note.TABLE_NAME,
                values,
                " " + Note.COLUMN_ID + "=?",
                new String[] { String.valueOf(note.getId()) });

        db.close();

        return rowsAffected;
    }
}
