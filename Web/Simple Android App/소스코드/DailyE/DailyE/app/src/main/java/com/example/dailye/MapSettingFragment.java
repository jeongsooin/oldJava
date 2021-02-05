package com.example.dailye;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class MapSettingFragment extends Fragment {
    private static final String TAG = "MainActivity";

    Geocoder geocoder;
    EditText etAddress;
    SupportMapFragment mapFragment;
    GoogleMap map;
    LatLng myStudyLocation;
    ImageButton imageButton;

    public static MapSettingFragment getInstance() {
        return new MapSettingFragment();
    }

    public MapSettingFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        ViewGroup rootView =
                (ViewGroup) inflater.inflate(R.layout.fragment_map_setting, container, false);
        if (ContextCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                    android.Manifest.permission.ACCESS_FINE_LOCATION)) {
            } else {
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            }
        }

        geocoder = new Geocoder(getContext());
        etAddress = rootView.findViewById(R.id.address_string);

        mapFragment = (SupportMapFragment) this.getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                Log.d(TAG, "GoogleMap is Ready.");
                map = googleMap;
                requestMyLocation();
                onAddMarker();
            }
        });

        try {
            MapsInitializer.initialize(getContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        ImageButton button = rootView.findViewById(R.id.search_address);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBtnClicked();
            }
        });

        Switch switch_button = rootView.findViewById(R.id.switch_location);
        switch_button.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                MainActivity activity = (MainActivity) getActivity();
                if (isChecked && myStudyLocation != null) {
                    activity.startLocationService(myStudyLocation);
                } else if(isChecked && myStudyLocation == null) {
                    Toast.makeText(getContext(), "지역을 설정해 주세요", Toast.LENGTH_SHORT).show();
                } else {
                    activity.stopLocationService();
                }
            }
        });

        return rootView;
    }

    public void onBtnClicked() {
        List<Address> list = null;
        String address = etAddress.getText().toString();
        LatLng location = null;
        try {
            list = geocoder.getFromLocationName(address, 10);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (list != null) {
            double lat = list.get(0).getLatitude();
            double lng = list.get(0).getLongitude();
            location = new LatLng(lat, lng);
            this.myStudyLocation = location;
            map.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_my_locatin)).position(location));
        }
    }

    public void onAddMarker() {
        map.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_my_locatin));
                markerOptions.position(latLng);

                Double latitude = latLng.latitude;
                Double longitude = latLng.longitude;
                LatLng location = new LatLng(latitude, longitude);

                myStudyLocation = location;
                Log.d(TAG, "myStudyLocation latitude : " + location.latitude + ", longitude : " + location.longitude);
                map.clear();
                map.animateCamera(CameraUpdateFactory.newLatLng(latLng));
                map.addMarker(markerOptions);
            }
        });
    }

    public void requestMyLocation() {

        LocationManager manager =
                (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);

        try {
            long minTime = 10000;
            float minDistance = 0;
            manager.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER,
                    minTime,
                    minDistance,
                    new LocationListener() {
                        @Override
                        public void onLocationChanged(Location location) {
                            showCurrentLocation(location);
                        }

                        @Override
                        public void onStatusChanged(String provider, int status, Bundle extras) {

                        }

                        @Override
                        public void onProviderEnabled(String provider) {

                        }

                        @Override
                        public void onProviderDisabled(String provider) {

                        }
                    }
            );

            Location lastlocation = manager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if (lastlocation != null) {
                showCurrentLocation(lastlocation);
            }

            manager.requestLocationUpdates(
                    LocationManager.NETWORK_PROVIDER,
                    minTime,
                    minDistance,
                    new LocationListener() {
                        @Override
                        public void onLocationChanged(Location location) {
                            showCurrentLocation(location);
                        }

                        @Override
                        public void onStatusChanged(String provider, int status, Bundle extras) {

                        }

                        @Override
                        public void onProviderEnabled(String provider) {

                        }

                        @Override
                        public void onProviderDisabled(String provider) {

                        }
                    }
            );
        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }

    private void showCurrentLocation(Location location) {
        LatLng curPoint = new LatLng(location.getLatitude(), location.getLongitude());
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(curPoint, 15));
    }

    @Override
    public void onPause() {
        super.onPause();
        if (map != null) {
            try {
                map.setMyLocationEnabled(false);
            } catch (SecurityException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onResume(){
        super.onResume();
        if (map != null) {
            try {
                map.setMyLocationEnabled(true);
            } catch (SecurityException e) {
                e.printStackTrace();
            }
        }
    }

}
