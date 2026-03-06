package com.example.myproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginPage extends AppCompatActivity
{
    EditText emailID,password;
    Button btnSighIn;
    TextView tvSighUp;
    FirebaseAuth mFirebaseAuth;
    FirebaseAuth.AuthStateListener mAuthStateListener;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        mFirebaseAuth=FirebaseAuth.getInstance();
        emailID=findViewById(R.id.editText);
        password=findViewById(R.id.editText2);
        tvSighUp=findViewById(R.id.textView);
        btnSighIn=findViewById(R.id.buttonL);

       mAuthStateListener=new FirebaseAuth.AuthStateListener()
        {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth)
            {
                FirebaseUser mFirebaseUser=mFirebaseAuth.getCurrentUser();
                if(mFirebaseUser!=null)
                {
                    /*Toast.makeText(LoginPage.this, "You are loggedin",Toast.LENGTH_SHORT).show();
                    Intent i=new Intent(LoginPage.this,HomePage3.class);
                    startActivity(i);*/
                }
                else
                {
                    Toast.makeText(LoginPage.this, "Please log in",Toast.LENGTH_SHORT).show();
                }
            }
        };
        btnSighIn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String email=emailID.getText().toString();
                String pwd=password.getText().toString();

                if(email.isEmpty())
                {
                    emailID.setError("Please enter email id");
                    emailID.requestFocus();
                }
                else if(pwd.isEmpty())
                {
                    password.setError("Please enter your password");
                    password.requestFocus();
                }
                else if(pwd.isEmpty() && email.isEmpty() )
                {
                    Toast.makeText(LoginPage.this, "Fields are empty",Toast.LENGTH_SHORT).show();
                }
                else if(!(pwd.isEmpty() && email.isEmpty()) )
                {
                    mFirebaseAuth.signInWithEmailAndPassword(email,pwd).addOnCompleteListener(LoginPage.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task)
                        {
                            if (!task.isSuccessful())
                            {
                                Toast.makeText(LoginPage.this, "Login Error,Please login again",Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                //startActivity(new Intent(LoginPage.this,HomePage3.class));
                            }
                        }
                    });
                }
                else
                {
                    Toast.makeText(LoginPage.this, "Error Occurred",Toast.LENGTH_SHORT).show();
                }
            }
        });
        tvSighUp.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent i=new Intent(LoginPage.this,RegisterPage.class);
                startActivity(i);
            }
        });
    }

    @Override
    protected void onStart()
    {
        super.onStart();
        mFirebaseAuth.addAuthStateListener(mAuthStateListener);
    }
}
