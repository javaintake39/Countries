package com.example.countries;

import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Toast;

import com.example.countries.MainActivity;

public class MyHandler extends Handler
{

    MainActivity mainActivity;
    public MyHandler(MainActivity mainActivity)
    {
        this.mainActivity = mainActivity;
    }

    @Override
    public void handleMessage(Message msg)
    {
		/*
		call method showData in MainActivity which updates UI
		*/
        mainActivity.showData(mainActivity.counter); 
		// when I click Button Next
        mainActivity.btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mainActivity.counter<mainActivity.arrayList.size()-1)
                {
                    mainActivity.showData(++mainActivity.counter);
                }
                else
                {
                    Toast.makeText(mainActivity, "This is the Last Country", Toast.LENGTH_SHORT).show();
                }
            }
        });
		// when I click Button Previous
        mainActivity.btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mainActivity.counter<=mainActivity.arrayList.size() && mainActivity.counter>0)
                {
                    mainActivity.showData(--mainActivity.counter);
                }
                else
                {
                    Toast.makeText(mainActivity, "No Previous Countries", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
