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

    private Webservice webservice;
    private static UsersRepository instance;

    private UsersRepository(){
        webservice = new Retrofit.Builder()
                .baseUrl(DatabaseConstants.ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(Webservice.class);
    }

    public static UsersRepository getInstance(){
        if(instance == null){
            instance = new UsersRepository();
        }
        return instance;
    }

    public MutableLiveData<User> getUser(String userId){

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

    public MutableLiveData<User> login(String username, String password){

        final MutableLiveData<User> user = new MutableLiveData<>();
        Log.i("EN USERREPOSITORY", "adentro del metodo");



        webservice.login(username, password).enqueue(new Callback<User>() {

            @Override
            public void onResponse(Call<User> call, Response<User> response) {

                user.setValue(response.body());
                Log.i("User repository:", user.getValue().toString() );

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.i("API failure:", t.getMessage() );
            }
        });
        //Log.i("Sending data", "Back to ModelVIew" );
       // Log.i("Data Sent:", data.getValue().toString() );
        return user;

    }

    public MutableLiveData<User> register(User user){

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
