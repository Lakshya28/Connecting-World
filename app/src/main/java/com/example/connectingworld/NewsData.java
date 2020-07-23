package com.example.connectingworld;

public class NewsData {

    private String mId;
    private String mTitle;
    private String mUrl;
    private String mThumbnail;
    private String mSection;
    private String mDate;

    public NewsData(String id, String title, String url, String thumbnail, String section, String date) {
        mId = id;
        mTitle = title;
        mUrl = url;
        mThumbnail = thumbnail;
        mSection = section;
        mDate = date;
    }

    public String getId() {
        return mId;
    }

    public void setId(String mId) {
        this.mId = mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getSection() {
        return mSection;
    }


    public String getDate() {
        return mDate;
    }

    public String getUrl() {
        return mUrl;
    }

    public String getThumbnail() {
        return mThumbnail;
    }

}
