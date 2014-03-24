package io.mazur.warmshowers.Activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.path.android.jobqueue.JobManager;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import de.greenrobot.event.EventBus;
import io.mazur.warmshowers.Controller.AuthJob;
import io.mazur.warmshowers.Controller.GetUserInfo;
import io.mazur.warmshowers.Controller.LogoutJob;
import io.mazur.warmshowers.Controller.SearchUsers;
import io.mazur.warmshowers.EventBus.AuthEvent;
import io.mazur.warmshowers.Fragment.FragmentFavourites;
import io.mazur.warmshowers.Fragment.FragmentHome;
import io.mazur.warmshowers.Fragment.FragmentList;
import io.mazur.warmshowers.Fragment.FragmentProfile;
import io.mazur.warmshowers.Model.Session;
import io.mazur.warmshowers.Navigation.Drawer;
import io.mazur.warmshowers.R;

public class MainActivity extends SherlockFragmentActivity implements Drawer.NavigationDrawerCallbacks {
    private Drawer drawer;

    private Session session;

    private JobManager jobManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EventBus.getDefault().register(this);

        this.setContentView(R.layout.activity_main);

        // Only set the tint if the device is running KitKat or above
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);

            SystemBarTintManager tintManager = new SystemBarTintManager(this);
            tintManager.setStatusBarTintEnabled(true);

            tintManager.setStatusBarTintColor(getResources().getColor(R.color.branding_light));
        }

        // Setup Navigation Drawer
        drawer = (Drawer) getSupportFragmentManager().findFragmentById(R.id.drawer);
        drawer.setUp(this, R.id.drawer, (DrawerLayout) findViewById(R.id.layout));

        jobManager = new JobManager(this);
        jobManager.addJobInBackground(new AuthJob());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getSupportMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.action_search: {
                break;
            }
            case R.id.action_refresh: {
                break;
            }
        }
        return true;
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment defaultFragment;

        switch(position) {
            case(0): {
                defaultFragment = FragmentHome.newInstance();
                break;
            }
            default: {
                defaultFragment = FragmentProfile.newInstance();
                break;
            }
        }

        fragmentManager.beginTransaction().replace(R.id.container, defaultFragment).commit();
    }

    @SuppressWarnings("UnusedDeclaration")
    public void onEventMainThread(AuthEvent event) {
        // TODO: for now only, should be done with SessionManager
        this.session = event.session;

        Toast.makeText(this, event.session.sessid + "\n" + event.session.session_name, Toast.LENGTH_LONG).show();

        jobManager.addJobInBackground(new SearchUsers(session));
    }
}
