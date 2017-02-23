package com.example.kurt.customdatabase;

/**
 * Created by Kurt on 3/10/2016.
 */
public class Unit {
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_RATE = "rate";

    private int id;
    private String title;
    private int rate;

    public Unit() {
    }

    public Unit(int id, String title, int rate) {
        this.id = id;
        this.title = title;
        this.rate = rate;
    }

    public Unit(String title, int rate) {
        this.title = title;
        this.rate = rate;
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

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }
}

