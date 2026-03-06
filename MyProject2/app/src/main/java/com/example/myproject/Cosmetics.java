package com.example.myproject;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class Cosmetics extends AppCompatActivity
{
    Button eyes,lips,face;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cosmetics);

        eyes=findViewById(R.id.moveToEyes);
        eyes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Cosmetics.this,EyesP.class));
            }
        });
        lips=findViewById(R.id.Lips);
        lips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Cosmetics.this,Lips.class));
            }
        });
        face=findViewById(R.id.Face);
        face.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Cosmetics.this,Face.class));
            }
        });
    }
}