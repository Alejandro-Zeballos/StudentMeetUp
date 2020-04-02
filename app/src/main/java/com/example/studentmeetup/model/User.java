package com.example.studentmeetup.model;

//User class is created and ready to be used, it implements a builder User.Builder

public class User {

    private String mName;
    private String mNickName;
    private Course mCourse;
    private String mDescription;
    private String mEmail;
    private String mPassword;
    private int mSessionsCreated;
    private int mSessionsJoined;


    private User(Builder builder){
        this.mName = builder.mName;
        this.mNickName = builder.mNickname;
        this.mCourse = builder.mCourse;
        this.mDescription = builder.mDescription;
        this.mEmail = builder.mEmail;
        this.mPassword = builder.mPassword;
    }


    public String getName() {
        return mName;
    }

    public String getNickName() {
        return mNickName;
    }

    public Course getCourse() {
        return mCourse;
    }

    public String getDescription() {
        return mDescription;
    }

    public String getEmail() {
        return mEmail;
    }

    public String getPassword() {
        return mPassword;
    }

    public int getSessionsCreated() {
        return mSessionsCreated;
    }

    public int getSessionsJoined() {
        return mSessionsJoined;
    }

    public void addSessionCreated(){
        this.mSessionsCreated++;
    }

    public void addSessionJoined(){
        this.mSessionsJoined++;
    }

    public static class Builder{
        private String mName;
        private String mNickname;
        private Course mCourse;
        private String mDescription;
        private String mEmail;
        private String mPassword;
        private int mSessionsCreated;
        private int mSessionsJoined;

        public Builder(String email, String nickname, String password, Course course){

            this.mEmail = email;
            this.mCourse = course;
            this.mPassword = password;
            this.mNickname = nickname;
            this.mDescription = "";
            this.mName = "";
            this.mSessionsCreated = 0;
            this.mSessionsJoined = 0;

        }

        public Builder setDescription(String description){

            this.mDescription = description;
            return this;
        }

        public Builder setName(String name){

            this.mName = name;
            return this;

        }

        public User build(){
            return new User(this);
        }



    }
}
