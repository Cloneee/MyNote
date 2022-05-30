package com.example.mynote.models;

import org.json.JSONObject;

public class VerifyOtpParams {

    public String email;
    public String otp;

    public VerifyOtpParams(String email, String otp){
        this.email = email;
        this.otp = otp;
    }

    public VerifyOtpParams(){
        this( "" , "");
    }

    public JSONObject toJson(){
        try{
            JSONObject jObject = new JSONObject();

            jObject.put("email", email);
            jObject.put("otp", otp);

            return jObject;

        }catch (Exception e){
            return  null;
        }
    }
}
