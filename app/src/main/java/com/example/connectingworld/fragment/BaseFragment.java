package com.example.connectingworld.fragment;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager.widget.ViewPager;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.connectingworld.News;
import com.example.connectingworld.NewsLoader;
import com.example.connectingworld.NewsPreferences;
import com.example.connectingworld.R;
import com.example.connectingworld.Result;
import com.example.connectingworld.adapter.NewsAdapter;
import com.example.connectingworld.utils.Constants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.List;

public class BaseFragment extends Fragment {
    private static final String TAG = "ScienceFragment";
    private RecyclerView recyclerView;
    private List<Result> articles = new ArrayList<>();
    private NewsAdapter mAdapter;
    private static final String URl = "https://content.guardianapis.com/search?";
    private static final String orderby = "&order-by=newest";
    private static final String key = "&api-key=14f1fe0f-2a5b-47f1-ac16-f4e590fc9e3b&show-fields=thumbnail";
    private String perfectURL;
    String keyword = "section=", section = "";
    private RequestQueue requestQueue;
    private int page = 1;
    int currentitem, totalitem, scrolledoutitems;
    boolean isScrolling = false;
    int visiblethreshold = 10;
    ViewPager viewPager;
    private View mLoadingIndicator;
    private SwipeRefreshLayout mSwipeRefreshLayout;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = view.findViewById(R.id.recyclerview);
        viewPager = view.findViewById(R.id.viewpager);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        mSwipeRefreshLayout = view.findViewById(R.id.swipe_refresh);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        page = 1;
                        articles.clear();
                        getData();
                        mSwipeRefreshLayout.setRefreshing(false);
                        Toast.makeText(getActivity(), " Updated just now ", Toast.LENGTH_SHORT).show();

                    }
                }, 3000);
            }
        });

        // Find the loading indicator from the layout
        mLoadingIndicator = view.findViewById(R.id.loading_indicator);

        // Find the empty view from the layout and set it on the new recycler view
        //recyclerView.setEmptyView(mEmptyStateTextView);

        // Create a new adapter that takes an empty list of news as input
        mAdapter = new NewsAdapter(getActivity(), new ArrayList<Result>());

        // Set the adapter on the {@link recyclerView}
        recyclerView.setAdapter(mAdapter);
        //recyclerView.addOnScrollListener();

        // Check for network connectivity and initialize the loader
        //initializeLoader(isConnected());
        recyclerView.addOnScrollListener(prOnScrollListener);
        requestQueue = Volley.newRequestQueue(getContext());
        getData();

        return view;
    }

    private RecyclerView.OnScrollListener prOnScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
        }

        @Override
        public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);

            if (islastitemDisplaying(recyclerView)) {
                mLoadingIndicator.setVisibility(View.VISIBLE);
                getData();
            }

        }
    };

    private boolean islastitemDisplaying(RecyclerView recyclerView) {
        if (recyclerView.getAdapter().getItemCount() != 0) {
            int lastVisiblePosition = ((LinearLayoutManager) recyclerView.getLayoutManager()).findLastCompletelyVisibleItemPosition();
            if (lastVisiblePosition != RecyclerView.NO_POSITION && lastVisiblePosition == recyclerView.getAdapter().getItemCount() - 1) {
                return true;
            }
        }
        return false;
    }

    private void getData() {
        perfectURL = URl + keyword + getSectionName() + orderby + "&page=" + page + key;
        requestQueue.add(getDatafromapi());
        page++;
    }

    private StringRequest getDatafromapi() {
        final StringRequest request = new StringRequest(perfectURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("Code", response);
                //parseData(response);
                GsonBuilder gsonBuilder = new GsonBuilder();
                Gson gson = gsonBuilder.create();
                News results = gson.fromJson(response, News.class);
                List<Result> temparticles = results.getResponse().getResults();
                articles.addAll(temparticles);
                Log.d("U R L     ", perfectURL);
                //articles=results.getResponse().getResults();
                mAdapter.addAll(temparticles);
                mLoadingIndicator.setVisibility(View.INVISIBLE);

                //news.setAdapter(new programingadapter(MainActivity.this,articles));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), "Wrong", Toast.LENGTH_SHORT).show();
            }
        });

        return request;
    }

    public String getSectionName() {
        /*int position = viewPager.getCurrentItem();
        Log.d("LakPosition", "" + position);
        switch (position) {
            case Constants.WORLD: {
                Log.d("Position", "" + position + " && " + Constants.WORLD);
                return "world";
            }
            case Constants.SCIENCE:
                return "science";
            case Constants.TECHNOLOGY:
                return "technology";
            case Constants.TRAVEL:
                return "travel";
            case Constants.SPORT:
                return "sport";
            case Constants.ENVIRONMENT:
                return "environment";
            case Constants.SOCIETY:
                return "society";
            case Constants.FASHION:
                return "fashion";
            case Constants.BUSINESS:
                return "business";
            case Constants.CULTURE:
                return "culture";
            case Constants.FOOD:
                return "food";
            case Constants.ARTANDDESIGN:
                return "artanddesign";
            default:
                return "";
        }*/
        return  "";
    }
}
