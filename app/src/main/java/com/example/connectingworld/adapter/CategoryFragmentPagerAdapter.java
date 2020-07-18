package com.example.connectingworld.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import android.content.Context;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.connectingworld.R;
import com.example.connectingworld.fragment.ArtandDesignFragment;
import com.example.connectingworld.fragment.BusinessFragment;
import com.example.connectingworld.fragment.CultureFragment;
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

/**
 * Provides the appropriate {@link Fragment} for a view pager.
 */

public class CategoryFragmentPagerAdapter extends FragmentPagerAdapter {

    /** Context of the app */
    private Context mContext;

    /**
     * Create a new {@link CategoryFragmentPagerAdapter} object.
     *
     * @param context is the context of the app
     * @param fm is the fragment manager that will keep each fragment's state in the adapter
     * across swipes.
     */
    public CategoryFragmentPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    /**
     * Return the {@link Fragment} that should be displayed for the given page number.
     */
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
            case Constants.CULTURE:
                return new CultureFragment();
            case Constants.FOOD:
                return new FoodFragment();
            case Constants.ARTANDDESIGN:
                return new ArtandDesignFragment();
            default:
                return null;
        }
    }

    /**
     * Return the total number of pages.
     */
    @Override
    public int getCount() {
        return 13;
    }

    /**
     * Return page title of the tap
     */
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
            case Constants.CULTURE:
                titleResId = R.string.culture;
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