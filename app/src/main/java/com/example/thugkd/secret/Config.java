package com.example.thugkd.secret;

import android.content.Context;
import android.content.SharedPreferences.Editor;

/**
 * Created by thugkd on 9/11/16.
 */
public class Config {
    public static final String KEY_TOKEN = "token";
    public static final String APP_ID = "com.example.thugkd.secret";

    /**
     * get cached token
     * @param context
     * @return token
     */
    public static String getCachedToken(Context context) {
        return context.getSharedPreferences(APP_ID, Context.MODE_PRIVATE).getString(KEY_TOKEN,null);
    }

    /**
     * cache token
     * @param context
     * @param token
     */
    public static void cacheTolen(Context context,String token){
        Editor editor = context.getSharedPreferences(APP_ID,Context.MODE_PRIVATE).edit();
         editor.putString(KEY_TOKEN,token);
        editor.commit();
    }
}
