package com.example.studentmeetup.model.webservice;

import com.example.studentmeetup.model.ApiResponse;
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

    @FormUrlEncoded
    @GET("getUser.php")
    Call<User> getUser(@Field("id") String id);

    @FormUrlEncoded
    @POST("login.php")
    Call<User> login(@Field("email") String username, @Field("password") String password);

    @FormUrlEncoded
    @POST("registerUser.php")
    Call<ApiResponse> register(@Field("firstName") String firstName,
                               @Field("nickname") String nickname,
                               @Field("registerEmail") String email,
                               @Field("registerPassword") String password,
                               @Field("repeatPassword") String repeatPass,
                               @Field("description") String description,
                               @Field("course") String course);

    //----Session-----



//    @FormUrlEncoded
//    @POST("getSession.php")
//    Call<List<Session>> getSessionsByTag(@Field("tag") String tag);

    @GET("getSessionsById.php")
    Call<List<Session>> getSessionsById(@Query("id") int id);

    @GET("getSession.php")
    Call<List<Session>> getSessionsByTag(@Query("tags") String tag);

    @FormUrlEncoded
    @POST("addUser.php")
    Call<ApiResponse> addUserToSession(@Field("userId") String userId, @Field("sessionId") String sessionId);

    @FormUrlEncoded
    @POST("createSession.php")
    Call<ApiResponse> createSession(@Field("title") String title,
                                    @Field("course") String course,
                                    @Field("date") String date,
                                    @Field("time") String time,
                                    @Field("location") String location,
                                    @Field("description") String description,
                                    @Field("adminId") int adminId,
                                    @Field("adminName") String adminName,
                                    @Field("tags") String tags);

    @FormUrlEncoded
    @POST("deleteSession.php")
    Call<ApiResponse> deleteSession(@Field("sessionId") int sessionId);


    //----Report------

    @POST("saveReport.php")
    Call<ApiResponse> saveReport(@Body Report report);

}
