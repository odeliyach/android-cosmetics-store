package com.example.myproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MyOrders extends AppCompatActivity
{
    static List<Order> listOrders=new ArrayList<>();
    static ListView listViewOrders;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_orders);

        /*listView=findViewById(R.id.orderList);

        listOrders.add(itemsModel);*/
    }
}
