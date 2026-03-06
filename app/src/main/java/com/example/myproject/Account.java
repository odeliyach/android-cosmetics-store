package com.example.myproject;

public class Account
{
    private String username,password;
    private long dbId;
    // constructor that will include all account field variables including long dbID
    //used in accountDb class
    public Account(String username, String password, long dbId)
    {
        this.username = username;
        this.password = password;
        this.dbId = dbId;

    }
    // constructor for creating instances of account. Used in createAccount activity
    public Account (String username, String password,  String priceRange, String clothesCategory, String cardType, String cardNumber, String expiryDate, String cvcCode)
    {
        this.username = username;
        this.password = password;
    }
    //getters and setters for all account field variable
    public String getUsername(){ return username; }
    public String getPassword(){ return password; }
    public void setDbId(long dbId) { this.dbId = dbId;}
    public long getDbId() { return dbId; }
    // 'toString' to return textual representation of an object
    @Override
    public String toString()
    {
        return(username + password);
    }
}
