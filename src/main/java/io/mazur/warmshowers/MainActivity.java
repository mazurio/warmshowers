package io.mazur.warmshowers;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import io.mazur.warmshowers.Navigation.Drawer;

public class MainActivity extends SherlockFragmentActivity implements Drawer.NavigationDrawerCallbacks {
    private Drawer drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_main);

        // Setup Navigation Drawer
        drawer = (Drawer) getSupportFragmentManager().findFragmentById(R.id.drawer);
        drawer.setUp(this, R.id.drawer, (DrawerLayout) findViewById(R.id.layout));
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment defaultFragment;

        switch(position) {
            case(0): {
                defaultFragment = FragmentMap.newInstance(fragmentManager);
                break;
            }
            default: {
                defaultFragment = FragmentProfile.newInstance(fragmentManager);
                break;
            }
        }

        fragmentManager.beginTransaction().replace(R.id.container, defaultFragment).commit();
    }
}
