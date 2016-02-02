package com.aimer.shd.gank.api;

/**
 * Created by shd on 2016/2/1.
 */
public class Result<T> {
    String error;
    T results;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public T getResults() {
        return results;
    }

    public void setResults(T results) {
        this.results = results;
    }
}
