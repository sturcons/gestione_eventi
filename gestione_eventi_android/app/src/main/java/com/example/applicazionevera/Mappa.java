package com.example.applicazionevera;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.applicazionevera.databinding.ActivityMappaBinding;

public class Mappa extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMappaBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMappaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        googleMap.getUiSettings().setZoomControlsEnabled(true);
        googleMap.getUiSettings().setZoomGesturesEnabled(false);

        // Add a marker in Sydney and move the camera
        LatLng milano = new LatLng(45.4654219, 9.1859243);
        mMap.addMarker(new MarkerOptions().position(milano).title("Marker in Milano"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(milano));
    }
}