package com.example.kurt.labdatabase;

/**
 * Created by Kurt on 2/18/2016.
 */
public class Note {
    public static final String TABLE_NAME = "note";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_CONTENT = "content";

    private int id;
    private String title;
    private String content;

    public Note(){}

    public Note(int id, String title, String content) {
        this.id = id;
        this.content = content;
        this.title = title;
    }

    public Note(String title, String content) {
        this.content = content;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
