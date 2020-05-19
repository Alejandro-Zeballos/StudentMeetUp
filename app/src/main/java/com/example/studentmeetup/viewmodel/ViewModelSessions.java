package com.example.studentmeetup.viewmodel;

import android.util.Log;

import com.example.studentmeetup.model.ApiResponse;
import com.example.studentmeetup.model.Session;
import com.example.studentmeetup.model.User;
import com.example.studentmeetup.model.repository.UsersRepository;
import com.example.studentmeetup.model.repository.SessionRepository;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ViewModelSessions extends ViewModel {

    private MutableLiveData<Session> currentSession;
    private String sessionTags="";
    private MutableLiveData<List<Session>> sessionList;
    private SessionRepository sessionRepository;

    public String getSessionTags() {
        return sessionTags;
    }

    public ViewModelSessions(){
        sessionRepository = SessionRepository.getInstance();
    }

    public LiveData<ApiResponse> createSession(Session session){

        MutableLiveData<ApiResponse> response = new MutableLiveData<>();

        response = sessionRepository.createSession(session);

        if(currentSession == null){
            currentSession = new MutableLiveData<>();
        }
        Log.i("VIewModelSessions", "setting current session");
        currentSession.setValue(session);
        Log.i("ViewModelSessions", "current session: "+session.toString());

        return response;
    }

    public LiveData<List<Session>> getSessionListByTag(){

        //if(sessionList == null){
            sessionList = sessionRepository.getSessionsByTag(sessionTags);
       // }

        return sessionList;

    }

    public void setSessionTags(String tags){
        this.sessionTags = tags;
    }

    public LiveData<ApiResponse> updateSession(Session session){

        MutableLiveData<ApiResponse> response = new MutableLiveData<>();

        response = sessionRepository.updateSession(session);

        if(currentSession == null){
            currentSession = new MutableLiveData<>();
        }
        Log.i("ViewModelSessions", "setting current session");
        currentSession.setValue(session);
        Log.i("ViewModelSessions", "current session: "+session.toString());

        return response;
    }

//    public LiveData<List<Session>> getSessionListByTag(String tag){
//
//        //if(sessionList == null){
//        sessionList = sessionRepository.getSessionsByTag(tag);
//        // }
//
//        return sessionList;
//
//    }

    public LiveData<List<Session>> getSessionListById(int adminId){


        sessionList = sessionRepository.getSessionListById(adminId);


        return sessionList;

    }

    public LiveData<ApiResponse> joinSession(User user, Session session){
        MutableLiveData<ApiResponse> response;
        response = sessionRepository.joinSession(user.getId(), session.getSessionId());

        return response;
    }

    public LiveData<ApiResponse> leaveSession(User user, Session session){

        MutableLiveData<ApiResponse> response;

        response = sessionRepository.leaveSession(user.getId(), session.getSessionId());

        return response;
    }

    public LiveData<ApiResponse> deleteSession(Session session){

        MutableLiveData<ApiResponse> response;

        response = sessionRepository.deleteSession(session.getSessionId());

        //currentSession.setValue(null);

        return response;
    }
    //todo implementar join session, search session


    public void setCurrentSession(Session session){
        if(currentSession == null){
            currentSession = new MutableLiveData<>();
        }
        Log.i("VIewModelSessions", "setting current session");
        currentSession.setValue(session);
        Log.i("ViewModelSessions", "current session: "+session.toString());
    }

    public LiveData<Session> getCurrentSession() {

        if(currentSession == null){
            currentSession = new MutableLiveData<>();
        }
        Log.i("getCurrent Session", "recoverng current session");
        Log.i("getCurrent Session", "recoverng current session"+ currentSession.getValue().toString());
        return currentSession;
    }
}
