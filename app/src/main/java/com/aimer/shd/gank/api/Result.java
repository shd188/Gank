package com.aimer.shd.gank.api;

import com.aimer.shd.gank.model.Gank;

import java.util.List;

/**
 * Created by shd on 2016/2/1.
 */
public class Result {
    boolean error;
    List<Gank> results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<Gank> getResults() {
        return results;
    }

    public void setResults(List<Gank> results) {
        this.results = results;
    }
}
