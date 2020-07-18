package com.example.connectingworld.fragment;

import android.util.Log;

import androidx.fragment.app.Fragment;

public class FashionFragment extends BaseFragment {
    public String getSectionName()
    {
        Log.d("LakFashionFragment","Called");
        String section="fashion";
        return section;
    }
}
