package com.example.studentmeetup.model.repository;

import android.util.Log;

import com.example.studentmeetup.model.ApiResponse;
import com.example.studentmeetup.model.Session;
import com.example.studentmeetup.model.User;
import com.example.studentmeetup.model.webservice.Webservice;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SessionRepository {

    private String TAG = "SessionRepository** ";
    private Webservice webservice;
    private static SessionRepository instance;


    private SessionRepository() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        webservice = new Retrofit.Builder()
                .baseUrl(DatabaseConstants.ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
                .create(Webservice.class);
    }

    public static SessionRepository getInstance() {
        if (instance == null) {
            instance = new SessionRepository();
        }
        return instance;
    }

    public MutableLiveData<List<Session>> getSessionsByTag(String tag) {

        final MutableLiveData<List<Session>> sessionList = new MutableLiveData<>();

        webservice.getSessionsByTag(tag).enqueue(new Callback<List<Session>>() {
            @Override
            public void onResponse(Call<List<Session>> call, Response<List<Session>> response) {
                if(response.isSuccessful()){
                    Log.i(TAG, "onResponse successful called inside getSessionsByTg");

                    sessionList.setValue(response.body());
                }else {
                    Log.i(TAG, "onResponse called not sucesful inside getSessionsByTg");
                }

            }

            @Override
            public void onFailure(Call<List<Session>> call, Throwable t) {
                Log.i(TAG, "onFailure called inside getSessionsByTg");
                Log.i(TAG, "onFailure called " + t.getMessage());
            }
        });

        return sessionList;

    }

    public MutableLiveData<List<Session>> getSessionListById(int adminId) {

        final MutableLiveData<List<Session>> sessionList = new MutableLiveData<>();

        webservice.getSessionsById(adminId).enqueue(new Callback<List<Session>>() {
            @Override
            public void onResponse(Call<List<Session>> call, Response<List<Session>> response) {
                if(response.isSuccessful()){
                    Log.i(TAG, "onResponse successful called inside getSessionsByTg");

                    sessionList.setValue(response.body());
                }else {
                    Log.i(TAG, "onResponse called not sucesful inside getSessionsByTg");
                }

            }

            @Override
            public void onFailure(Call<List<Session>> call, Throwable t) {
                Log.i(TAG, "onFailure called inside getSessionsByTg");
                Log.i(TAG, "onFailure called " + t.getMessage());
            }
        });

        return sessionList;

    }

    public MutableLiveData<ApiResponse> deleteSession(int sessionId){
        final MutableLiveData<ApiResponse> apiResponseMutableLiveData = new MutableLiveData<>();
        webservice.deleteSession(sessionId).enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                apiResponseMutableLiveData.setValue(response.body());
                Log.i(TAG, "onResponse called inside Delete Session");
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                Log.i(TAG, "onFailure called inside create Session");
                apiResponseMutableLiveData.setValue(new ApiResponse(false,t.getMessage(), "Session not deleted"));
            }
        });

        return apiResponseMutableLiveData;
    }

    public MutableLiveData<ApiResponse> createSession(Session session){
        final MutableLiveData<ApiResponse> apiResponseMutableLiveData = new MutableLiveData<>();
        webservice.createSession(session.getTitle(),
                    session.getCourse(),
                    session.getDate(),
                    session.getTime(),
                    session.getLocation(),
                    session.getDescription(),
                    session.getAdminId(),
                    session.getAdminName(),
                    session.getTags())
                .enqueue(new Callback<ApiResponse>() {

            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                apiResponseMutableLiveData.setValue(response.body());
                Log.i(TAG, "onResponse called inside create Session");
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                Log.i(TAG, "onFailure called inside create Session");
                apiResponseMutableLiveData.setValue(new ApiResponse(false,t.getMessage(), "Session not created"));
            }
        });

        return apiResponseMutableLiveData;
    }

    public MutableLiveData<ApiResponse> updateSession(Session session){
        final MutableLiveData<ApiResponse> apiResponseMutableLiveData = new MutableLiveData<>();
        webservice.updateSession(
                session.getSessionId(),
                session.getTitle(),
                session.getDate(),
                session.getTime(),
                session.getLocation(),
                session.getDescription(),
                session.getTags())
                .enqueue(new Callback<ApiResponse>() {

                    @Override
                    public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                        apiResponseMutableLiveData.setValue(response.body());
                        Log.i(TAG, "onResponse called inside update Session");
                    }

                    @Override
                    public void onFailure(Call<ApiResponse> call, Throwable t) {
                        Log.i(TAG, "onFailure called inside update Session");
                        Log.i(TAG, t.getMessage());
                        apiResponseMutableLiveData.setValue(new ApiResponse(false,t.getMessage(), "Session not created"));
                    }
                });

        return apiResponseMutableLiveData;
    }


}
