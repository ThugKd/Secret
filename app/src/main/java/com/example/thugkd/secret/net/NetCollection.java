package com.example.thugkd.secret.net;

import android.os.AsyncTask;

import com.example.thugkd.secret.Config;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by thugkd on 9/11/16.
 */
public class NetCollection {
    public NetCollection(final String url, final HttpMethod method, final SuccessCallback successCallback, final FailCallback failCallback, final String... kvs) {
        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... voids) {

                StringBuffer paramsStr = new StringBuffer();

                for (int i = 0; i < voids.length; i += 2) {
                    paramsStr.append(kvs[i]).append("=").append(voids[i + 1]).append("&");
                }
                paramsStr.substring(0, paramsStr.length() - 1);

                try {
                    URLConnection connection = null;
                    switch (method) {
                        case POST:
                            connection = new URL(url).openConnection();
                            connection.setDoOutput(true);
                            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream(), Config.CHARSET));
                            bw.write(paramsStr.toString());
                            break;
                        case GET:
                            connection = new URL(url + "?" + paramsStr.toString()).openConnection();
                            break;
                        default:
                            break;
                    }

                    BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), Config.CHARSET));
                    String line = null;
                    StringBuffer result = new StringBuffer();
                    while ((line = br.readLine()) != null) {
                        result.append(line);
                    }
                    return result.toString();

                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(String result) {
                if (result != null) {
                    if (successCallback != null) {
                        successCallback.onSuccess(result);
                    }
                } else {
                    if (failCallback != null){
                        failCallback.onFail();
                    }
                }
            }
        };
    }

    public static interface SuccessCallback {
        void onSuccess(String result);
    }

    public static interface FailCallback {
        void onFail();
    }
}
