package com.example.islam.cars.availablecarsmap.view;

import android.os.Bundle;

import com.example.islam.cars.BaseActivity;
import com.example.islam.cars.R;
import com.example.islam.cars.common.Constants;
import com.example.islam.cars.data.models.PlacemarksItem;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

/**
 * Created by islam on 29/05/17.
 * there is no presenter nor business as this activity only show data sent from the
 * previous activity and has no interaction except zoom which is supported by google map
 */

public class AvailableCarsMapActivity extends BaseActivity implements OnMapReadyCallback {
    List<PlacemarksItem> mPlacemarksItems;
    PlacemarksItem mSelectedPlacemarksItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_available_cars_map);
        mPlacemarksItems = getIntent().getExtras().getParcelableArrayList(Constants.EXTRAS_PLACE_MARK_LIST_ITEMS);
        mSelectedPlacemarksItem = getIntent().getExtras().getParcelable(Constants.EXTRAS_PLACE_MARK_ITEMS);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap map) {
        map.getUiSettings().setZoomControlsEnabled(true);
        if (mSelectedPlacemarksItem != null) {
            map.addMarker(new MarkerOptions().position(new LatLng(mSelectedPlacemarksItem.getCoordinates().
                    get(1), mSelectedPlacemarksItem.getCoordinates().get(0))).title(mSelectedPlacemarksItem.getName()));
            animateToMarker(map, mSelectedPlacemarksItem.getCoordinates().get(1), mSelectedPlacemarksItem.getCoordinates().get(0));
        } else if (mPlacemarksItems != null) {
            for (PlacemarksItem placemarksItem : mPlacemarksItems) {
                map.addMarker(new MarkerOptions().position(new LatLng(placemarksItem.getCoordinates().
                        get(1), placemarksItem.getCoordinates().get(0))).title(placemarksItem.getName()));
            }
            // animate to the first one
            animateToMarker(map, mPlacemarksItems.get(0).getCoordinates().
                    get(1), mPlacemarksItems.get(0).getCoordinates().
                    get(0));
        }
    }

    private void animateToMarker(GoogleMap map, double latitude, double longitude) {
        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        builder.include(new LatLng(latitude, longitude));
        LatLngBounds bounds = builder.build();
        map.animateCamera(CameraUpdateFactory.newLatLngBounds(bounds, 25, 25, 5));
    }
}

