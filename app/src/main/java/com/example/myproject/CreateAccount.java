package com.example.myproject;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.ArrayList;
public class CreateAccount extends AppCompatActivity
{
    private EditText edtUsername,edtConfirmPassword,edtPassword;
    public String password,username;
    private String confirmPassword;
    ArrayList<Account> currentAccount = new ArrayList<>();
    String priceRange,clothesCategory,cardType,cardNumber,expiryDate,cvcCode;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        edtUsername = findViewById(R.id.edt_username);
        edtPassword = findViewById(R.id.edt_password);
        edtConfirmPassword = findViewById(R.id.edt_confirm_password);
        Button btnCreate = findViewById(R.id.btn_create);

        // create button saves new instances of account
        btnCreate.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                save();

            }
        });
        TextView tvSighIn=findViewById(R.id.textView);
        tvSighIn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent i=new Intent(CreateAccount.this,LoginActivity2.class);
                startActivity(i);
            }
        });
    }
    // method to create an instance of account from what user entered
    private Account loadAccount()
    {
        username = edtUsername.getText().toString();
        password = edtPassword.getText().toString();
        priceRange = "";
        clothesCategory = "";
        cardType = "";
        cardNumber = "";
        expiryDate = "";
        cvcCode = "";
        return new Account(username, password, priceRange, clothesCategory, cardType, cardNumber, expiryDate, cvcCode);
    }
    private void save()
    {
        // load account from UI
        loadAccount();
        confirmPassword = edtConfirmPassword.getText().toString();

        // performs error checking
        // set error when username is null
        if (username.length() == 0 || username == null)
        {
            edtUsername.setError("Username cannot be empty");
        }
        // set error when password is null or less or equal to 4 characters long
        if (password.length() <= 4 || password == null)
        {
            edtPassword.setError("Password has to be at least 5 characters");
        }
        //set error when confirm password is null
        if (confirmPassword.length() == 0 || confirmPassword == null)
        {
            edtConfirmPassword.setError("Confirm password cannot be empty");
            // performs all other checks including password must equal confirm password
        }
        else if (password.equals(confirmPassword) && username.length() != 0 && password.length() >= 5)
        {
            // create instance of account
            Account account = loadAccount();

            currentAccount.clear();

            AccountDb accountDb = new AccountDb(getApplicationContext());

            // get current account from database and add it to currentAccount
            currentAccount = accountDb.getAccounts(username, password);

            // check to see if account already exits
            // Save new account only if account does not exist in database
            if (currentAccount.size() != 0)
            {
                FancyToast.makeText(this,"Account already exists ",FancyToast.LENGTH_LONG,FancyToast.SUCCESS,true).show();
            }
            else
             {
                accountDb.saveAccount(account);
                 FancyToast.makeText(this,"Account: " + username + " created!",FancyToast.LENGTH_LONG,FancyToast.SUCCESS,true).show();
             }
        }
        //set error when password doesn't equal confirm password
        else if (password.equals(confirmPassword) == false)
        {
            edtConfirmPassword.setError("Password and confirm password doesn't match");
        }
    }
}