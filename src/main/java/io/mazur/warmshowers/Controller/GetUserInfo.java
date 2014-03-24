package io.mazur.warmshowers.Controller;

import android.util.Log;

import com.path.android.jobqueue.Job;
import com.path.android.jobqueue.Params;

import java.util.concurrent.atomic.AtomicInteger;

import io.mazur.warmshowers.Model.Session;
import io.mazur.warmshowers.Model.User;
import io.mazur.warmshowers.Networking.Rest;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.RetrofitError;

public class GetUserInfo extends Job {
    private static final AtomicInteger jobCounter = new AtomicInteger(0);

    private final int id;

    private final long uid;

    private final Session session;

    public GetUserInfo(Session session, long uid) {
        super(new Params(Priority.HIGH).requireNetwork());

        this.id = jobCounter.incrementAndGet();
        this.session = session;
        this.uid = uid;
    }

    @Override
    public void onAdded() {
        Log.d("GetUserInfo", "onAdded");
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

        Rest.Users data = null;

        try {
            data = rest.getUserInfo("39373");
        } catch (Exception e) {
            e.printStackTrace();
        }

        for(User user : data.users) {
            Log.d("Get user, Fullname: ", user.fullname);
        }
    }

    @Override
    protected void onCancel() {
        Log.d("GetUserInfo", "onCancel");
    }

    @Override
    protected boolean shouldReRunOnThrowable(Throwable throwable) {
        Log.d("GetUserInfo", throwable.getMessage());
        return false;
    }
}
