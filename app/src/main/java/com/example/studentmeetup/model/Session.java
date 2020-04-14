package com.example.studentmeetup.model;

public class Session {

    private int id_session;
    private String course;
    private String session_title;
    private int session_id_admin;
    private String session_admin;
    private String session_date;
    private String session_time;
    private String session_location;

    private String session_tags;
    private String session_descr;

    private Session(Builder builder){
        this.id_session = builder.id;
        this.session_title = builder.title;
        this.course = builder.course;
        this.session_date = builder.date;
        this.session_time = builder.time;
        this.session_location = builder.location;
        this.session_descr = builder.description;
        this.session_id_admin = builder.idAdmin;
        this.session_admin = builder.adminName;
        this.session_tags = builder.tags;
    }

    @Override
    public String toString(){
        return "\n\n"+ "Id: "+ this.id_session + "\n"+
                "title: " + this.session_title + "\n"+
                "course: " + this.course + "\n"+
                "date: " + this.session_date + "\n"+
                "time: " + this.session_time + "\n"+
                "location: " + this.session_location + "\n"+
                "idAdmin: " + this.session_id_admin + "\n"+
                "tags" + this.session_tags + "\n"+
                "description" + this.session_descr + "\n\n";

    }

    public int getSessionId() {
        return id_session;
    }

    public void setId_session(int id_session) {
        this.id_session = id_session;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getTitle() {
        return session_title;
    }

    public void setTitle(String session_title) {
        this.session_title = session_title;
    }

    public int getAdminId() {
        return session_id_admin;
    }

    public void setAdminId(int session_id_admin) {
        this.session_id_admin = session_id_admin;
    }

    public String getAdminName() {
        return session_admin;
    }

    public void setAdminName(String session_admin) {
        this.session_admin = session_admin;
    }

    public String getDate() {
        return session_date;
    }

    public void setDate(String session_date) {
        this.session_date = session_date;
    }

    public String getTime() {
        return session_time;
    }

    public void setTime(String session_time) {
        this.session_time = session_time;
    }

    public String getLocation() {
        return session_location;
    }

    public void setLocation(String session_location) {
        this.session_location = session_location;
    }

    public String getTags() {
        return session_tags;
    }

    public void setTags(String session_tags) {
        this.session_tags = session_tags;
    }

    public String getDescription() {
        return session_descr;
    }

    public void setDescription(String session_descr) {
        this.session_descr = session_descr;
    }

    public static class Builder{


        private int id;
        private String title;
        private String course;
        private String date;
        private String time;
        private String location;
        private String description;
        private int idAdmin;
        private String adminName;
        private String tags;



        public Builder(String title,
                       String course,
                       String date,
                       String time,
                       String location,
                       String adminName){
            this.title = title;
            this.course = course;
            this.date = date;
            this.time = time;
            this.location = location;
            this.id = 0;
            this.idAdmin = 0;
            this.adminName = adminName;
            this.description = "";
            this.tags = title;


        }

        public Builder setTags(String tags){

            this.tags = tags;
            return this;
        }

        public Builder setIdAdmin(int idAdmin){

            this.idAdmin = idAdmin;
            return this;
        }

        public Builder setDescription(String description){

            this.description = description;
            return this;
        }

        public Builder setAdminName(String adminName){
            this.adminName = adminName;
            return this;
        }



        public Session build(){
            return new Session(this);
        }


    }

}
