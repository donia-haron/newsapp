package com.example.anrdoid.lastapp;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

public class loader extends AsyncTaskLoader<List<News>> {

    private String mUrl;

    public loader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<News> loadInBackground() {
        if (mUrl == null) {
            return null;
        }

        List<News> newws = QueryUtils.fetchNews(mUrl);
        return newws;
    }
}

