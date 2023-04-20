package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.example.myapplication.databinding.ActivityMapsBinding;

public class MapsActivity extends BaseActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference dbReference = database.getReference("test");
    private Button findlocationbtn;

    private static final String TAG = "MapsActivity";

    ActivityMapsBinding activityMapsBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMapsBinding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(activityMapsBinding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        DatabaseReference dbReference = database.getReference();
        dbReference.addValueEventListener(locListener);
    }

    private ValueEventListener locListener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            if (snapshot.exists()) {
                LocationInfo location = snapshot.child("test").getValue(LocationInfo.class);
                Double locationLat = location != null ? location.latitude : null;
                Double locationLong = location != null ? location.longitude: null;
                findlocationbtn.setOnClickListener(v -> {
                    if (location != null && locationLong != null) {
                        LatLng latLng = new LatLng(locationLat, locationLong);
                        mMap.addMarker(new MarkerOptions().position(latLng).title("You are currently here"));
                        CameraUpdate update = CameraUpdateFactory.newLatLngZoom(latLng, 16.0f);
                        mMap.moveCamera(update);
                    } else {
                        Log.e(TAG, "User location cannot be found");
                    }
                });
            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {
            Toast.makeText(getApplicationContext(), "Could not read from database", Toast.LENGTH_LONG).show();
        }
    };

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
    }
}