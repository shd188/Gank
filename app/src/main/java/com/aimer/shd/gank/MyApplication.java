package com.aimer.shd.gank;

import android.app.Application;
import android.content.Context;

import com.orhanobut.logger.AndroidLogTool;
import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;


/**
 * Created by shd on 2016/1/25.
 */
public class MyApplication extends Application {

    private static final String TAG="Gank";
    public static Context mContext;
    @Override
    public void onCreate() {
        super.onCreate();
        mContext=this.getApplicationContext();
        Logger.init(TAG)                 // default PRETTYLOGGER or use just init()
                .methodCount(3)                 // default 2
                .hideThreadInfo()               // default shown
                .logLevel(LogLevel.FULL)        // default LogLevel.FULL
                .methodOffset(2)                // default 0
                .logTool(new AndroidLogTool()); // custom log tool, optional
    }
}
