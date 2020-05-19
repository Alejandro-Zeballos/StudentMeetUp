package com.example.studentmeetup.model;


public class Report {
    private int idReportee;
    private int idReported;
    private Reason reason;
    private String date;
    private String reason_desc;

    private Report(Builder builder){

        this.idReportee = builder.idReportee;
        this.idReported = builder.idReported;
        this.reason = builder.reason;
        this.date = builder.date;
        this.reason_desc = builder.reason_desc;

    }

    @Override
    public String toString(){
        return "ReporteeID: "+idReportee+" ReportedID: "+idReported+ " Reason: "+reason+ " Descr: "+reason_desc;
    }

    public int getIdReportee() {
        return idReportee;
    }

    public int getIdReported() {
        return idReported;
    }

    public Reason getReason() {
        return reason;
    }

    public String getReason_desc(){ return reason_desc;}

    public String getDate() {
        return date;
    }


    public static class Builder{


        private int idReportee;
        private int idReported;
        private Reason reason;
        private String date;
        private String reason_desc;

        public Builder(int idReportee, int idReported){
            this.idReported = idReported;
            this.idReportee = idReportee;

            this.date = "";
            this.reason = Reason.other;
            this.reason_desc = "";

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

        public Report build(){

            return new Report(this);
        }



    }
}
