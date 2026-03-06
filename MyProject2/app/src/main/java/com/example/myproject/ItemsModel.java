package com.example.myproject;

import java.io.Serializable;

public class ItemsModel implements Serializable
{
    private String name,priceInString,desc,id;
    private int image,price,quantity,rating;
    //constructor
    public ItemsModel(String name, String desc, int image,int price,String priceInString)
    {
        this.name = name;
        this.desc = desc;
        this.image = image;
        this.price=price;
        this.priceInString=priceInString;
    }
    //constructor
    public ItemsModel(String name, String desc, int image,int price,String priceInString,int rating)
    {
        this.name = name;
        this.desc = desc;
        this.image = image;
        this.price=price;
        this.priceInString=priceInString;
        this.rating=rating;
    }
    //constructor
    public ItemsModel(String name, String desc, int image,int price,String priceInString,String id,int quantity,int rating)
    {
        this.name = name;
        this.desc = desc;
        this.image = image;
        this.price=price;
        this.priceInString=priceInString;
        this.id=id;
        this.quantity=quantity;
        this.rating=rating;
    }
    //constructor
    public ItemsModel(String name, String desc, int price,String priceInString)
    {
        this.name = name;
        this.desc = desc;
        this.price=price;
        this.priceInString=priceInString;
    }
    //Get and Set functions
    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name = name;
    }
    public String getPriceInString()
    {
        return priceInString;
    }
    public String getDesc()
    {
        return desc;
    }
    public int getImage()
    {
        return image;
    }
    public void setImage(int image)
    {
        this.image = image;
    }
    public int getPrice()
    {
        return price;
    }
    public void setPrice(int price)
    {
        this.price = price;
    }
    public String getId()
    {
        return id;
    }
    public void setId(String id)
    {
        this.id = id;
    }
    public int getQuantity()
    {
        return quantity;
    }
    public void setQuantity(int quantity)
    {
        this.quantity = quantity;
    }
    public int getRating()
    {
        return rating;
    }
    public void setQRating(int rating)
    {
        this.rating = rating;
    }
}

