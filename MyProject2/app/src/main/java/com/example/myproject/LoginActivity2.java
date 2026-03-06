package com.example.myproject;

import android.content.Intent;
import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import com.example.myproject.ui.Navigation;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.ArrayList;

public class LoginActivity2 extends AppCompatActivity
{
    private EditText edtUsername,edtPassword;
    static String username,password;
    static  long id_to_use;
    ArrayList<Account> currentAccount = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        edtUsername = findViewById(R.id.edt_username);
        edtPassword = findViewById(R.id.edt_password);
        Button btnLogin = findViewById(R.id.buttonL);
        TextView tvSighUp=findViewById(R.id.textView);

        // perform error checking and login(start ShoppingActivity)
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                errorCheck();

            }

        });

        tvSighUp.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent i=new Intent(LoginActivity2.this,CreateAccount.class);
                startActivity(i);
            }
        });
    }
    // start shoppingActivity and pass username and password in the intent
    private void launchShoppingActivity()
    {
        Intent intent = new Intent(getApplicationContext(), Navigation.class);
        intent.putExtra("username", edtUsername.getText().toString());
        intent.putExtra("password",edtPassword.getText().toString());
        startActivity(intent);
    }
    // perform error checking and call launchShoppingActivity method
    private void errorCheck()
    {
        username = edtUsername.getText().toString();
        password = edtPassword.getText().toString();

        // set error when username is null
        if (username.length() == 0 || username == null)
        {
            edtUsername.setError("Username cannot be empty");
        }
        // set error when is password is null or less than 5 characters
        if ( password.length() <= 4 || password == null)
        {
            edtPassword.setError("Password has to be more than 5 characters");
        }
        // if it passes all error checking
        else if ( username.length() != 0 &&  password.length() >= 5) {
            AccountDb accountDb = new AccountDb(getApplicationContext());
            currentAccount.clear();
            // get current account from database and add it to currentAccount
            currentAccount = accountDb.getAccounts(username, password);
            // check to see if account already exits
            if (currentAccount.size() != 0) {
                FancyToast.makeText(this, "You are logged in", FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();

                for (Account account : currentAccount) {
                    if (account.getUsername().equals(username))
                    {
                        id_to_use=account.getDbId();

                    }
                }
            }
            // long i=-1;
            //  i=accountDb.getId2(username);
            launchShoppingActivity();
        }
            else
            {
                FancyToast.makeText(this,"Username or password doesn't match the saved account",FancyToast.LENGTH_LONG,FancyToast.ERROR,true).show();
            }
        }
    }

