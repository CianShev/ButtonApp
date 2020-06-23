package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.HTTPAccess;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.Calendar;
public class MainActivity extends AppCompatActivity {
    HTTPAccess access = new HTTPAccess();
    int buttonCounter;
    /*
    TODO
    Establish schema and functionality for users
     */
    String user = "Test User";
    String responseValue = "";
    private DBHelper db;
    private long currentId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = new DBHelper(this);
        setContentView(R.layout.activity_main);
        final TextView textView = findViewById(R.id.textView_id);
        final TextView textViewServerReponse = findViewById(R.id.textViewServerResponse);
        final TextView textViewResult = findViewById(R.id.textViewResult_id);
        Button button = findViewById(R.id.button_id);
        button.setOnClickListener(new View.OnClickListener() {
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
                getAllFromDb(currentId);
            }
        });


        final Button sendToServerButton = findViewById(R.id.sendToServerButton_id);
        sendToServerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendToServer();
                textViewServerReponse.setText(sendToServer());
            }
        });
    }

    /*
    TODO
    Break this out service out into its own class
     */
    private String sendToServer() {
        RequestQueue queue = Volley.newRequestQueue(this);
        String URL = access.getAccessToken();
        JsonArrayRequest jsonRequest = new JsonArrayRequest(Request.Method.GET, URL, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d("Response from server", "onResponse: " + response.toString());
                        responseValue = response.toString();
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Server response", "onErrorResponse: " + " Server response error" + error);
                responseValue = error.toString();
            }

        });
        queue.add(jsonRequest);
        return responseValue;
    }


        private void writeToDB(){
        String currentTime = Calendar.getInstance().getTime().toString();
        currentId = db.insertNew(buttonCounter, user, currentTime);
            Log.d("writeToDB", "writeToDB: " + currentId);
    }

        private void getAllFromDb(long id){
            final TextView textViewResult = findViewById(R.id.textViewResult_id);
            textViewResult.setText("Number of times pressed: " + db.getMostRecent(id) );
    }


}
