package com.example.studentmeetup.model.webservice;

import com.example.studentmeetup.model.Report;
import com.example.studentmeetup.model.Session;
import com.example.studentmeetup.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Webservice {

    //---User-------

    @GET("getUser.php")
    Call<User> getUser(@Field("id") String id);

    @FormUrlEncoded
    @POST("login.php")
    Call<User> login(@Field("email") String username, @Field("password") String password);

    @POST("register.php")
    Call<User> register(@Body User user);

    //----Session-----

    @GET("getSession.php")
    Call<Session> getSessionById(@Field("id") String id);

    @GET("getSession.php")
    Call<Session> getSessionByTag(@Field("tag") String tag);

    @POST("addUser.php")
    Call<Boolean> addUser(@Body User user);

    @POST("createSession.php")
    Call<Session> createSession(@Field("tag") String tag);

    @DELETE("deleteSession.php")
    Call<Boolean> deleteSession(@Field("id") String id);

    //----Report------

    @POST("saveReport.php")
    Call<Boolean> saveReport(@Body Report report);

}
