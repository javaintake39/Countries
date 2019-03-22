package com.example.countries;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Handler;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

// MyAsyncTask takes String : URL of Image and returns Bitmap Image as Result
public class MyAsyncTask extends AsyncTask<String, Void, Bitmap>
{
   MainActivity mainActivity;
    public MyAsyncTask(MainActivity mainActivity)
    {
        this.mainActivity = mainActivity;
    }
	// doInBackground takes url of image & returns Bitmap
    @Override
    protected Bitmap doInBackground(String... url)
    {
        Bitmap result = null;
        try {
            result = downloadImage(url [0]);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
	// after Downloading Image 
    @Override
    protected void onPostExecute(Bitmap bitmap)
    {
        super.onPostExecute(bitmap);
        mainActivity.imgFlag.setImageBitmap(bitmap); // set Downloaded image into it's place in mainActivity
    }
    private Bitmap downloadImage(String url) throws IOException {
        Bitmap result=null;
        URL urlObject ;
        HttpURLConnection httpURLConnection;
        InputStream inputStream = null;
        try {
            urlObject=new URL(url);
            httpURLConnection = (HttpURLConnection)urlObject.openConnection();
            httpURLConnection.connect();

            inputStream= httpURLConnection.getInputStream();
            result = BitmapFactory.decodeStream(inputStream);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if(inputStream !=null)
                inputStream.close();
        }
        return result;
    }
}