package io.mazur.warmshowers.Networking;

import java.util.List;

import io.mazur.warmshowers.Model.Search;
import io.mazur.warmshowers.Model.Session;
import io.mazur.warmshowers.Model.User;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.Query;

public interface Rest {
    public static final String SERVER = "https://www.warmshowers.org/";

    public static class Users {
        public List<User> users;
    }

    @FormUrlEncoded
    @POST("/services/rest/user/login")
    Session login(@Field("username") String username, @Field("password") String password);

    @POST("/services/rest/user/logout")
    String logout();

    @POST("/services/rest/message/unreadCount")
    int unreadCount();

    @GET("/user/{uid}/json")
    Users getUserInfo(@Path("uid") String uid);

//    POST /services/rest/hosts/by_location
//    Accept: application/json
//    Cookie: <session_name>=<sessid>  (obtained from login)`
//
//    Post parameters: minlat maxlat minlon maxlon centerlat centerlon limit
//
//    Search for Users by Location

//    @FormUrlEncoded
    @POST("/services/rest/hosts/by_location")
    Search searchByLocation(
            @Query("minlat") String minlat,
            @Query("maxlat") String maxlat,
            @Query("minlon") String minlon,
            @Query("maxlon") String maxlon,
            @Query("centerlat") String centerlat,
            @Query("centerlon") String centerlon,
            @Query("limit") String limit
    );
}
