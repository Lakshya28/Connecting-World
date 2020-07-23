package com.example.connectingworld;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.example.connectingworld.adapter.NewsAdapter;

import java.util.ArrayList;
import java.util.List;

public class UserFavourites extends AppCompatActivity {
    private RecyclerView recyclerView;
    private NewsAdapter mAdapter;
    private SwipeRefreshLayout mSwipeRefreshLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_favourites);

        Toolbar toolbar = findViewById(R.id.fav_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.ic_title_fav);

        recyclerView = findViewById(R.id.fav_recyclerview);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        mSwipeRefreshLayout = findViewById(R.id.fav_swipe_refresh);

        mAdapter = new NewsAdapter(this, new ArrayList<NewsData>());

        recyclerView.setAdapter(mAdapter);
        final DataBaseHelper db = new DataBaseHelper(this);
        List<NewsData> favArticles = db.getNewsList();
        mAdapter.addAll(favArticles);

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mAdapter.clearAll();
                        mAdapter.addAll(db.getNewsList());
                        mSwipeRefreshLayout.setRefreshing(false);
                        Toast.makeText(UserFavourites.this, "Updated Just Now", Toast.LENGTH_SHORT).show();

                    }
                }, 1000);
            }
        });
    }


}