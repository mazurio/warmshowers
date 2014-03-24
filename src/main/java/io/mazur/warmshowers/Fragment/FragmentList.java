package io.mazur.warmshowers.Fragment;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.actionbarsherlock.app.SherlockFragment;

import io.mazur.warmshowers.R;

public class FragmentList extends SherlockFragment {
    private FragmentManager fragmentManager;

    public static SherlockFragment newInstance() {
        return new FragmentList();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return (ViewGroup) inflater.inflate(R.layout.list, null);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

    }
}
