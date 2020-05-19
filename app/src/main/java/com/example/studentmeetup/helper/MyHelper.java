package com.example.studentmeetup.helper;

import android.content.Context;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import cn.pedant.SweetAlert.SweetAlertDialog;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MyHelper {

    public static LiveData<Boolean> confirmDialog(Context context, int warningType, String title, String content, String confirmText){

        final LiveData<Boolean> result = new MutableLiveData<>();

        new SweetAlertDialog(context, warningType)
                .setTitleText(title)
                .setContentText(content)
                .setConfirmText(confirmText)
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                         ((MutableLiveData<Boolean>) result).setValue(true);
                    }
                })
                .setCancelButton("Cancel", new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        sDialog.dismissWithAnimation();
                    }
                })
                .show();

        return result;
    }

    public static boolean isPassComplex(String pass){

        Pattern pattern;
        Matcher matcher;
        String pass_pattern ="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$";
        pattern = Pattern.compile(pass_pattern);
        matcher = pattern.matcher(pass);
        return matcher.matches();

    }

//    public static String md5(String input){
//
//
//
//        try {
//            // Create MD5 Hash
//            MessageDigest digest = java.security.MessageDigest.getInstance("MD5");
//            digest.update(input.getBytes());
//            byte messageDigest[] = digest.digest();
//
//            // Create Hex String
//            StringBuffer hexString = new StringBuffer();
//            for (int i=0; i<messageDigest.length; i++)
//                hexString.append(Integer.toHexString(0xFF & messageDigest[i]));
//
//            return hexString.toString();
//
//        }catch (NoSuchAlgorithmException e) {
//                e.printStackTrace();
//        }
//        return "";
//    }

    public static String md5(String in){
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("MD5");
            digest.reset();
            digest.update(in.getBytes());
            byte[] a = digest.digest();
            int len = a.length;
            StringBuilder sb = new StringBuilder(len << 1);
            for (int i = 0; i < len; i++) {
                sb.append(Character.forDigit((a[i] & 0xf0) >> 4, 16));
                sb.append(Character.forDigit(a[i] & 0x0f, 16));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) { e.printStackTrace(); }
        return null;
    }

}


