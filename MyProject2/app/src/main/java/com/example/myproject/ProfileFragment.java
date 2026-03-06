package com.example.myproject;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment
{


    public ProfileFragment()
    {
        // Required empty public constructor
    }

    private TextView occupationTxtView, nameTxtView, workTxtView;
    private ImageView userImageView, emailImageView, phoneImageView, videoImageView;
    private ImageView facebookImageView, twitterImageView;
    private final String TAG = this.getClass().getName().toUpperCase();
    private FirebaseDatabase database;
    private DatabaseReference mDatabase;
    private Map<String, String> userMap;
    private String email;
    private String userid;
    private static final String USERS = "users";

    TextView payment_amount,name;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view=inflater.inflate(R.layout.fragment_profile, container, false);
       // itemCountTextView = (TextView) view.findViewById(R.id.item_count);

        payment_amount=(TextView) view.findViewById(R.id.payment_label);
        payment_amount.setText(String.valueOf(MyCart.totalPrice));

        name=(TextView) view.findViewById(R.id.fullname_field);
        name.setText(LoginActivity2.username);


        // Inflate the layout for this fragment
        return view;
    }

}
