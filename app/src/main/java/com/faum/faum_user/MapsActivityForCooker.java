package com.faum.faum_user;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.location.Location;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.faum.faum_expert.R;
import com.firebase.geofire.GeoFire;
import com.firebase.geofire.GeoLocation;
import com.firebase.geofire.LocationCallback;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
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
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static com.faum.faum_user.Main2Activity.uid;

public class MapsActivityForCooker extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    SharedPreferences mPrefrences;

    DatabaseReference locationRefrence = FirebaseDatabase.getInstance().getReference("Expert Location Information");
    DatabaseReference userlocationRefrence = FirebaseDatabase.getInstance().getReference("User Location Information");
    GeoFire geoFire = new GeoFire(locationRefrence);
    GeoFire geofireUser = new GeoFire(userlocationRefrence);

    Button btnConfirmLocation;
    private int PLACE_PICKER_REQUEST=1;

    public static LatLng userLocation;
    public static LatLng cookerLocation;

    String placeName,placeAddress;

    Boolean flag = false;



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

        //GoogleMap googleMap = null;
        //mMap = googleMap;

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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == PLACE_PICKER_REQUEST){
            if (resultCode == RESULT_OK){

                Place place = PlacePicker.getPlace(data,this);
                placeName = String.format("Place: %s", place.getName());
                placeAddress = String.format("Address: %s", place.getAddress());

                userLocation = new LatLng(place.getLatLng().latitude,place.getLatLng().longitude);

                geofireUser.setLocation(uid,new GeoLocation(userLocation.latitude,userLocation.longitude));

                mMap.addMarker(new MarkerOptions().position(userLocation)
                        .title(placeName).snippet(placeAddress)
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA)));


                flag = true;

            }
        }
    }
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

                cookerLocation = new LatLng(location.latitude,location.longitude);

                mMap.addMarker(new MarkerOptions().position(cookerLocation)
                        .title("Cooker Location")
                        .icon(BitmapDescriptorFactory.fromBitmap(bmp)));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(cookerLocation));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15),2000,null);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        if(flag == true){

            mMap.addMarker(new MarkerOptions().position(userLocation)
                    .title(placeName).snippet(placeAddress)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA)));

            mMap.animateCamera(CameraUpdateFactory.zoomTo(15),2000,null);

            /*
            mMap.addPolyline(new PolylineOptions()
                    .add(new LatLng(cookerLocation.latitude,cookerLocation.longitude),
                            new LatLng(userLocation.latitude,userLocation.longitude))
                    .width(10)
                    .color(Color.RED)
            );
            */

            /*

            PolylineOptions polylineOptions = new PolylineOptions()
                    .add(new LatLng(cookerLocation.latitude,cookerLocation.longitude))
                    .add(new LatLng(userLocation.latitude,userLocation.longitude)); // Point B.
            Polyline polyline = mMap.addPolyline(polylineOptions);

            */

            PolylineOptions polylineoptions = new PolylineOptions()
                    .add(new LatLng(cookerLocation.latitude,cookerLocation.longitude))
                    .add(new LatLng(userLocation.latitude,userLocation.longitude))
                    .width(10)
                    .color(Color.RED)
                    .visible(true)
                    .geodesic(true);
            Polyline polyline = mMap.addPolyline(polylineoptions);


        }


        btnConfirmLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MapsActivityForCooker.this,Deal_Invoice.class));
            }
        });



    }



}
