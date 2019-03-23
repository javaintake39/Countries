package com.example.countrylistview.Fragments;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.countrylistview.DetailsActivity;
import com.example.countrylistview.R;
import com.example.countrylistview.dataProccess.CountryDAO;
import com.example.countrylistview.dataProccess.JsonParser;
import com.example.countrylistview.dataProccess.KeyTags;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class HomeFragment extends Fragment
{
    private final static String api = "https://www.androidbegin.com/tutorial/jsonparsetutorial.txt";
    JsonParser parser = new JsonParser();
    List<CountryDAO> arrayList;
    String [ ] Countries=new String[10];
    ListView listview;
    ArrayAdapter<String> adapter;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view=inflater.inflate(R.layout.fragment_home, container, false);
        try {
            GetJson getJson = new GetJson(); // this class GetJson exists at the end of this class
            getJson.start();
            getJson.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        listview = (ListView) view.findViewById(R.id.listCountries);
        adapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), android.R.layout.simple_list_item_1, android.R.id.text1, Countries);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(getActivity().getApplicationContext(), position+" Clicked  " +Countries[position], Toast.LENGTH_SHORT).show();
                moveToNewActivity(position);
            }
        });
        return view;
    }

    private class GetJson extends Thread {
        @Override
        public void run()
        {
            String JsonObjectAsString=getJsonObjectFromURL(api);
            arrayList = parser.JsonProcess(JsonObjectAsString);
            for (int i=0;i<arrayList.size();i++)
            {
                Countries[i]=arrayList.get(i).getCountry();
            }
        }
    }

    public String getJsonObjectFromURL(String urlLink)
    {
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String data="";
        try {
            StringBuffer buffer = new StringBuffer();
            URL url = new URL(urlLink);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.connect();
            InputStream inputStream = new BufferedInputStream(urlConnection.getInputStream());
            reader = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";

            while ((line = reader.readLine()) != null) {

                buffer.append(line + "\n");
            }
            data = buffer.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;

    }
    private boolean checkInternetConnection()
    {
        ConnectivityManager cm = (ConnectivityManager) getActivity().getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (null != activeNetwork)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    private void moveToNewActivity(int position)
    {
        Intent intent = new Intent(getActivity(), DetailsActivity.class);

        intent.putExtra(KeyTags.objectKey,arrayList.get(position));

        startActivity(intent);
        ((Activity) getActivity()).overridePendingTransition(0,0);

    }
}
