package com.apps.liamk.csvtester;

import android.support.v7.app.AppCompatActivity;
import android.app.Activity;
import android.os.Parcelable;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.listCSV);

        readGPSData();
    }

    private final List<Coordinates> coordArray = new ArrayList<>();

    private void readGPSData() {
        // Read the raw csv file
        InputStream is = getResources().openRawResource(R.raw.rawgps);
        // Reads text from character-input stream, buffering characters for efficient reading
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(is, Charset.forName("UTF-8"))
        );
        // Initialization
        String line = "";
        // Initialization
        try {
            // Step over headers
            reader.readLine();
            // If buffer is not empty
            while ((line = reader.readLine()) != null) {
                Log.d("MyActivity","Line: " + line);
                // use comma as separator columns of CSV
                String[] tokens = line.split(",");
                // Read the data
                Coordinates sample = new Coordinates();
                // Setters
                sample.setLat(tokens[0]);
                sample.setLon(tokens[1]);

                // Adding object to a class
                coordArray.add(sample);
                final ArrayAdapter<Coordinates> adapter = new ArrayAdapter<Coordinates>(getApplicationContext(), android.R.layout.simple_list_item_1, coordArray);
                listView.setAdapter(adapter);
                // Log the object
                Log.d("My Activity", "Just created: " + sample);
            }

        } catch (IOException e) {
            // Logs error with priority level
            Log.wtf("MyActivity", "Error reading data file on line" + line, e);

            // Prints throwable details
            e.printStackTrace();
        }
    }
}
