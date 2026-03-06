package com.example.myproject;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Perfumes extends AppCompatActivity
{
    ImageView perfumeMan,perfumeWoman;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfumes);

        perfumeMan=findViewById(R.id.perfumeMan);
        perfumeMan.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                man();
            }
        });
        perfumeWoman=findViewById(R.id.perfumeWoman);
        perfumeWoman.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                woman();
            }
        });
    }
    //a function that moves to WomanActivity
    public void woman()
    {
        startActivity(new Intent(Perfumes.this,WomanActivity.class));
    }
    //a function that moves to ManActivity
    public void man() { startActivity(new Intent(Perfumes.this,ManActivity.class)); }
}
