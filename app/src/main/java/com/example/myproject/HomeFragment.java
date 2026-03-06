package com.example.myproject;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment
{
    View view;
    Button perfume,cosmetics,hair,nailCare;
    static ItemsModel itemsModel;
    public HomeFragment()
    {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_home2, container, false);

        cosmetics=(Button) view.findViewById(R.id.moveToCosmetics);
        cosmetics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getActivity(),Cosmetics.class));
            }
        });

        perfume=(Button) view.findViewById(R.id.moveToPerfumes);
        perfume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),Perfumes.class));
            }
        });
        hair=(Button) view.findViewById(R.id.moveTohair);
        hair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),Hair.class));
            }
        });
        nailCare=(Button) view.findViewById(R.id.moveToNailCare);
        nailCare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),NailCare.class));
            }
        });

        return view;
    }
}
