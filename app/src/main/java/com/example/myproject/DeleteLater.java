package com.example.myproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.ArrayList;
import java.util.List;

public class DeleteLater extends AppCompatActivity
{
    Button btnviewAll,btnAddData,move;
    ShoppingCartDb myDb;
    static List<ItemsModel> listItemsCart=new ArrayList<>();
    static ItemsModel itemsModel;
    static int totalPrice=0,itemCount=0;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_later);
        myDb= new ShoppingCartDb(getBaseContext());


       /* btnAddData = (Button)findViewById(R.id.example);//buttun to add
        btnAddData.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                add(v);
            }
        });
        btnviewAll = (Button) findViewById(R.id.example2);
        btnviewAll.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                all(v);
            }
        });
        move = (Button) findViewById(R.id.move);
        move.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getApplicationContext(), Move.class);
                startActivity(intent);
            }
        });*/
    }
    // a function that adds the data to the table
   /* public void add(View view)
    {
        boolean isInserted = myDb.insertDataShopping("a","b","c",2,R.drawable.armani);
        if(isInserted == true)
            //if the data was inserted
            FancyToast.makeText(this,"Data Inserted",FancyToast.LENGTH_LONG,FancyToast.SUCCESS,true).show();
        else
            //if the data was not inserted
            FancyToast.makeText(this,"Data not Inserted",FancyToast.LENGTH_LONG,FancyToast.ERROR,true).show();

        /*editName.setText("");
        editSurname.setText("");
        editMarks.setText("");
        editTextId.setText("");*/
    //}
    //a function that shows the user all the products that were saved
    /*public void all(View view)
    {
        Cursor res = myDb.getAllData3();
        if(res.getCount() == 0)
        {
            // show message error if no data was inserted
            showMessage("Sorry,there are no perfumes in the list ","Nothing found");//if the list ia empty show error message
            return;
        }
        StringBuffer buffer = new StringBuffer();
        while (res.moveToNext())
        {
            buffer.append("Id :"+ res.getString(0)+"\n");//add id to column  1
            buffer.append("Product Name :"+ res.getString(1)+"\n");//add name to column  2
            buffer.append("Product Description :"+ res.getString(2)+"\n");//add company name to column  3
            buffer.append("Price :"+ res.getString(3)+"\n\n");//add price to column  4
            itemsModel=new ItemsModel(res.getString(1)+"\n",res.getString(2)+"\n",res.getInt(5),res.getInt(4),res.getString(3)+"\n");

           long i= LoginActivity2.id_to_use;
            if(res.getLong(0)==(LoginActivity2.id_to_use))
            {
                listItemsCart.add(itemsModel);
                totalPrice += itemsModel.getPrice();
                itemCount++;
            }
        }
        // Show all data
        showMessage("Perfume List",buffer.toString());
    }*/

    public void showMessage(String title,String Message)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
}
