
package com.example.connectingworld.utils;

import android.content.Context;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.connectingworld.Result;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class QueryUtils {
    private static final String LOG_TAG = QueryUtils.class.getSimpleName();

    private QueryUtils() {
    }



    public static List<Result> fetchNewsData(Context context,String requestUrl) {
        // Create URL object
        URL url = createUrl(requestUrl);

        // Perform HTTP request to the URL and receive a JSON response back
        String jsonResponse = null;
        jsonResponse = makeHttpRequest(url);

        // Extract relevant fields from the JSON response and create a list of {@link News}
        List<Result> newsList = extractFeatureFromJSON(jsonResponse);

        // Return the list of {@link News}
        return newsList;
    }

    private static List<Result> extractFeatureFromJSON(String jsonResponse) {
        List<Result>data=null;
        return data;
    }

    private static String makeHttpRequest(URL url) {
        return "";
    }


    private static URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Problem building the URL.", e);
        }
        return url;
    }
}