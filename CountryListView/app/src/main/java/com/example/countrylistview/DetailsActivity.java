package com.example.countrylistview;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.countrylistview.Fragments.DetailsFragment;
import com.example.countrylistview.Fragments.HomeFragment;
import com.example.countrylistview.dataProccess.CountryDAO;
import com.example.countrylistview.dataProccess.KeyTags;

public class DetailsActivity extends AppCompatActivity {

    DetailsFragment detailsFragment;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    CountryDAO countryDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        fragmentManager=getSupportFragmentManager();
        fragmentTransaction=fragmentManager.beginTransaction();

        if(savedInstanceState==null) // first time
        {
            detailsFragment=new DetailsFragment();
            fragmentTransaction.add(R.id.detailsLayout,detailsFragment,"detailsFragment");
            fragmentTransaction.commit();
        }

        countryDAO = (CountryDAO) getIntent().getSerializableExtra(KeyTags.objectKey);

        Bundle bundle = new Bundle();

        bundle.putSerializable(KeyTags.objectKey,countryDAO);

        detailsFragment.setArguments(bundle);

    }

}
