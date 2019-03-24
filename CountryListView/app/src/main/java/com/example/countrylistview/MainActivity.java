package com.example.countrylistview;

import android.content.res.Configuration;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;


import com.example.countrylistview.Fragments.Communicator;
import com.example.countrylistview.Fragments.DetailsFragment;
import com.example.countrylistview.Fragments.HomeFragment;
import com.example.countrylistview.dataProccess.CountryDAO;

public class MainActivity extends AppCompatActivity implements Communicator {

    HomeFragment homeFragment;
    DetailsFragment detailsFragment=null;
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
        int orientation = getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) // In landscape
        {
            detailsFragment=(DetailsFragment) fragmentManager.findFragmentByTag("detailsFragment");
            if(detailsFragment==null)
            {
                detailsFragment=new DetailsFragment();
                fragmentTransaction.add(R.id.Layoutdetails,detailsFragment,"detailsFragment");
                fragmentTransaction.commit();
            }
        }
    }
    @Override
    public void sendData(CountryDAO countryDAO) {
        //detailsFragment=(DetailsFragment) fragmentManager.findFragmentByTag("detailsFragment");
        detailsFragment.sendData(countryDAO);
    }
}
