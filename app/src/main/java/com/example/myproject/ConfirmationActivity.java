package com.example.myproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ConfirmationActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);

        Button btnLogout = findViewById(R.id.btn_logout);
        // logout button launches back to MainActivity
        btnLogout.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                launchMainActivity();

            }
        });
    }
    // method starting MainActivity class
    private void launchMainActivity()
    {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }
    // starting MainActivity when logout is clicked from the menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        return super.onOptionsItemSelected(item);
    }
}