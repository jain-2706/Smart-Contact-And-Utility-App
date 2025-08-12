package com.example.my_first_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


import java.io.IOException;
import java.util.List;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps2);
        SupportMapFragment s1=(SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        if(s1!=null) {
            s1.getMapAsync(this);
        }

    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap=googleMap;
        LatLng lng=new LatLng(29.471397, 77.696732);
        MarkerOptions moptions=new MarkerOptions().position(lng).title("Muzaffarnagar");
        googleMap.addMarker(moptions);
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(lng));
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(lng,16f));
        googleMap.addCircle(new CircleOptions().center(lng).radius(1000).fillColor(Color.GREEN).strokeColor(Color.RED));
        googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(@NonNull LatLng latLng) {
                Geocoder g1=new Geocoder(MapsActivity.this);
                try {
                    List<Address> l1=g1.getFromLocation(latLng.latitude,latLng.longitude,2);
                    for(int i=0;i<l1.size();i++)
                    {
                        Log.d("Location is ",l1.get(i).getAddressLine(0));
                    }
                    MarkerOptions moptions=new MarkerOptions().position(latLng).title("Clicked Here");
                    googleMap.addMarker(moptions);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
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

}