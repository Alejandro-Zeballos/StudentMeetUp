package com.example.studentmeetup.viewmodel;

import android.util.Log;

import com.example.studentmeetup.model.User;
import com.example.studentmeetup.model.repository.UsersRepository;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ViewModelLogin extends ViewModel {

    String userId;
    private MutableLiveData<User> user;
    private UsersRepository repository;

    public ViewModelLogin(){
        repository = UsersRepository.getInstance();
    }

    public LiveData<User> login(String username, String password){

        user = repository.login(username, password);

        return user;
    }

    public LiveData<User> getUser() {

        return user;
    }

}
