package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int buttonCounter;
    String user = "Test User";
    private DBHelper db;

    // Set up, draw buttons
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = new DBHelper(this);
        setContentView(R.layout.activity_main);
        final TextView textView = findViewById(R.id.textView_id);
        final TextView textViewResult = findViewById(R.id.textViewResult_id);
        Button button = findViewById(R.id.button_id);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                buttonCounter++;
                textView.setText("Button Pushed " + buttonCounter);
                writeToDB();
                Log.d("PRESS", "Button Pushed " + buttonCounter);
            }
        });

        Button getAllButton = findViewById(R.id.getAllButton_id);
        getAllButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getAllFromDb();
            }
        });
    }


        private void writeToDB(){
            long id = db.insertNew(buttonCounter, user);
    }

        private void getAllFromDb(){
            final TextView textViewResult = findViewById(R.id.textViewResult_id);
            textViewResult.setText("Number of times pressed: " + db.getMostRecent(1) );
    }


}
