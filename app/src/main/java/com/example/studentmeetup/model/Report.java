package com.example.studentmeetup.model;


public class Report {
    private int id;
    private String idReportee;
    private String idReported;
    private Reason reason;
    private String date;
    private String time;
    private String reason_desc;

    private Report(Builder builder){
        this.id = builder.id;
        this.idReportee = builder.idReportee;
        this.idReported = builder.idReported;
        this.reason = builder.reason;
        this.date = builder.date;
        this.time = builder.time;
        this.reason_desc = builder.reason_desc;

    }


    public String getIdReportee() {
        return idReportee;
    }

    public String getIdReported() {
        return idReported;
    }

    public Reason getReason() {
        return reason;
    }

    public String getDate() {
        return date;
    }

    public String getTime(){ return time; }


    public static class Builder{

        private int id;
        private String idReportee;
        private String idReported;
        private Reason reason;
        private String date;
        private String time;
        private String reason_desc;

        public Builder(String idReported, String idReportee, int id){
            this.idReported = idReported;
            this.idReportee = idReportee;
            this.id = id;
            this.date = "";
            this.time = "";
            this.reason = Reason.Reason1;
            this.reason_desc = "";
            this.id = id;
        }

        public Builder setReason(Reason reason){
            this.reason = reason;
            return this;
        }

        public Builder setReasonDescription(String description){
            this.reason_desc = description;
            return this;
        }

        public Builder setDate(String date){
            this.date = date;
            return this;
        }

        public Builder setTime(String time){
            this.time = time;
            return this;
        }

        public Report build(){

            return new Report(this);
        }



    }
}
