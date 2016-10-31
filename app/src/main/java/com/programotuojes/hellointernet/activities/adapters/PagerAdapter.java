package com.programotuojes.hellointernet.activities.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.programotuojes.hellointernet.activities.ArchiveFragment;
import com.programotuojes.hellointernet.activities.LatestFragment;

public class PagerAdapter extends FragmentPagerAdapter {

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new LatestFragment();
            case 1:
                return new ArchiveFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    public PagerAdapter(FragmentManager fm) {
        super(fm);
    }
}
