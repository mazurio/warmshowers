package io.mazur.warmshowers.Fragment;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.actionbarsherlock.app.SherlockFragment;
import com.astuetz.PagerSlidingTabStrip;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import io.mazur.warmshowers.R;

public class FragmentHome extends SherlockFragment {
    private PagerSlidingTabStrip viewTabs;
    private ViewPager viewPager;

    public static SherlockFragment newInstance() {
        return new FragmentHome();
    }

    /**
     * Register for EventBus in the constructor.
     */
    public FragmentHome() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home, null);

        this.setInsets(getSherlockActivity(), view);

        return (ViewGroup) view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        this.viewTabs = (PagerSlidingTabStrip) view.findViewById(R.id.viewTabs);
        this.viewPager = (ViewPager) view.findViewById(R.id.viewPager);

        this.viewPager.setAdapter(new ViewPagerAdapter(getChildFragmentManager()));
        this.viewTabs.setViewPager(this.viewPager);

        this.viewTabs.setIndicatorColor(getResources().getColor(R.color.branding_light));
    }

    public static void setInsets(Activity context, View view) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) return;

        SystemBarTintManager tintManager = new SystemBarTintManager(context);
        SystemBarTintManager.SystemBarConfig config = tintManager.getConfig();
        view.setPadding(0, config.getPixelInsetTop(true), config.getPixelInsetRight(), 0);
    }

    public static class ViewPagerAdapter extends FragmentPagerAdapter {
        public ViewPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public Fragment getItem(int position) {
            switch(position) {
                case(0): {
                    return FragmentMap.newInstance();
                }
                case(1): {
                    return FragmentList.newInstance();
                }
                case(2): {
                    return FragmentFavourites.newInstance();
                }
                default: {
                    return FragmentMap.newInstance();
                }
            }
        }

        public CharSequence getPageTitle(int position) {
            if (position == 0) {
                return "Map";
            } else if(position == 1) {
                return "List";
            } else {
                return "Favourites";
            }
        }
    }
}
