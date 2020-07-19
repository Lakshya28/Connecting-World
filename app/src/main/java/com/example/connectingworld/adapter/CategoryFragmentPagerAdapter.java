package com.example.connectingworld.adapter;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.connectingworld.R;
import com.example.connectingworld.fragment.ArtandDesignFragment;
import com.example.connectingworld.fragment.BusinessFragment;
import com.example.connectingworld.fragment.EnvironmentFragment;
import com.example.connectingworld.fragment.FashionFragment;
import com.example.connectingworld.fragment.FoodFragment;
import com.example.connectingworld.fragment.HomeFragment;
import com.example.connectingworld.fragment.ScienceFragment;
import com.example.connectingworld.fragment.SocietyFragment;
import com.example.connectingworld.fragment.SportFragment;
import com.example.connectingworld.fragment.TechnologyFragment;
import com.example.connectingworld.fragment.TravelFragment;
import com.example.connectingworld.fragment.WorldFragment;
import com.example.connectingworld.utils.Constants;

public class CategoryFragmentPagerAdapter extends FragmentStatePagerAdapter {

    private Context mContext;

    public CategoryFragmentPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case Constants.HOME:
                return new HomeFragment();
            case Constants.WORLD:
                return new WorldFragment();
            case Constants.SCIENCE:
                return new ScienceFragment();
            case Constants.TECHNOLOGY:
                return new TechnologyFragment();
            case Constants.TRAVEL:
                return new TravelFragment();
            case Constants.SPORT:
                return new SportFragment();
            case Constants.ENVIRONMENT:
                return new EnvironmentFragment();
            case Constants.SOCIETY:
                return new SocietyFragment();
            case Constants.FASHION:
                return new FashionFragment();
            case Constants.BUSINESS:
                return new BusinessFragment();
            case Constants.FOOD:
                return new FoodFragment();
            default:
                return new ArtandDesignFragment();
        }
    }

    @Override
    public int getCount() {
        return 12;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        int titleResId;
        switch (position) {
            case Constants.HOME:
                titleResId = R.string.ic_title_home;
                break;
            case Constants.WORLD:
                titleResId = R.string.ic_title_world;
                break;
            case Constants.SCIENCE:
                titleResId = R.string.ic_title_science;
                break;
            case Constants.TECHNOLOGY:
                titleResId = R.string.ic_title_technology;
                break;
            case Constants.TRAVEL:
                titleResId = R.string.ic_title_travel;
                break;
            case Constants.SPORT:
                titleResId = R.string.ic_title_sport;
                break;
            case Constants.ENVIRONMENT:
                titleResId = R.string.ic_title_environment;
                break;
            case Constants.SOCIETY:
                titleResId = R.string.ic_title_society;
                break;
            case Constants.FASHION:
                titleResId = R.string.ic_title_fashion;
                break;
            case Constants.BUSINESS:
                titleResId = R.string.ic_title_business;
                break;
            case Constants.FOOD:
                titleResId = R.string.ic_title_food;
                break;
            default:
                titleResId = R.string.ic_title_art_and_design;
                break;
        }
        return mContext.getString(titleResId);
    }
}