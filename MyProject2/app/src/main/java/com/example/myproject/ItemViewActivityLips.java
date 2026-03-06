package com.example.myproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class ItemViewActivityLips extends AppCompatActivity
{
    ImageView imageView;
    TextView textView1,textView2,textView3,textView4;
    ItemsModel itemsModel;

    RatingBar ratingBar;
    TextView textView5;
    Button add,addToCart,goToCart;
    DatabaseHelper myDb;
    static ItemsModel i;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_view_lips);

        myDb = new DatabaseHelper(this);

        imageView=findViewById(R.id.imageViewItem);
        textView1=findViewById(R.id.textViewName);
        textView2=findViewById(R.id.descriptionText);
        textView3=findViewById(R.id.priceText);
        textView5 =findViewById(R.id.textView);

        ratingBar =(RatingBar)findViewById(R.id.ratingBar);
        ratingBar.setRating(load());

        //if the rating button is pressed the then save it
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener()
        {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser)
            {
                textView5.setText(""+rating);
                save(rating);
                Toast.makeText(ItemViewActivityLips.this,"Thank you for rating",Toast.LENGTH_LONG).show();
            }
        });
        Intent intent=getIntent();

        itemsModel= (ItemsModel) intent.getSerializableExtra("item");//take the image item
        imageView.setImageResource(itemsModel.getImage());//take the image
        textView1.setText(itemsModel.getName());//take the  name
        textView2.setText(itemsModel.getDesc());//take the company name
        textView3.setText(itemsModel.getPriceInString());//get price

        i=new ItemsModel(itemsModel.getName(),itemsModel.getDesc(),itemsModel.getImage(),itemsModel.getPrice(),itemsModel.getPriceInString());


        //if the add button is pressed the then add the item to the list
        addToCart= (Button)findViewById(R.id.btnaddToCart);
        addToCart.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                addToCart();
            }
        });
        addToCart.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(ItemViewActivityLips.this,MyCart.class).putExtra("item",i));
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
    public void add()//  a function that calls the function isInserted that inserts the data and is in DatabaseHelper class
    {
        boolean isInserted = myDb.insertData(itemsModel.getName(), itemsModel.getDesc(),itemsModel.getPriceInString());
        if(isInserted == true)
            Toast.makeText(ItemViewActivityLips.this,"Data Inserted",Toast.LENGTH_LONG).show();//if the data was inserted
        else
            Toast.makeText(ItemViewActivityLips.this,"Data not Inserted",Toast.LENGTH_LONG).show();//if the data was not inserted
    }
    public void openCart()//function that opens another activity (second screen)
    {
        Intent intent=new Intent(this,MyCart.class);
        startActivity(intent);
    }
    public void addToCart()//  a function that calls the function isInserted that inserts the data and is in DatabaseHelper class
    {
        startActivity(new Intent(ItemViewActivityLips.this,MyCart.class).putExtra("item",i));
        Toast.makeText(ItemViewActivityLips.this,"Item added to cart",Toast.LENGTH_LONG).show();//if the data was inserted
    }


    public  void save(float f)//a function that saves the rating with shared preferences
    {
        SharedPreferences sharedPreferences=getSharedPreferences("folder",MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putFloat("rating",f);
        editor.commit();
    }

    public float load()//a function that loads the rating when the program reloads
    {
        SharedPreferences sharedPreferences=getSharedPreferences("folder",MODE_PRIVATE);
        float f=sharedPreferences.getFloat("rating",0f);
        return  f;
    }

}


