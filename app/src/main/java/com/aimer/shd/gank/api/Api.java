package com.aimer.shd.gank.api;

/**
 * Created by shd on 2016/2/2.
 */
public interface Api {

    void getAndroidGanks(int page);
    void getIOSGanks(int page);
    void getResourceGanks(int page);
    void getRecommendGanks(int page);
}
