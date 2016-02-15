package com.aimer.shd.gank.api;

import android.os.AsyncTask;

import com.aimer.shd.gank.net.OkHttpUtil;
import com.google.gson.Gson;

import java.io.IOException;

/**
 * Created by shd on 2016/2/14.
 */
public class ApiImp implements Api {

    private final OkHttpUtil mOkHttpUtil;
    private Gson gson;

    public ApiImp() {
        mOkHttpUtil = OkHttpUtil.getInstance();
        gson = new Gson();
    }

    @Override
    public void getGanks(final String type, final int page, final RequestCallbackListener listener) {
        new AsyncTask<Void, Void, Result>() {
            @Override
            protected Result doInBackground(Void... params) {
                try {

                    return mOkHttpUtil.get(type, page);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Result result) {
                if (listener != null && result != null) {
                    if (!result.isError()) {
                        listener.onSuccess(result.getResults());
                    } else {
                        listener.onFailure();
                    }
                }
            }
        }.execute();
    }
}
