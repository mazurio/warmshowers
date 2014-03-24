package io.mazur.warmshowers.Fragment;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.actionbarsherlock.app.SherlockFragment;
import com.devspark.robototextview.widget.RobotoTextView;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import io.mazur.warmshowers.R;

public class FragmentMap extends SherlockFragment {
    private MapView mapView;
    private GoogleMap map;

    static final LatLng PERTH = new LatLng(-31.90, 115.86);

    private RobotoTextView name;

    public static SherlockFragment newInstance() {
        return new FragmentMap();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.map, container, false);

        // Gets the MapView from the XML layout and creates it
        mapView = (MapView) view.findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);

        // Gets to GoogleMap from the MapView and does initialization stuff
        map = mapView.getMap();
        map.getUiSettings().setMyLocationButtonEnabled(false);
        map.setMyLocationEnabled(true);

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        name = (RobotoTextView) view.findViewById(R.id.name);

        map.addMarker(new MarkerOptions()
            .position(new LatLng(0, 0))
            .title("Damian Mazurkiewicz")
        );

        map.addMarker(
                new MarkerOptions()
                        .position(PERTH)
                        .title("Phil Benachour")
        );

        map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                name.setText(marker.getTitle());
                marker.setAlpha(2);
                return true;
            }
        });
    }

    @Override
    public void onResume() {
        mapView.onResume();
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }
}
