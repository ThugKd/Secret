package com.example.thugkd.secret;

import android.content.Context;
import android.content.SharedPreferences.Editor;

/**
 * Created by thugkd on 9/11/16.
 */
public class Config {

    public static final String KEY_ACTION = "action";
    public static final String KEY_TOKEN = "token";
    public static final String KEY_STATUS = "status";
    public static final String KEY_PHONE_NUM = "phone";
    public static final String ACTION_GET_CODE = "send_pass";


    public static final int RESULT_STATUS_SUCCESS = 1;
    public static final int RESULT_STATUS_FAIL = 0;
    public static final int RESULT_STATUS_INVLID_TOKEN = 2;


    public static final String SERVER_URL = "http://demo.eoeschool.com/api/v1/nimings/io";
    public static final String APP_ID = "com.example.thugkd.secret";
    public static final String CHARSET = "utf-8";


    /**
     * get cached token
     *
     * @param context
     * @return token
     */
    public static String getCachedToken(Context context) {
        return context.getSharedPreferences(APP_ID, Context.MODE_PRIVATE).getString(KEY_TOKEN, null);
    }

    /**
     * cache token
     *
     * @param context
     * @param token
     */
    public static void cacheTolen(Context context, String token) {
        Editor editor = context.getSharedPreferences(APP_ID, Context.MODE_PRIVATE).edit();
        editor.putString(KEY_TOKEN, token);
        editor.commit();
    }
}
