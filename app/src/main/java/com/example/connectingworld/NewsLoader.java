package com.example.connectingworld;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.connectingworld.utils.QueryUtils;

import java.util.List;

public class NewsLoader extends AsyncTaskLoader<List<Result>> {
    private static final String TAG = "NewsLoader";
    private String mURL;
    private Context context;
    RequestQueue requestQueue= Volley.newRequestQueue(getContext());

    public NewsLoader(@NonNull Context context,String url) {
        super(context);
        this.context=context;
        mURL=url;
    }


    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Nullable
    @Override
    public List<Result> loadInBackground() {
        if (mURL == null) {
            return null;
        }
        // Perform the network request, parse the response, and extract a list of news.
        List<Result> newsData = QueryUtils.fetchNewsData(context,mURL);
        return newsData;






    }
}
