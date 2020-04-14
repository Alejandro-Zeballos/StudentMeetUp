package com.example.studentmeetup.model;

//User class is created and ready to be used, it implements a builder User.Builder

public class User {

    private int id_student;
    private String student_firstName;
    private String student_nickname;
    private Course student_course;
    private String student_description;
    private String student_email;
    private String student_password;
    private int sessions_joined;
    private int sessions_created;


    private User(Builder builder){
        this.id_student = builder.mId;
        this.student_firstName = builder.mName;
        this.student_nickname = builder.mNickname;
        this.student_course = builder.mCourse;
        this.student_description = builder.mDescription;
        this.student_email = builder.mEmail;
        this.student_password = builder.mPassword;
    }

    @Override
    public String toString(){
        return "\n\n"+ "Id: "+ this.id_student + "\n"+
                "Email: " + this.student_email + "\n"+
                "Password: " + this.student_password + "\n"+
                "Name: " + this.student_firstName + "\n"+
                "Nickname: " + this.student_nickname + "\n"+
                "Course: " + this.student_course + "\n"+
                "Description: " + this.student_description + "\n"+
                "Sessions_joined" + this.sessions_joined + "\n"+
                "Sessions_created" + this.sessions_created + "\n\n";

    }


    public String getName() {
        return student_firstName;
    }

    public String getNickName() {
        return student_nickname;
    }

    public Course getCourse() {
        return student_course;
    }

    public String getDescription() {
        return student_description;
    }

    public String getEmail() {
        return student_email;
    }

    public String getPassword() {
        return student_password;
    }

    public int getId(){ return id_student; }

    public int getSessionsCreated() {
        return sessions_created;
    }

    public int getSessionsJoined() {
        return sessions_joined;
    }

    public void addSessionCreated(){
        this.sessions_created++;
    }

    public void addSessionJoined(){
        this.sessions_joined++;
    }

    public static class Builder{
        private int mId;
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
            this.mId = 0;
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

        public Builder setId(int id){

            this.mId = id;
            return this;
        }

        public User build(){
            return new User(this);
        }



    }
}
