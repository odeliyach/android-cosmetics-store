package com.example.myproject;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.shashank.sony.fancytoastlib.FancyToast;


public class AddStock extends AppCompatActivity
{
    EditText itemId,addQuantity;
    Button btnUpdate;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_stock);
        itemId=findViewById(R.id.etId);
        addQuantity=findViewById(R.id.etAdd);
        btnUpdate=findViewById(R.id.btnUpdateStock);
        btnUpdate.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(TextUtils.isEmpty((itemId.getText()).toString()))
                    FancyToast.makeText(AddStock.this,"Please Enter Item Id",FancyToast.LENGTH_LONG,FancyToast.ERROR,true).show();
                else if(TextUtils.isEmpty((addQuantity.getText()).toString()))
                    FancyToast.makeText(AddStock.this,"Please Enter Quantity to add",FancyToast.LENGTH_LONG,FancyToast.ERROR,true).show();
                else
                {
                    int tmp=0;
                    for(ItemsModel itemsModel : NailCare.listItemsNails)
                    {
                      if((String.valueOf(itemId.getText())).equals(itemsModel.getId()))
                      {
                        itemsModel.setQuantity((itemsModel.getQuantity()+Integer.valueOf(addQuantity.getText().toString())));
                        System.out.println(Integer.parseInt(addQuantity.getText().toString()));
                        tmp=1;
                          FancyToast.makeText(AddStock.this,"Stock Updated Successfully",FancyToast.LENGTH_LONG,FancyToast.SUCCESS,true).show();
                      }
                    }
                    if(tmp==1)
                       return;
                    if(tmp==0)
                    {
                        FancyToast.makeText(AddStock.this,"Invalid item id",FancyToast.LENGTH_LONG,FancyToast.ERROR,true).show();
                    }
                }
            }
        });
    }
    //if the back button is pressed move to Admin Login page
    public void onBackPressed()
    {
        Intent intent=new Intent(AddStock.this,AdminLogin.class);
        startActivity(intent);
    }
}
