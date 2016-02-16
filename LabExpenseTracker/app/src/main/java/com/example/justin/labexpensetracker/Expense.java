package com.example.justin.labexpensetracker;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Justin on 2/16/2016.
 */
public class Expense
{
    private String item;
    private double price;
    private Date date;

    public Expense(String item, double price)
    {
        this.item = item;
        this.price = price;
        date = Calendar.getInstance().getTime();
    }

    public String getItem()
    {
        return item;
    }

    public double getPrice()
    {
        return price;
    }

    public Date getDate()
    {
        return date;
    }
}
