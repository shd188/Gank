package com.aimer.shd.gank.api;

/**
 * Created by shd on 2016/2/2.
 */
public interface Api {

//    void getAndroidGanks(int page,RequestCallbackListener listener);
//    void getIOSGanks(int page,RequestCallbackListener listener);
//    void getResourceGanks(int page,RequestCallbackListener listener);
//    void getRecommendGanks(int page,RequestCallbackListener listener);

    void getGanks(String type,int page,RequestCallbackListener listener);



}
