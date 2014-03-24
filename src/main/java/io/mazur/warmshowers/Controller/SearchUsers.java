package io.mazur.warmshowers.Controller;

import android.util.Log;

import com.path.android.jobqueue.Job;
import com.path.android.jobqueue.Params;

import java.util.concurrent.atomic.AtomicInteger;

import io.mazur.warmshowers.Model.Search;
import io.mazur.warmshowers.Model.Session;
import io.mazur.warmshowers.Model.User;
import io.mazur.warmshowers.Networking.Rest;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.RetrofitError;

public class SearchUsers extends Job {
    private static final AtomicInteger jobCounter = new AtomicInteger(0);

    private final int id;
    private final Session session;

    public SearchUsers(Session session) {
        super(new Params(Priority.HIGH).requireNetwork());

        this.id = jobCounter.incrementAndGet();
        this.session = session;
    }

    @Override
    public void onAdded() {
        Log.d("SearchUsers", "onAdded");
    }

    @Override
    public void onRun() throws Throwable {
        if (this.id != jobCounter.get()) {
            return;
        }

        RequestInterceptor requestInterceptor = new RequestInterceptor() {
            @Override
            public void intercept(RequestInterceptor.RequestFacade request) {
                request.addHeader("Set-Cookie", session.session_name + "=" + session.sessid);
            }
        };

        //     @Headers("Set-Cookie: SESSca3ec806b9aee9140beb6c03142b4638=ll7268bbjfjsg1qlpn8jq32n95")

        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(Rest.SERVER)
                .setRequestInterceptor(requestInterceptor)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();

        Rest rest = adapter.create(Rest.class);

        Search data = null;

        Log.d("Set-Cookie", session.session_name + "=" + session.sessid);

        try {
            data = rest.searchByLocation("0", "1000", "0", "1000", "500", "500", "10");
        } catch (Exception e) {
            e.printStackTrace();
        }

        Log.d("Search", data.status.delivered);
        Log.d("Search", data.status.totalresults);
    }

    @Override
    protected void onCancel() {
        Log.d("SearchUsers", "onCancel");
    }

    @Override
    protected boolean shouldReRunOnThrowable(Throwable throwable) {
        Log.d("SearchUsers", throwable.getMessage());
        return false;
    }
}
