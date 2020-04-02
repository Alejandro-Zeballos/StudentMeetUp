package com.example.studentmeetup.model;


public class Report {
    private String idReportee;
    private String idReported;
    private Reason reason;
    private String date;
    private String time;
    private String reason_desc;

    private Report(Builder builder){
        this.idReportee = builder.idReportee;
        this.idReported = builder.idReported;
        this.reason = builder.reason;
        this.date = builder.date;
        this.time = builder.time;
        this.reason_desc = builder.reason_desc;

    }

    public String idReportee() {
        return idReportee;
    }

    public String idReported() {
        return idReported;
    }

    public Reason getReason() {
        return reason;
    }

    public String getDate() {
        return date;
    }

    public static class Builder{

        private String idReportee;
        private String idReported;
        private Reason reason;
        private String date;
        private String time;
        private String reason_desc;

        public Builder(String idReported, String idReportee){
            this.idReported = idReported;
            this.idReportee = idReportee;
            this.date = DateFormater.getDate();
            this.time = DateFormater.getTime();
            this.reason = Reason.Reason1;
            this.reason_desc = "";
        }

        public Report build(){
            return new Report(this);
        }


    }
}
