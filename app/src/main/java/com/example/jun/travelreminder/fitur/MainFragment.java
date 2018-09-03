package com.example.jun.travelreminder.fitur;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jun.travelreminder.R;
import com.example.jun.travelreminder.helper.SimplePagerAdapter;

public class MainFragment extends Fragment {
    private final static String TAG_FRAGMENT = "fragment";

    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fragment, container, false);
        ViewPager vpMain = (ViewPager) view.findViewById(R.id.vpMain);
        TabLayout tabMain = (TabLayout) view.findViewById(R.id.tabMain);
        tabMain.setupWithViewPager(vpMain);

        vpMain.setAdapter(buildAdapter());


        return view;
    }

    private PagerAdapter buildAdapter() {
        return (new SimplePagerAdapter(getChildFragmentManager()));
    }

}
