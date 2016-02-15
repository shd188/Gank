package com.aimer.shd.gank.api;

import com.aimer.shd.gank.model.Gank;

import java.util.List;

/**
 * Created by shd on 2016/2/14.
 */

public interface RequestCallbackListener {

    public void onSuccess(List<Gank> ganks);

    public void onFailure();
}
