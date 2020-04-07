package com.example.studentmeetup.model.repository;

public class DatabaseConstants {

    public static final String ROOT_URL = "http://127.0.0.1/Android/v1/";
    public static final String URL_REGISTER = ROOT_URL + "registerUser.php";
    public static final String URL_LOGIN = ROOT_URL + "loginUser.php";
    public static final String URL_CREATE_SESSION = ROOT_URL + "createSession.php";
    public static final String URL_DELETE_SESSION = ROOT_URL + "deleteSession.php";
    public static final String URL_SAVE_REPORT = ROOT_URL + "saveReport.php";
    public static final String URL_UPDATE_USER = ROOT_URL + "updateUser.php";
    public static final String URL_DELETE_USER = ROOT_URL + "deleteUser.php";

    public static final String COLUMN_SESSIONS_CREATED = "sessions_created";
    public static final String COLUMN_SESSIONS_JOINED = "sessions_joined";
    public static final String COLUMN_DATE = "date";
    public static final String COLUMN_TIME = "time";
    public static final String COLUMN_REASON = "reason";
    public static final String COLUMN_ID_REPORTED = "id_reported";
    public static final String COLUMN_ID_REPORTEE = "id_reportee";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_SESSION_SIZE = "session_size";

}
