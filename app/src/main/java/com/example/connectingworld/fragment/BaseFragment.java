package com.example.connectingworld.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
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
import com.example.connectingworld.NewsPreferences;
import com.example.connectingworld.R;
import com.example.connectingworld.Result;
import com.example.connectingworld.adapter.NewsAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import static com.example.connectingworld.NewsPreferences.getPreferredUrl;

public class BaseFragment extends Fragment {
    private RecyclerView recyclerView;
    private List<Result> articles = new ArrayList<>();
    private NewsAdapter mAdapter;
    private String perfectURL;
    private RequestQueue requestQueue;
    private int page = 1;
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
                        Toast.makeText(getActivity(), " Updated just now", Toast.LENGTH_SHORT).show();

                    }
                }, 3000);
            }
        });

        mLoadingIndicator = view.findViewById(R.id.loading_indicator);

        mAdapter = new NewsAdapter(getActivity(), new ArrayList<Result>());

        recyclerView.setAdapter(mAdapter);
        recyclerView.addOnScrollListener(OnScrollListener);
        requestQueue = Volley.newRequestQueue(getContext());
        getData();

        return view;
    }

    private RecyclerView.OnScrollListener OnScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
        }

        @Override
        public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);

            if (isLastItemDisplaying(recyclerView)) {
                mLoadingIndicator.setVisibility(View.VISIBLE);
                getData();
            }

        }
    };

    private boolean isLastItemDisplaying(RecyclerView recyclerView) {
        if (recyclerView.getAdapter().getItemCount() != 0) {
            int lastVisiblePosition = ((LinearLayoutManager) recyclerView.getLayoutManager()).findLastCompletelyVisibleItemPosition();
            if (lastVisiblePosition != RecyclerView.NO_POSITION && lastVisiblePosition == recyclerView.getAdapter().getItemCount() - 1) {
                return true;
            }
        }
        return false;
    }

    private void getData() {
        perfectURL = NewsPreferences.getPreferredUrl(getContext(), getSectionName());
        requestQueue.add(getDatafromapi());
        page++;
    }

    private StringRequest getDatafromapi() {
        final StringRequest request = new StringRequest(perfectURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                GsonBuilder gsonBuilder = new GsonBuilder();
                Gson gson = gsonBuilder.create();
                News results = gson.fromJson(response, News.class);
                List<Result> tempArticles = results.getResponse().getResults();
                mAdapter.addAll(tempArticles);
                mLoadingIndicator.setVisibility(View.INVISIBLE);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), "Network Not Available, Try Again", Toast.LENGTH_SHORT).show();
            }
        });
        return request;
    }

    public String getSectionName() {
        return "";
    }
}
