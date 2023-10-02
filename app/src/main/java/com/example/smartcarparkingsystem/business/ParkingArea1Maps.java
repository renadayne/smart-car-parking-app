package com.example.smartcarparkingsystem.business;

import android.os.Bundle;

import androidx.fragment.app.FragmentActivity;

import com.example.smartcarparkingsystem.R;
import com.example.smartcarparkingsystem.databinding.ActivityParkingArea1MapsBinding;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class ParkingArea1Maps extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityParkingArea1MapsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityParkingArea1MapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
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
        // Add a marker in Parking Area 1 and move the camera
        LatLng parkingarea1 = new LatLng(21.03706257060441, 105.7823135893581);
        mMap.addMarker(new MarkerOptions().position(parkingarea1).title("Bãi 1 : 144 Xuân Thuỷ, Cầu giấy"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(parkingarea1));
    }
}