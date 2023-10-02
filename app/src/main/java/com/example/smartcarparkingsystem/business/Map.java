package com.example.smartcarparkingsystem.business;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.AsyncQueryHandler;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.JsonReader;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import com.example.smartcarparkingsystem.R;
import com.example.smartcarparkingsystem.databinding.ActivityMapBinding;
import com.example.smartcarparkingsystem.directionhelpers.DataParser;
import com.example.smartcarparkingsystem.directionhelpers.FetchURL;
import com.example.smartcarparkingsystem.directionhelpers.TaskLoadedCallback;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Map extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap, hMap;
    private static final int LOCATION_REQUEST = 500;
    ArrayList<LatLng> listPoints;
    private ActivityMapBinding binding;
    private boolean mListener;
    //private Polyline currentPolyline;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        listPoints = new ArrayList<>();
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.getUiSettings().setZoomControlsEnabled(true);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
           ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_REQUEST);
            return;
        }
        mMap.setMyLocationEnabled(true);
        mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(@NonNull LatLng latLng) {
                // Reset marker when already 2
                if(listPoints.size() == 2) {
                    listPoints.clear();
                    mMap.clear();
                }
                // Save first points select
                listPoints.add(latLng);
                // Create Marker
                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.position(latLng);

                if(listPoints.size() == 1) {
                    // Add first marker to the map
                    markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
                }
                else {
                    // Add second marker to the map
                    markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
                }
                mMap.addMarker(markerOptions);

                if(listPoints.size() == 2) {
                    // Creat the URL to get request from first marker to second marker
                    String url = getRequestUrl(listPoints.get(0), listPoints.get(1));
                    TaskRequestDirections taskRequestDirections = new TaskRequestDirections();
                    taskRequestDirections.execute(url);
                }
            }
        });
        //CameraUpdate defaultpoint = CameraUpdateFactory.newLatLng(new LatLng(21.029036114647948, 105.78268649755309));
        //mMap.moveCamera(defaultpoint);
        // Marker Bai~ 1
        LatLng parkingarea1 = new LatLng(21.029036114647948, 105.78268649755309);
        Marker marker1 = mMap.addMarker(new MarkerOptions().position(parkingarea1).title("8 Tôn Thất Thuyết"));
        //marker1.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));

        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(parkingarea1, 13), 3000, null);
        // Marker Bai~ 2
        LatLng parkingarea2 = new LatLng(20.991337789707938, 105.79601703992847);
        Marker marker2 = mMap.addMarker(new MarkerOptions().position(parkingarea2).title("181 Lương Thế Vinh, Thanh Xuân"));
        // Cac vi tri gia lap
        LatLng yourpostition = new LatLng(21.007887731610875, 105.79295468220629);
        MarkerOptions pos = new MarkerOptions().position(yourpostition).title("Your Location");
        pos.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
        mMap.addMarker(pos);

        LatLng parkingarea3 = new LatLng(21.03706257060441, 105.7823135893581);
        Marker marker3 = mMap.addMarker(new MarkerOptions().position(parkingarea3).title("144 Xuân Thuỷ, Cầu giấy"));

        LatLng parkingarea4 = new LatLng(21.038498132918956, 105.78039379343709);
        Marker marker4 = mMap.addMarker(new MarkerOptions().position(parkingarea4).title("7 Phạm Văn Đồng, Mai Dịch, Từ Liêm"));


        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(@NonNull Marker marker) {
                if(marker.equals(marker1)) showNotiDialog();
                if(marker.equals(marker2)) showNotiDialog2();
                return false;
            }
        });

        //mMap.moveCamera(CameraUpdateFactory.newLatLng(parkingarea2));

    }

    private String getRequestUrl(LatLng origin, LatLng dest) {
        //Value of origin
        String str_org = "origin=" + origin.latitude + "," + origin.longitude;
        // Value of destination
        String str_dest = "destination=" + dest.latitude + "," + dest.longitude;
        //set value enable the sensor
        String sensor = "sensor=false";
        // Mode for find direction
        String mode = "mode=driving";
        //Build the full param
        String param = str_org + "&" + str_dest + "&" + sensor + "&" + mode;
        //Output format
        String output = "json";
        // Create url to request
        String url = "http://maps.googleapis.com/maps/api/directions/" + output + "?" + param;
        return url;
    }

    private String requestDirection(String reqUrl) throws IOException {
        String responeString = "";
        InputStream inputStream = null;
        HttpURLConnection httpURLConnection = null;
        try {
            URL url = new URL(reqUrl);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.connect();

            // Get the respone results
            inputStream = httpURLConnection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            StringBuffer stringBuffer = new StringBuffer();
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                stringBuffer.append(line);
            }

            responeString = stringBuffer.toString();
            bufferedReader.close();
            inputStreamReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(inputStream != null) {
                inputStream.close();
            }
            httpURLConnection.disconnect();
        }
        return responeString;
    }

    @SuppressLint({"MissingPermission", "MissingSuperCall"})
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        //super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case LOCATION_REQUEST:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    mMap.setMyLocationEnabled(true);
                }
                break;
        }
    }

    public class TaskRequestDirections extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            String responeString = "";
            try {
                responeString = requestDirection(strings[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return responeString;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            // Parse json here
            TaskParser taskParser = new TaskParser();
            taskParser.execute(s);
        }
    }

    public class TaskParser extends AsyncTask<String, Void, List<List<HashMap<String, String>>> > {

        @Override
        protected List<List<HashMap<String, String>>> doInBackground(String... strings) {
            JSONObject jsonObject = null;
            List<List<HashMap<String, String>>> routes = null;
            try {
                jsonObject = new JSONObject(strings[0]);
                DataParser directionParser = new DataParser();
                routes = directionParser.parse(jsonObject);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return routes;
        }

        @Override
        protected void onPostExecute(List<List<HashMap<String, String>>> lists) {
            // Get list route and display it into the map
            ArrayList points = null;

            PolylineOptions polylineOptions = null;

            for(List<HashMap<String, String>> path:lists) {
                points = new ArrayList();
                polylineOptions = new PolylineOptions();

                for(HashMap<String, String> point : path) {
                    double lat = Double.parseDouble(point.get("lat"));
                    double lng = Double.parseDouble(point.get("lng"));

                    points.add(new LatLng(lat, lng));
                }
                polylineOptions.addAll(points);
                polylineOptions.width(15);
                polylineOptions.color(Color.RED);
                polylineOptions.geodesic(true);
            }

            if(polylineOptions != null) {
                mMap.addPolyline(polylineOptions);
            }
            else {
                Toast.makeText(getApplicationContext(), "Không tìm thấy đường", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private  void showNotiDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(Map.this, R.style.AlertDialogTheme);
        View view = LayoutInflater.from(Map.this).inflate(
                R.layout.layout_noti_bg,(ConstraintLayout)findViewById(R.id.layoutDialogContainer)
        );
        builder.setView(view);
        ((TextView) view.findViewById(R.id.textTitle)).setText("Parking Info");
        ((TextView) view.findViewById(R.id.textMessage)).setText("\n\nĐịa chỉ/ Address : Số 8 Tôn Thất Thuyết, Cầu Giấy, Hà Nội\nSỗ chỗ trong bãi đỗ : 8");

        ((Button) view.findViewById(R.id.buttonShowMap)).setText("Parking Status");
        ((Button) view.findViewById(R.id.buttonBack)).setText("Back");
        //((ImageView) view.findViewById(R.id.imageIcon)).setImageResource();
        final AlertDialog alertDialog = builder.create();
        // nut chuyen huong sang man hinh map
        view.findViewById(R.id.buttonShowMap).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Map.this, ParkingAreaClone.class);
                Bundle bundle = new Bundle();
                intent.putExtra("packet", bundle);
                startActivity(intent);
            }
        });


        // nut tro ve
        view.findViewById(R.id.buttonBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
                //Toast.makeText(ChooseParking.this, "", Toast.LENGTH_SHORT).show();
            }
        });

        if (alertDialog.getWindow() != null) {
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }
        alertDialog.show();

    }

    private  void showNotiDialog2() {
        AlertDialog.Builder builder = new AlertDialog.Builder(Map.this, R.style.AlertDialogTheme);
        View view = LayoutInflater.from(Map.this).inflate(
                R.layout.layout_noti_bg,(ConstraintLayout)findViewById(R.id.layoutDialogContainer)
        );
        builder.setView(view);
        ((TextView) view.findViewById(R.id.textTitle)).setText("Parking Info");
        ((TextView) view.findViewById(R.id.textMessage)).setText("\n\nĐịa chỉ/ Address : 181 Lương Thế Vinh, Thanh Xuân, Hà Nội\nSỗ chỗ trong bãi đỗ : 10");

        ((Button) view.findViewById(R.id.buttonShowMap)).setText("Parking Status");
        ((Button) view.findViewById(R.id.buttonBack)).setText("Back");
        //((ImageView) view.findViewById(R.id.imageIcon)).setImageResource();
        final AlertDialog alertDialog = builder.create();
        // nut chuyen huong sang man hinh map
        view.findViewById(R.id.buttonShowMap).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Map.this, ParkingArea2.class);
                Bundle bundle = new Bundle();
                intent.putExtra("packet", bundle);
                startActivity(intent);
            }
        });


        // nut tro ve
        view.findViewById(R.id.buttonBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
                //Toast.makeText(ChooseParking.this, "", Toast.LENGTH_SHORT).show();
            }
        });

        if (alertDialog.getWindow() != null) {
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }
        alertDialog.show();

    }

    private  void showMapNoti() {
        AlertDialog.Builder builder = new AlertDialog.Builder(Map.this, R.style.AlertDialogTheme);
        View view = LayoutInflater.from(Map.this).inflate(
                R.layout.layout_noti_3_bg,(ConstraintLayout)findViewById(R.id.layoutDialogContainer)
        );
        builder.setView(view);
        ((TextView) view.findViewById(R.id.textTitle)).setText("Parking Info");
        ((TextView) view.findViewById(R.id.textMessage)).setText("\n\nĐịa chỉ/ Address : Số 8 Tôn Thất Thuyết, Cầu Giấy, Hà Nội\nSỗ chỗ trong bãi đỗ : 8");

        ((Button) view.findViewById(R.id.buttonShowMap)).setText("Tình trạng bãi đỗ");
        ((Button) view.findViewById(R.id.buttonFindWay)).setText("Dẫn đường");
        ((Button) view.findViewById(R.id.buttonBack)).setText("Trở về");
        //((ImageView) view.findViewById(R.id.imageIcon)).setImageResource();
        final AlertDialog alertDialog = builder.create();
        // nut chuyen huong sang man hinh map
        view.findViewById(R.id.buttonShowMap).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Map.this, ParkingAreaClone.class);
                Bundle bundle = new Bundle();
                intent.putExtra("packet", bundle);
                startActivity(intent);
            }
        });

        view.findViewById(R.id.buttonFindWay).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // chỉ đường
            }
        });


        // nut tro ve
        view.findViewById(R.id.buttonBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
                //Toast.makeText(ChooseParking.this, "", Toast.LENGTH_SHORT).show();
            }
        });

        if (alertDialog.getWindow() != null) {
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }
        alertDialog.show();

    }

}