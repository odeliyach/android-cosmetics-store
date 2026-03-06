package com.example.myproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterPage extends AppCompatActivity
{
    EditText emailID,password;
    Button btnSighUp;
    TextView tvSighIn;
    FirebaseAuth mFirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);

        mFirebaseAuth=FirebaseAuth.getInstance();
        emailID=findViewById(R.id.editTextR);
        password=findViewById(R.id.editText2R);
        tvSighIn=findViewById(R.id.textView);
        btnSighUp=findViewById(R.id.button1);
        btnSighUp.setOnClickListener(new View.OnClickListener()
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
                    Toast.makeText(RegisterPage.this, "Fields are empty",Toast.LENGTH_SHORT).show();
                }
                else if(!(pwd.isEmpty() && email.isEmpty()) )
                {
                    mFirebaseAuth.createUserWithEmailAndPassword(email,pwd).addOnCompleteListener(RegisterPage.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task)
                        {
                            if (!task.isSuccessful())
                            {
                                Toast.makeText(RegisterPage.this, "Sign Up is unsuccessful, please try again",Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                               // startActivity(new Intent(RegisterPage.this,HomePage3.class));
                            }
                        }
                    });
                }
                else
                {
                    Toast.makeText(RegisterPage.this, "Error Occurred",Toast.LENGTH_SHORT).show();
                }
            }
        });

        tvSighIn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent i=new Intent(RegisterPage.this,LoginPage.class);
                startActivity(i);
            }
        });
    }
}
