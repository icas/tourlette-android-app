package com.example.myapplication;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.placesapi.model.Places;

import java.util.ArrayList;
import java.util.HashMap;

public class DisplayMessageActivity extends Activity {

    @SuppressLint("NewApi")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Get the message from the intent
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        // Create the text view
        TextView textView = new TextView(this);
        textView.setTextSize(40);
        textView.setText(message);

        // Set the text view as the activity layout
        Thread thread = new Thread(new Runnable(){
            @Override
            public void run() {
                Places places = new Places();
                ArrayList allPlaces = places.get(new HashMap());
                Log.i("ICAS-PROGRAM-THREAD", allPlaces.toString());
            }
        });

        thread.start();

        setContentView(textView);

    }

    public void onStop(){
       super.onStop();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
