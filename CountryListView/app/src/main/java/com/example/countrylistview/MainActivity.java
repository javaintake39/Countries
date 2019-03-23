package com.example.countrylistview;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import com.example.countrylistview.Fragments.HomeFragment;

public class MainActivity extends AppCompatActivity {

    HomeFragment homeFragment;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManager=getSupportFragmentManager();
        fragmentTransaction=fragmentManager.beginTransaction();

        if(savedInstanceState==null) // first time
        {
            homeFragment=new HomeFragment();
            fragmentTransaction.add(R.id.mainLayout,homeFragment,"homeFragment");
            fragmentTransaction.commit();
        }

    }



}