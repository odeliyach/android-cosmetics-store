package com.example.myproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.myproject.ui.Navigation;

public class RegLogChoice extends AppCompatActivity
{
    Button reg,log;
    TextView admlog;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_log_choice);
        reg=(Button)findViewById(R.id.btnreg);
        log=(Button)findViewById(R.id.btnlog);
        admlog=(TextView)findViewById(R.id.admlog);
        log.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(RegLogChoice.this, LoginActivity2.class);
                startActivity(intent);
            }
        });
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(RegLogChoice.this,CreateAccount.class);
                startActivity(intent);
            }
        });
        admlog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegLogChoice.this,AdminLogin.class));
            }
        });
    }
}