package com.example.studentmeetup.model.repository;

import android.content.Context;
import android.util.Log;

import com.example.studentmeetup.model.Report;
import com.example.studentmeetup.model.User;
import com.example.studentmeetup.model.webservice.Webservice;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UsersRepository {

    private Webservice webservice = new Retrofit.Builder()
                            .baseUrl("https://jsonplaceholder.typicode.com/")
                            .addConverterFactory(GsonConverterFactory.create())
                            .build()
                            .create(Webservice.class);
            ;

//    public User getUser();
//    public void registerUser(User user, Context context);
//    public void editUser(User user, Context context);
//    public void reportUser(Report form, Context context);
//    public void deleteUser(User user, Context context);

    public LiveData<User> getUser(String userId){

        final MutableLiveData<User> data = new MutableLiveData<>();

        webservice.getUser(userId).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });

        return data;

    }

    public LiveData<User> login(String username, String password){

        final MutableLiveData<User> data = new MutableLiveData<>();

        webservice.login(username, password).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                data.setValue(response.body());
                Log.i("API response:", response.message() );
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });

        return data;

    }

    public LiveData<User> register(User user){

        final MutableLiveData<User> data = new MutableLiveData<>();

        webservice.register(user).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                data.setValue(response.body());
                Log.i("API response:", response.message() );
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });

        return data;

    }

}
