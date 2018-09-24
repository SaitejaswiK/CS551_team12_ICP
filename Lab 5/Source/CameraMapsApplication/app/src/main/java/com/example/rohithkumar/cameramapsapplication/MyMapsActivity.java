package com.example.rohithkumar.cameramapsapplication;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.security.KeyStore;
import java.util.List;


public class MyMapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private LocationManager manager;
    public Location location;
    private LocationListener locationListener;

    private static final int  MY_PERMISSIONS_REQUEST_FINE_LOCATION = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        //get the location service


//        locationListener = new LocationListener() {
//            @Override
////            public void onLocationChanged() {
////                //get the latitude and longitude from the location
////                double latitude = location.getLatitude();
////                double longitude = location.getLongitude();
////                //get the location name from latitude and longitude
////                Geocoder geocoder = new Geocoder(getApplicationContext());
////                try {
////                    List<Address> addresses =
////                            geocoder.getFromLocation(latitude, longitude, 1);
////                    String result = addresses.get(0).getSubLocality()+":";
////                    result += addresses.get(0).getLocality()+":";
////                    result += addresses.get(0).getCountryCode();
////                    LatLng latLng = new LatLng(latitude, longitude);
////                    mMap.addMarker(new MarkerOptions().position(latLng).title(result));
////                    mMap.setMaxZoomPreference(10);
////                    mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
////                } catch (IOException e) {
////                    e.printStackTrace();
////                }
////            }
//
//            @Override
//            public void onStatusChanged(String provider, int status, Bundle extras) {
//
//            }
//
//            @Override
//            public void onProviderEnabled(String provider) {
//
//            }
//
//            @Override
//            public void onProviderDisabled(String provider) {
//
//            }
//        };
        //manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
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
        manager = (LocationManager) getSystemService(LOCATION_SERVICE);
        //request the location update thru location manager
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            // ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            // public void onRequestPermissionsResult(int requestCode, String[] permissions,
            // int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            ActivityCompat.requestPermissions(MyMapsActivity.this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    MY_PERMISSIONS_REQUEST_FINE_LOCATION);
            //return;
        }
        location =manager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

        double latitude = location.getLatitude();
        double longitude = location.getLongitude();

        // Add a marker in Sydney and move the camera
        LatLng latlongval = new LatLng(latitude, longitude);
        mMap.addMarker(new MarkerOptions().position(latlongval).title("Current Position"+latlongval));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latlongval));
    }

    @Override
    protected void onPause() {
        super.onPause();
        manager.removeUpdates(locationListener);
        Log.i("onPause...","paused");
    }
}
