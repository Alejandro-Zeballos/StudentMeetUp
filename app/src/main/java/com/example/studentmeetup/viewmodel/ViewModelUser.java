package com.example.studentmeetup.viewmodel;

import android.util.Log;

import com.example.studentmeetup.model.ApiResponse;
import com.example.studentmeetup.model.Report;
import com.example.studentmeetup.model.User;
import com.example.studentmeetup.model.repository.UsersRepository;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ViewModelUser extends ViewModel {

    private MutableLiveData<ApiResponse> apiResponse;
    private UsersRepository repository;
    private MutableLiveData<User> user;
    //userProfile user will only be used in the profile section
    private MutableLiveData<List<User>> userProfiles;
    private User profileSelected;

    public ViewModelUser(){
        repository = UsersRepository.getInstance();
    }

    public LiveData<ApiResponse> register(String email,
                                          String name,
                                          String nickname,
                                          String password,
                                          String repeatPass,
                                          String description,
                                          String course){

        apiResponse = repository.register(email, name, nickname, password, repeatPass, description, course);

        return apiResponse;
    }
    public LiveData<User> login(String username, String password){

        user = repository.login(username, password);
        Log.i("ViewModelUser", "setting up user");
        return user;
    }

    public LiveData<List<User>> searchUser(String nickname){

        userProfiles = repository.searchUser(nickname);

        return userProfiles;
    }

    public LiveData<ApiResponse> reportUser(Report report){

        apiResponse = repository.reportUser(report);
        return apiResponse;
    }

    public LiveData<ApiResponse> deleteAccount(User user){

        apiResponse = repository.deleteAccount(user);
        return apiResponse;
    }

    public LiveData<ApiResponse> editUserProfile(User newUser){

        apiResponse = repository.editUserProfile(newUser);
        return apiResponse;
    }

    public LiveData<User> getUser() {
        Log.i("ViewModelUser", "recovering user" + user.toString());
        return user;
    }

    public void setProfileSelected(User profile){
        this.profileSelected = profile;
    }


    public User getProfileUser(){
        return profileSelected;
    }
}
