package com.example.jun.travelreminder.helper;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.jun.travelreminder.fitur.output.OutputFragment;

public class SimplePagerAdapter extends FragmentPagerAdapter {
    final int PAGE_COUNT = 2;
    private String[] title = new String[]{"OUTPUT", "INPUT"};

    public SimplePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return OutputFragment.newInstance();
//            case 1: return InputFragment.newInstance();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return title[position];
    }
}
