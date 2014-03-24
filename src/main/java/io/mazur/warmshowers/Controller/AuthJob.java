package io.mazur.warmshowers.Controller;

import android.util.Log;

import com.path.android.jobqueue.Job;
import com.path.android.jobqueue.Params;

import java.util.concurrent.atomic.AtomicInteger;

import de.greenrobot.event.EventBus;
import io.mazur.warmshowers.EventBus.AuthEvent;
import io.mazur.warmshowers.Model.Session;
import io.mazur.warmshowers.Networking.Rest;
import retrofit.RestAdapter;
import retrofit.RetrofitError;

public class AuthJob extends Job {
    private static final AtomicInteger jobCounter = new AtomicInteger(0);

    private final int id;

    public AuthJob() {
        super(new Params(Priority.HIGH).requireNetwork());
        this.id = jobCounter.incrementAndGet();
    }

    @Override
    public void onAdded() {
        Log.d("Auth Job", "onAdded");
    }

    @Override
    public void onRun() throws Throwable {
        if (this.id != jobCounter.get()) {
            return;
        }

        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(Rest.SERVER)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();

        Rest rest = adapter.create(Rest.class);

        Session data = null;

        try {
            data = rest.login("mazurkiewicz.damian@mazurlabs.com", "damian15");
        } catch (Exception e) {
            e.printStackTrace();
        }

        // send event to MainActivity that new session was created
        EventBus.getDefault().post(new AuthEvent(data));

        Log.d("Session id:", data.sessid);
        Log.d("Name:", data.user.name);
        Log.d("Fullname:", data.user.fullname);
    }

    @Override
    protected void onCancel() {
        Log.d("Auth Job", "onCancel");
    }

    @Override
    protected boolean shouldReRunOnThrowable(Throwable throwable) {
        Log.d("Auth Job", throwable.getMessage());
        return true;
    }
}
