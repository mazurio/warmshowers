package io.mazur.warmshowers;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.actionbarsherlock.app.SherlockFragment;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class FragmentProfile extends SherlockFragment {
    private FragmentManager fragmentManager;

    public static SherlockFragment newInstance(FragmentManager fragmentManager) {
        return new FragmentProfile(fragmentManager);
    }

    public FragmentProfile(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return (ViewGroup) inflater.inflate(R.layout.profile, null);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

    }
}
