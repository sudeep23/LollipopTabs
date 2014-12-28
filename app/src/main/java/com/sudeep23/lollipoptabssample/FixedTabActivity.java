package com.sudeep23.lollipoptabssample;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sudeep23.lollipoptabs.fixedtab.FixedTabLayout;


public class FixedTabActivity extends ActionBarActivity {

    Toolbar toolbar;
    FixedTabLayout mFixedTabLayout;
    ViewPager mViewPager;
    MyViewPagerAdapter viewPagerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fixed_tab);

        setUpToolbar();
        initialiseAllViews();
        setUpViewPager();
        setUpSlidingTab();
    }

    private void setUpSlidingTab() {
        mFixedTabLayout.setViewPager(mViewPager, viewPagerAdapter.getCount());
        mFixedTabLayout.setSelectedIndicatorColors(getResources().getColor(android.R.color.white));

    }

    private void setUpViewPager() {
        viewPagerAdapter = new MyViewPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(viewPagerAdapter);
    }

    private void initialiseAllViews() {
        mFixedTabLayout = (FixedTabLayout) findViewById(R.id.fixed_tab);
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
    }

    private void setUpToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Fixed Tabs");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private class MyViewPagerAdapter extends FragmentPagerAdapter {

        String[] viewPagerPages = {"Home", "News", "Events"};

        public MyViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return MyFragment.newInstance(viewPagerPages[position]);
        }

        @Override
        public int getCount() {
            return viewPagerPages.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return viewPagerPages[position];
        }
    }

    public static class MyFragment extends Fragment {

        public static MyFragment newInstance(String title) {
            MyFragment myFragment = new MyFragment();
            Bundle bundle = new Bundle();
            bundle.putString(Intent.EXTRA_TEXT, title);
            myFragment.setArguments(bundle);
            return myFragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            TextView textView = new TextView(getActivity());
            textView.setGravity(Gravity.CENTER);
            textView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            textView.setText(getArguments().getString(Intent.EXTRA_TEXT));
            return textView;
        }
    }

}
