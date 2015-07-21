package com.example.wadamikako.googlemapsapplication01;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MapsActivity extends FragmentActivity {

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        setUpMapIfNeeded();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_map_top, menu);
        menu.add("select");
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    /**
     * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
     * installed) and the map has not already been instantiated.. This will ensure that we only ever
     * call {@link #setUpMap()} once when {@link #mMap} is not null.
     * <p/>
     * If it isn't installed {@link SupportMapFragment} (and
     * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
     * install/update the Google Play services APK on their device.
     * <p/>
     * A user can return to this FragmentActivity after following the prompt and correctly
     * installing/updating/enabling the Google Play services. Since the FragmentActivity may not
     * have been completely destroyed during this process (it is likely that it would only be
     * stopped or paused), {@link #onCreate(Bundle)} may not be called again so we should call this
     * method in {@link #onResume()} to guarantee that it will be called.
     */
    private void setUpMapIfNeeded() {

        if (mMap == null) {

            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }

    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * just add a marker near Africa.
     * <p/>
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     */
    private void setUpMap() {

        FragmentManager manager = getSupportFragmentManager();
        SupportMapFragment frag = (SupportMapFragment) manager.findFragmentById(R.id.map);
        // GoogleMapの取得
        GoogleMap map = frag.getMap();

        // モリジョビの場所にピンとコメントを表示
        LatLng position = new LatLng(39.704802, 141.142702);
        MarkerOptions options = new MarkerOptions();
        options.position(position);
        options.title("盛岡情報ビジネス専門学校");
        map.addMarker(options);

        Intent intent = this.getIntent();
        final String address = intent.getStringExtra("address");

        double latitude = 43.0675;
        double longitude = 141.350784;

        try{
            Address address1 = getLatLongFromLocationName(address); //受け取った住所
            latitude = address1.getLatitude();
            longitude = address1.getLongitude();

        }catch (IOException e){
            e.printStackTrace();
        }

        //カメラ
        CameraUpdate cu = CameraUpdateFactory.newLatLngZoom(new LatLng(latitude,longitude),18);
        mMap.moveCamera(cu);
        mMap.setMyLocationEnabled(true);
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.addMarker(new MarkerOptions().position(new LatLng(0, 0)).title("Marker"));
    }

    private Address getLatLongFromLocationName(String locationName) throws IOException{

        Geocoder gcoder = new Geocoder(this, Locale.getDefault());
        List<Address>addressesList = gcoder.getFromLocationName(locationName, 1);
        Address address1 = addressesList.get(0);

        return address1;

    }

}


