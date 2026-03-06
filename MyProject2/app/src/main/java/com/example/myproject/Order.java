package com.example.myproject;

public class Order
{
    private int totalPrice,numberOfItems;
    private String costomerName;
    //Constructor
    public Order(String costomerName,int totalPrice,int numberOfItems)
    {
        this.costomerName=costomerName;
        this.totalPrice=totalPrice;
        this.numberOfItems=numberOfItems;
    }
    //Get and Set functions
    public String getCostomerName() { return costomerName; }
    public int getTotalPrice()
    {
        return totalPrice;
    }
    public int getNumberOfItems()
    {
        return numberOfItems;
    }
}
