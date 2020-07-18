package com.example.connectingworld.fragment;

import android.util.Log;

import androidx.fragment.app.Fragment;

public class CultureFragment extends Fragment {
    public String getSectionName()
    {
        Log.d("LakCultureFragment","Called");
        String section="culture";
        return section;
    }

}
