package com.example.myproject;

public class Order
{
    private int orderId,totalPrice,numberOfItems;
    private String costomerName,adress;
    private boolean status;
    //Constructor
    public Order(String costomerName, String adress,int totalPrice,int orderId,int numberOfItems,boolean status)
    {
        this.costomerName=costomerName;
        this.adress=adress;
        this.totalPrice=totalPrice;
        this.orderId=orderId;
        this.numberOfItems=numberOfItems;
        this.status=status;
    }
    //Get and Set functions
    public boolean getStatus()
    {
        return status;
    }
    public void setStatus(boolean status)
    {
        this.status = status;
    }
    public String getCostomerName()
    {
        return costomerName;
    }
    public void setCostomerName(String costomerName)
    {
        this.costomerName = costomerName;
    }
    public String getAdress()
    {
        return adress;
    }
    public void setAdress(String adress)
    {
        this.adress = adress;
    }
    public int getOrderId()
    {
        return orderId;
    }
    public void setOrderId(int orderId)
    {
        this.orderId = orderId;
    }
    public int getTotalPrice()
    {
        return totalPrice;
    }
    public void setTotalPrice(int totalPrice)
    {
        this.totalPrice = totalPrice;
    }
    public int getNumberOfItems()
    {
        return numberOfItems;
    }
    public void setNumberOfItems(int numberOfItems)
    {
        this.numberOfItems = numberOfItems;
    }
}
