package io.mazur.warmshowers;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import io.mazur.warmshowers.Navigation.Drawer;

public class MainActivity extends SherlockFragmentActivity implements Drawer.NavigationDrawerCallbacks {
    private GoogleMap googleMap;

    private Drawer drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_main);

        drawer = (Drawer) getSupportFragmentManager().findFragmentById(R.id.drawer);
        drawer.setUp(this, R.id.drawer, (DrawerLayout) findViewById(R.id.layout));

//        googleMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
//        googleMap.addMarker(new MarkerOptions()
//                .position(new LatLng(0, 0))
//                .flat(true)
//                .title("Damian Mazurkiewicz"));
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {

    }
}
