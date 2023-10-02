package com.example.smartcarparkingsystem.business;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.smartcarparkingsystem.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ParkingAreaClone extends AppCompatActivity {
    public ImageView redcar;
    private TextView sensor1, sensor2, slotleft, slotleft2;
    private TextView note;
    private Button download;
    private Button upload;
    private RequestQueue mQueue;
    public String Status1, Status2, Status3, Status4;
    public String dataUP;
    public int slot = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking_area_clone);
        //sensor2 = findViewById(R.id.sensor2);
        //sensor1 = findViewById(R. id.sensor1);
        slotleft = (TextView) findViewById(R.id.slotleft);
        slotleft2 = (TextView) findViewById(R.id.slotleft2);
        redcar = (ImageView) findViewById(R.id.redcarno1);
        mQueue= Volley.newRequestQueue(this);

        content();
    }

    public void jsonParse() {
        String urlfeed = "https://api.thingspeak.com/channels/1661007/feeds.json?api_key=KKT5CZ8SCYQY5R8G&results=2"; //change this with you http request "READ A CHANNEL FEED"
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, urlfeed, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("feeds");
                            JSONObject feeds = jsonArray.getJSONObject(0);
                            Status1 = feeds.getString("field1");
                            Status2 = feeds.getString("field2");
                            if(Status1.equals("1")) {//sensor1.setText(Status1);
                                redcar.setVisibility(View.VISIBLE);
                                slot = 4;
                            }
                            else {redcar.setVisibility(View.INVISIBLE);
                                slot = 5;}

                            //sensor2.setText(Status2);
                            if(Status2.equals("1")) slot--;
                            slotleft.setText("Parked Car: " + String.valueOf(slot));
                            slotleft2.setText("Number of cars entering parking lot : 0");
                            //slotleft2.setText("Số xe đang vào bãi : " + Status2);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        mQueue.add(request); }



    public void urlreq() {

        RequestQueue queue = Volley.newRequestQueue(this);
        final String base = "https://api.thingspeak.com/update?";
        final String api_key = "api_key";
        final String field_1 = "field1";

        // Build up the query URI
        Uri builtURI = Uri.parse(base).buildUpon()
                .appendQueryParameter(api_key, "7Z982VO9JNGFQCD3") //change this with your write api key
                .appendQueryParameter(field_1, dataUP )
                .build();
        String url = builtURI.toString();

        }

    // loop task
    public void content()
    {
        jsonParse();
        // after end of content, call this method
        refresh(100);
    }

    private void refresh(int milliseconds) {
        final Handler handler = new Handler();

        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                content();
            }
        };

        handler.postDelayed(runnable, milliseconds);
    }

}