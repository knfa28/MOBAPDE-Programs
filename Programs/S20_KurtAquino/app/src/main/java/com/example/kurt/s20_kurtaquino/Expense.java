package com.example.kurt.s20_kurtaquino;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Kurt on 2/16/2016.
 */
public class Expense {
    private String name;
    private String price;
    private String expenseDate;

    public Expense(String name,String price){
        this.name = name;
        this.price = price;
        this.expenseDate = new SimpleDateFormat("MM-dd-yyyy").format(new Date());
    }

    public String getName(){
        return name;
    }

    public String getPrice(){
        return price;
    }

    public String getDate(){
        return expenseDate;
    }
}
