package com.example.myproject;


import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.shashank.sony.fancytoastlib.FancyToast;


/**
 * A simple {@link Fragment} subclass.
 */
public class FavoritesFragment extends Fragment
{
    public FavoritesFragment()
    {
        // Required empty public constructor
    }

    //Activity for my perfumes list
    DatabaseHelper myDb;
    EditText editName,editSurname,editMarks ,editTextId;
    Button btnAddData;
    Button btnviewAll;
    Button btnDelete;
    Button btnviewUpdate;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_favorites, container, false);
        myDb = new DatabaseHelper(getContext());

        editName = (EditText)view.findViewById(R.id.etName);
        editSurname = (EditText)view.findViewById(R.id.etSurname);
        editMarks = (EditText)view.findViewById(R.id.etMarks);
        editTextId = (EditText)view.findViewById(R.id.etID);
        btnAddData = (Button)view.findViewById(R.id.btadd);//buttun to add
        btnAddData.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                add(v);
            }
        });
        btnviewAll = (Button)view.findViewById(R.id.btview2);
        btnviewAll.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                all(v);
            }
        });
        btnviewUpdate= (Button)view.findViewById(R.id.btupdate);
        btnviewUpdate.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                update(v);
            }
        });
        btnDelete= (Button)view.findViewById(R.id.btdelete);
        btnDelete.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                delete(v);
            }
        });
        return view;
    }
    // a function that adds the data to the table
    public void add(View view)
    {
        boolean isInserted = myDb.insertData(editName.getText().toString(),
                editSurname.getText().toString(),
                editMarks.getText().toString() );
        if(isInserted == true)
            //if the data was inserted
            FancyToast.makeText(getContext(),"Data Inserted",FancyToast.LENGTH_LONG,FancyToast.SUCCESS,true).show();
        else
            //if the data was not inserted
            FancyToast.makeText(getContext(),"Data not Inserted",FancyToast.LENGTH_LONG,FancyToast.ERROR,true).show();

        editName.setText("");
        editSurname.setText("");
        editMarks.setText("");
        editTextId.setText("");
    }
    //a function that shows the user all the products that were saved
    public void all(View view)
    {
        Cursor res = myDb.getAllData();
        if(res.getCount() == 0)
        {
            // show message error if no data was inserted
            showMessage("Sorry,there are no perfumes in the list ","Nothing found");//if the list ia empty show error message
            return;
        }
        StringBuffer buffer = new StringBuffer();
        while (res.moveToNext())
        {
            buffer.append("Id :"+ res.getString(0)+"\n");//add id to column  1
            buffer.append("Product Name :"+ res.getString(1)+"\n");//add name to column  2
            buffer.append("Product Description :"+ res.getString(2)+"\n");//add company name to column  3
            buffer.append("Price :"+ res.getString(3)+"\n\n");//add price to column  4
        }
        // Show all data
        showMessage("Perfume List",buffer.toString());
    }

    public void showMessage(String title,String Message)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

    public void update(View view)
    {
        boolean isUpdate = myDb.updateData(editTextId.getText().toString(), editName.getText().toString(),editSurname.getText().toString(),editMarks.getText().toString());
        if(isUpdate == true)
            FancyToast.makeText(getContext(),"Data  Updated",FancyToast.LENGTH_LONG,FancyToast.SUCCESS,true).show();
        else
            FancyToast.makeText(getContext(),"Data not Updated",FancyToast.LENGTH_LONG,FancyToast.ERROR,true).show();
    }

    public void delete(View view)
    {
        myDb.deleteData(editTextId.getText().toString());
        Toast.makeText(getContext(),"Data Deleted",Toast.LENGTH_LONG).show();
    }
}