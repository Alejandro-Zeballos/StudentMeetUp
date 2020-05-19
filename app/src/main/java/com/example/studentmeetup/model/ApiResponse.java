package com.example.studentmeetup.model;

public class ApiResponse {

    private boolean isSuccessful;
    private String error;
    private String message;

    public ApiResponse(boolean isSuccessful, String error, String message){
        this.isSuccessful = isSuccessful;
        this.error = error;
        this.message = message;
    }

    public boolean isSuccessful() {
        return isSuccessful;
    }

    public void setSuccessful(boolean successful) {
        isSuccessful = successful;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString(){
        return "Message: "+message+" ,Error: "+error+", successful: "+isSuccessful;
    }
}
