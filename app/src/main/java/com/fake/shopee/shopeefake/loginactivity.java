package com.fake.shopee.shopeefake;


import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.fake.shopee.shopeefake.fragment.fragement_signup;
import com.fake.shopee.shopeefake.fragment.fragment_login;
import com.fake.shopee.shopeefake.fragment.fragment_profile;
import com.fake.shopee.shopeefake.fragment.fragment_profile_sell;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.nio.BufferUnderflowException;
import java.util.ArrayList;
import java.util.List;

public class loginactivity extends FragmentActivity {

    private FirebaseAuth mAuth;
    ViewPager mPager;
    Bundle bundle;
    session_class session;
    TabLayout tabLayout;
    private ScreenSlidePagerAdapter mPagerAdapter;

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    private void updateUI(FirebaseUser currentUser) {
        session = new session_class(this);
        if (currentUser==null){
            session.setusename("");
        }
        else
        {
            session.setusename(currentUser.getDisplayName());
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginactivity);

        mAuth = FirebaseAuth.getInstance();

        bundle=new Bundle();
        mPager = (ViewPager) findViewById(R.id.pagerlogin);
        mPagerAdapter = new loginactivity.ScreenSlidePagerAdapter(getSupportFragmentManager(),bundle);
        mPagerAdapter.addfragment(new fragment_login(),"Beli");
        mPagerAdapter.addfragment(new fragement_signup(),"jual");
        mPager.setOffscreenPageLimit(2);
        mPager.setAdapter(mPagerAdapter);

        tabLayout = (TabLayout) findViewById(R.id.tablogin);
        tabLayout.setupWithViewPager(mPager);


    }
    private class ScreenSlidePagerAdapter extends FragmentPagerAdapter {
        private final Bundle fragmentbundle;
        private final List<String> mfragmenttitlelsit = new ArrayList<String>();
        private final List<Fragment> mfragment = new ArrayList<Fragment>();

        public ScreenSlidePagerAdapter(FragmentManager fm, Bundle data) {
            super(fm);
            fragmentbundle = data;
        }
        public void addfragment(Fragment fragment, String title){
            mfragment.add(fragment);
            mfragmenttitlelsit.add(title);
        }
        @Override
        public CharSequence getPageTitle(int position){
            String title = null;
            if (position == 0)
            {
                title = "Login";
            }
            else if (position == 1)
            {
                title = "Sign up";
            }

            return title;
        }


        @Override
        public Fragment getItem(int position) {
            Fragment fragment = null;
            if (position == 0)
            {
                fragment =  new fragment_login();
            }
            else if (position == 1)
            {
                fragment = new fragement_signup();
            }
            return fragment;
        }

        @Override
        public int getItemPosition(Object object){
            return POSITION_NONE;
        }

        @Override
        public long getItemId(int position){
            return System.currentTimeMillis();
        }

        @Override
        public int getCount() {
            return 2;
        }

    }

    public void onBackPressed() {
        if (mPager.getCurrentItem() == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed();
        } else {
            // Otherwise, select the previous step.
            mPager.setCurrentItem(mPager.getCurrentItem() - 1);
        }
    }
}
