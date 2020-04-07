package com.example.studentmeetup.model;

public class Session {

    private int id;
    private String title;
    private Course course;
    private String date;
    private String time;
    private String location;
    private int sessionSize;
    private String description;

    private Session(Builder builder){
        this.id = builder.id;
        this.title = builder.title;
        this.course = builder.course;
        this.date = builder.date;
        this.time = builder.time;
        this.location = builder.location;
        this.sessionSize = builder.sessionSize;
        this.description = builder.description;
    }

    public String getTitle() {
        return title;
    }

    public Course getCourse() {
        return course;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getLocation() {
        return location;
    }

    public int getSessionSize() {
        return sessionSize;
    }

    public String getDescription() {
        return description;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void addParticipant() {
        this.sessionSize++;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static class Builder{

        private int id;
        private String title;
        private Course course;
        private String date;
        private String time;
        private String location;
        private int sessionSize;
        private String description;

        public Session Builder(String title, Course course, String date, String time, String location){
            this.title = title;
            this.course = course;
            this.date = date;
            this.time = time;
            this.location = location;
            this.id = 0;
            this.description = "";
            this.sessionSize = 1;

            return new Session(this);
        }

        public Builder setId(int id){

            this.id = id;
            return this;
        }

        public Builder setDescription(String description){

            this.description = description;
            return this;
        }

        public Session build(){
            return new Session(this);
        }


    }

}
