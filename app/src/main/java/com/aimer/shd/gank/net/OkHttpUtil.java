package com.aimer.shd.gank.net;

import android.util.Log;

import com.aimer.shd.gank.api.Result;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by shd on 2016/1/15.
 */
public class OkHttpUtil {
    private static OkHttpUtil mInstance;
    private OkHttpClient mOkHttpClient;
    public static final String URL = "http://gank.avosapps.com/api/data/";
    //参数类型
    private static final MediaType MEDIA_TYPE_PNG = MediaType.parse("image/png");
//    final MediaType MEDIA_TYPE_PNG=MediaType.parse(AppConstant.arrImages.get(i).getMediaType());

    public OkHttpUtil() {
        mOkHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build();
    }

    public static OkHttpUtil getInstance() {
        if (mInstance == null) {
            mInstance = new OkHttpUtil();
        }
        return mInstance;
    }

    public Result get(String type, int page) throws IOException {
        Request request = new Request.Builder()
                .url(URL + type + "/10/" + page)
                .build();
        Call call = mOkHttpClient.newCall(request);
        Response execute = call.execute();
        Gson gson = new Gson();
        String result = execute.body().string();
        return gson.fromJson(result, new TypeToken<Result>() {
        }.getType());
    }

    public <T> T post(Map<String, Object> map, Type type) throws IOException {

        Request request = buildPostRequest(map);
        Response execute = mOkHttpClient.newCall(request).execute();
        if (execute.isSuccessful()) {
            String result = execute.body().string();
            Log.d("Aimer", result);
            Gson gson = new Gson();

            return gson.fromJson(result, type);

        } else {
            throw new IOException("Unexpected code " + execute);
        }

    }

    public <T> T postFile(Map<String, Object> map, Type type, String... paths) throws IOException {
        Request request = buildPostFileRequest(map, paths);
        Response response = mOkHttpClient.newCall(request).execute();
        Log.d("Aimer", "xxfile==" + response.body().string());
        if (response.isSuccessful()) {
            String result = response.body().string();
            Log.d("Aimer", "file==" + result);
            Gson gson = new Gson();
            return gson.fromJson(result, type);
        } else {
            throw new IOException("Unexpected code " + response);
        }
    }

    private Request buildPostFileRequest(Map<String, Object> map, String... paths) {
        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
        if (map == null) {
            map = new HashMap<>();
        }
        for (String key : map.keySet()) {
            builder.addFormDataPart(key, String.valueOf(map.get(key)));
        }

        RequestBody requestBody = builder.build();
        return new Request.Builder().url(URL)
                .addHeader("x-yx-app-v", "153")
                .addHeader("x-yx-api-v", "2")
                .addHeader("x-yx-net-t", "")
                .addHeader("x-yx-dev-t", "Android")
                .addHeader("x-yx-dev-imei", "")
                .addHeader("x-yx-dev-model", "")
                .post(requestBody)
                .build();
    }

    private Request buildPostRequest(Map<String, Object> map, String... paths) {

        if (map == null) {
            map = new HashMap<>();
        }

        FormBody.Builder builder = new FormBody.Builder();
        for (String key : map.keySet()) {
            builder.add(key, String.valueOf(map.get(key)));
        }
        RequestBody requestBody = builder.build();
        return new Request.Builder()
                .url(URL)
                .addHeader("x-yx-app-v", "153")
                .addHeader("x-yx-api-v", "2")
                .addHeader("x-yx-net-t", "")
                .addHeader("x-yx-dev-t", "Android")
                .addHeader("x-yx-dev-imei", "")
                .addHeader("x-yx-dev-model", "")
                .post(requestBody)
                .build();

    }
}
