package io.mazur.warmshowers.Controller;

import android.util.Log;

import com.path.android.jobqueue.Job;
import com.path.android.jobqueue.Params;

import java.util.concurrent.atomic.AtomicInteger;

import io.mazur.warmshowers.Networking.Rest;
import retrofit.RestAdapter;
import retrofit.RetrofitError;

public class LogoutJob extends Job {
    private static final AtomicInteger jobCounter = new AtomicInteger(0);

    private final int id;

    public LogoutJob() {
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

        String data = null;

        try {
            data = rest.logout();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Log.d("Data", data);
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
