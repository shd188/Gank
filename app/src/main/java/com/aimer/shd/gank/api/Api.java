package com.aimer.shd.gank.api;

/**
 * Created by shd on 2016/2/2.
 */
public interface Api {

    void getGanks(String type, int page, RequestCallbackListener listener);

}
