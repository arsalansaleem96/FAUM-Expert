package com.faum.faum_user;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.view.View;
import android.widget.Button;

import com.faum.faum_expert.R;
import com.firebase.geofire.GeoFire;
import com.firebase.geofire.GeoLocation;
import com.firebase.geofire.LocationCallback;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MapsActivityForCooker extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    SharedPreferences mPrefrences;

    DatabaseReference locationRefrence = FirebaseDatabase.getInstance().getReference("Expert Location Information");
    GeoFire geoFire = new GeoFire(locationRefrence);

    Button btnConfirmLocation;
    private int PLACE_PICKER_REQUEST=999;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps_for_cooker);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        mPrefrences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor mEditor =  mPrefrences.edit();

        btnConfirmLocation = (Button)findViewById(R.id.btnConfirmLocation);

        PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();

        try {
            Intent intent = builder.build(MapsActivityForCooker.this);
            startActivityForResult(intent, PLACE_PICKER_REQUEST);
        } catch (GooglePlayServicesRepairableException e) {
            e.printStackTrace();
        } catch (GooglePlayServicesNotAvailableException e) {
            e.printStackTrace();
        }

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
    public void onMapReady(final GoogleMap googleMap) {
        mMap = googleMap;
        Bitmap.Config conf = Bitmap.Config.ARGB_8888;
        final Bitmap bmp = Bitmap.createBitmap(80, 80, conf);
        Canvas canvas1 = new Canvas(bmp);
        Paint color = new Paint();
        canvas1.drawBitmap(BitmapFactory.decodeResource(getResources(),
                R.drawable.cooker), 0,0, color);

        String location = mPrefrences.getString(getString(R.string.COOKER_ID)," ");


        geoFire.getLocation(location, new LocationCallback() {
            @Override
            public void onLocationResult(String key, GeoLocation location) {

                LatLng cookerLocation = new LatLng(location.latitude,location.longitude);
                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.position(cookerLocation);
                markerOptions.title("Cooker Location");
                //markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
                markerOptions.icon(BitmapDescriptorFactory.fromBitmap(bmp));
                // Add a marker in Sydney and move the camera

                mMap.addMarker(new MarkerOptions().position(cookerLocation).title("Cooker Location"));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(cookerLocation));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15),2000,null);


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        btnConfirmLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MapsActivityForCooker.this,User_sign.class));
            }
        });

    }
}
