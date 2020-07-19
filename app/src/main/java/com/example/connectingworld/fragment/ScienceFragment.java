package com.example.connectingworld.fragment;

public class ScienceFragment extends BaseFragment {
    public int page = 1;

    public Integer getPageNumber(boolean isRefreshed) {
        if (isRefreshed) page = 1;
        int temp = page;
        page++;
        return temp;
    }

    public String getSectionName() {
        String section = "science";
        return section;
    }

}