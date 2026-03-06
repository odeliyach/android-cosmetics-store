package com.example.myproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.ArrayList;
import java.util.List;

public class ItemViewActivity extends AppCompatActivity
{
    ImageView imageView;
    TextView textView1,textView2,textView3,textView5;
    ItemsModel itemsModel;
    RatingBar ratingBar;
    Button add,addToCart;
    DatabaseHelper myDb;
    static ItemsModel i;
    static String s;
    static boolean r;
    ShoppingCartDb myDb2;
    static List<ItemsModel> listItemsCart=new ArrayList<>();
    static int totalPrice=0,itemCount=0,e;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_view);

        myDb = new DatabaseHelper(this);
        myDb2= new ShoppingCartDb(getBaseContext());

        imageView=findViewById(R.id.imageViewItem);
        textView1=findViewById(R.id.textViewName);
        textView2=findViewById(R.id.descriptionText);
        textView3=findViewById(R.id.priceText);
        textView5 =findViewById(R.id.textView);

        Intent intent=getIntent();
        itemsModel= (ItemsModel) intent.getSerializableExtra("item");
        imageView.setImageResource(itemsModel.getImage());//take the image
        textView1.setText(itemsModel.getName());//take the  name
        textView2.setText(itemsModel.getDesc());//take the company name
        textView3.setText(itemsModel.getPriceInString());//get price

        e=itemsModel.getRating();
        s=itemsModel.getName();

        ratingBar =(RatingBar)findViewById(R.id.ratingBar);
        ratingBar.setRating(e);
        textView5.setText(""+e);

        i=new ItemsModel(itemsModel.getName(),itemsModel.getDesc(),itemsModel.getImage(),itemsModel.getPrice(),itemsModel.getPriceInString());

         //if the rating button is pressed the then save it
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener()
        {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser)
            {
                itemsModel.setQRating((int)rating);
                e=(int)rating;
                r=true;
                save(itemsModel.getRating());
                ratingBar.setRating(load());
                textView5.setText(""+load());
                Toast.makeText(ItemViewActivity.this,"Thank you for rating",Toast.LENGTH_LONG).show();
            }
        });
        //if the add button is pressed the then add the item to the list
        addToCart= (Button)findViewById(R.id.btnaddToCart);
        addToCart.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                add(v);
                all2(v);
                addToCart();
            }
        });
        add= (Button)findViewById(R.id.btadd);
        add.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                add();
            }
        });
    }
    //a function that shows the user all the products that were saved
    public void all2(View view)
    {
        Cursor res = myDb2.getAllData3();
        if(res.getCount() == 0)
        {
            return;
        }
        while (res.moveToNext())
        {
            itemsModel=new ItemsModel(res.getString(1),res.getString(2),res.getInt(5),res.getInt(4),res.getString(3));

            long i= LoginActivity2.id_to_use;
            if(res.getLong(0)==(LoginActivity2.id_to_use))
            {
                listItemsCart.add(itemsModel);
                totalPrice += itemsModel.getPrice();
                itemCount++;
            }
        }
    }
    // a function that adds the data to the table
    public void add(View view)
    {
        boolean isInserted = myDb2.insertDataShopping(itemsModel.getName(),itemsModel.getDesc(),itemsModel.getPriceInString(),itemsModel.getPrice(),itemsModel.getImage());
        if(isInserted == true)
            //if the data was inserted
        {
            FancyToast.makeText(this, "Data Inserted", FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();
        }
        else
            //if the data was not inserted
            FancyToast.makeText(this,"Data not Inserted",FancyToast.LENGTH_LONG,FancyToast.ERROR,true).show();
    }
    //a function that calls the function isInserted that inserts the data and is in DatabaseHelper class
    public void add()
    {
        boolean isInserted = myDb.insertData(itemsModel.getName(), itemsModel.getDesc(),itemsModel.getPriceInString());
        if(isInserted == true)
            Toast.makeText(ItemViewActivity.this,"Data Inserted",Toast.LENGTH_LONG).show();//if the data was inserted
        else
            Toast.makeText(ItemViewActivity.this,"Data not Inserted",Toast.LENGTH_LONG).show();//if the data was not inserted
    }
    //a function that adds the item to the cart and puts it in a intent and moves to MyCart activity
    public void addToCart()
    {
        startActivity(new Intent(ItemViewActivity.this,MyCart.class).putExtra("item",i));
        Toast.makeText(ItemViewActivity.this,"Item added to cart",Toast.LENGTH_LONG).show();//if the data was inserted
    }
    //a function that saves the rating with shared preferences
    public void save(float f)
    {
        SharedPreferences sharedPreferences=getSharedPreferences(s,MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putFloat("rating",f);
        editor.commit();
    }
    //a function that loads the rating when the program reloads
    public float load()
    {
        SharedPreferences sharedPreferences=getSharedPreferences(s,MODE_PRIVATE);
        float f=sharedPreferences.getFloat("rating",0f);
        return  f;
    }
}