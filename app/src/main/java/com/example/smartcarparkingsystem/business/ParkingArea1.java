package com.example.smartcarparkingsystem.business;
// do xung đột app nên file màn hình này bị bỏ qua
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

public class ParkingArea1 extends AppCompatActivity {
    ImageView redcar, redcar1, redcar2, redcar3;
    private TextView sensor, sensor2, sensor3, sensor4;
    TextView textView4, sensorx, sensory;
    private TextView note;
    private Button download;
    private Button upload;
    private RequestQueue mQueue;
    public String Status1, Status2, Status3, Status4;
    public String dataUP;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking_area1);
        Init();
        // nếu cảm biến báo có thì hiển thị và ngc lại
        //sensor2 = findViewById(R.id.sensor2);

        //note = findViewById(R. id. note);
        sensorx = (TextView) findViewById(R.id.sensorx);
        sensory = (TextView) findViewById(R.id.sensory);
        download = (Button) findViewById(R. id. download);
        //upload = (Button) findViewById(R. id. button2);
        mQueue= Volley.newRequestQueue(this);
        redcar.setVisibility(View.INVISIBLE);
        redcar1.setVisibility(View.VISIBLE);
        redcar2.setVisibility(View.VISIBLE);
        redcar3.setVisibility(View.VISIBLE);
        // xử lí về số lượng xe ra vào trong bãi
        // cập nhật thủ công bằng button do sự không ổn định khi cập nhật theo thời gian
        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ParkingArea1.this, ParkingAreaClone.class);
                Bundle bundle = new Bundle();
                intent.putExtra("packet", bundle);
                startActivity(intent);
            }
        });
        // cập nhật tình hình bãi đỗ xe theo thời gian 5s/ lần
        //content();
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
                            if(Status1.equals("1")) sensorx.setText(Status1);
                            else sensorx.setText("2");
                            sensor2.setText(Status2);

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

        // Request a string response from the provided URL.

        /*StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display response string.

                        if (response.equals("0")) {
                            note.setText("Set failed" + "\n" + "try again !!"+ "\n" + "Response Id: "+ response);

                        } else { note.setText("Set success" +"\n" + "Response Id: "+ response);}
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                note.setText("no internet");

            }
        });
        queue.add(stringRequest);*/}

    // loop task
    public void content()
    {
        /*count++;
        textView.setText("refresh" + count);*/
        jsonParse();
        // after end of content, call this method
        refresh(20000);
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



    private void Init() {
        textView4 = (TextView) findViewById(R.id.textView4);
        redcar1 = (ImageView) findViewById(R.id.redcar1);
        redcar2 = (ImageView) findViewById(R.id.redcar2);
        redcar3 = (ImageView) findViewById(R.id.redcar3);
        redcar = (ImageView) findViewById(R.id.redcar);
    }
}