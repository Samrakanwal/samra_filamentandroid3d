package com.reviling.androidfilament3d.parser;

import android.content.Context;


import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import org.json.JSONException;
import org.json.JSONObject;

public class ResponseParser {
    public enum CALLS {
        LOGIN, SIGNUP, FORGOTPASS, CHANGEPASS, APPSETTINGS, GETCATEGORY, SUBSCRIPTION, GETPRODUCT, GETFAVPRODUCT, GETNEARBYUSER, TRANSACTION, RECOMMENDED, NOTIFICATION
    }

    public static void parse(Context context, CALLS call, JSONObject jsonObject, JSONObject payload) throws JSONException {
        switch (call) {
            case LOGIN:

                JsonParser parser = new JsonParser();
                JsonElement mJson = parser.parse(payload.toString());
                Gson gson = new Gson();
//                AppObj object = gson.fromJson(mJson, AppObj.class);
//                object.setLogin(true);
//                object.setObj_id("1");


                break;
            default:
                break;
        }
    }
}