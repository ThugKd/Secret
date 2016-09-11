package com.example.thugkd.secret;

import com.example.thugkd.secret.net.HttpMethod;
import com.example.thugkd.secret.net.NetCollection;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by thugkd on 9/11/16.
 */
public class GetCode {
    public GetCode(String phone, final SuccessCallback successCallback, final FailCallback failCallback) {
        new NetCollection(Config.SERVER_URL, HttpMethod.POST, new NetCollection.SuccessCallback() {
            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject json = new JSONObject(result);
                    switch (json.getInt(Config.KEY_STATUS)) {
                        case Config.RESULT_STATUS_SUCCESS:
                            if (successCallback != null) {
                                successCallback.onSuccess();
                            }
                            break;
                        case Config.RESULT_STATUS_FAIL:
                            if (failCallback != null) {
                                failCallback.onFail();
                            }
                            break;
                    }

                } catch (JSONException e) {
                    e.printStackTrace();

                    if (failCallback != null) {
                        failCallback.onFail();
                    }
                }
            }
        }, new NetCollection.FailCallback() {
            @Override
            public void onFail() {
                if (failCallback != null) {
                    failCallback.onFail();
                }
            }
        }, Config.KEY_ACTION, Config.ACTION_GET_CODE, Config.KEY_PHONE_NUM, phone);
    }

    public static interface SuccessCallback {
        void onSuccess();
    }

    public static interface FailCallback {
        void onFail();
    }
}
