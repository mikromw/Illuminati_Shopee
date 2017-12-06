package com.fake.shopee.shopeefake;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.widget.Toast;


import com.fake.shopee.shopeefake.fragment.fragment_profile;
import com.fake.shopee.shopeefake.fragment.fragment_profile_sell;

import java.util.ArrayList;
import java.util.List;

public class main_profile extends FragmentActivity {

    private static final int NUM_PAGES = 2;

    /**
     * The pager widget, which handles animation and allows swiping horizontally to access previous
     * and next wizard steps.
     */
    private ViewPager mPager;

    /**
     * The pager adapter, which provides the pages to the view pager widget.
     */
    private ScreenSlidePagerAdapter mPagerAdapter;
    int a=0;
    ImageButton mainhome,maintimeline,maincamera,mainnotif,mainprofile;
    TabLayout tabLayout;

    Bundle bundle=new Bundle();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_profile);


        initpager();

        mainhome = (ImageButton) findViewById(R.id.profilemainmenu);
        maintimeline = (ImageButton) findViewById(R.id.profiletimeline);
        maincamera = (ImageButton) findViewById(R.id.profilecamera);
        mainnotif = (ImageButton) findViewById(R.id.profilenotif);
        mainprofile = (ImageButton) findViewById(R.id.profileprofile);



        mainhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(main_profile.this , MainActivity.class);
                startActivity(i);
                finish();
            }
        });

        maintimeline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(main_profile.this , main_timeline.class);
                startActivity(i);
                finish();
            }
        });
        maincamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final CharSequence[] item = {"Kamera","Foto"};
                AlertDialog.Builder dialog = new AlertDialog.Builder(main_profile.this);
                dialog.setTitle("Select");
                dialog.setItems(item,new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int position) {
                        Intent a = new Intent(main_profile.this, camera_test.class);
                        startActivity(a);
                        Toast.makeText(getApplicationContext(),"selected Item:"+position, Toast.LENGTH_SHORT).show();
                    }

                });
                dialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        dialog.dismiss();
                    }
                });
                AlertDialog alert = dialog.create();
                alert.show();
            }
        });
        mainnotif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(main_profile.this , main_notification.class);
                startActivity(i);
                finish();
            }
        });
        mainprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(main_profile.this , main_profile.class);
                startActivity(i);
                finish();
            }
        });
    }

    private void initpager(){
        mPager = (ViewPager) findViewById(R.id.pager);
        mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager(),bundle);
        fragment_profile.page=1;
        mPagerAdapter.addfragment(new fragment_profile(),"Beli");
        mPagerAdapter.addfragment(new fragment_profile(),"jual");
        mPager.setOffscreenPageLimit(2);
        mPager.setAdapter(mPagerAdapter);

        tabLayout = findViewById(R.id.tabsprofile);
        tabLayout.setupWithViewPager(mPager);

    }

    private class ScreenSlidePagerAdapter extends FragmentPagerAdapter {
        private final Bundle fragmentbundle;
        private final List<String> mfragmenttitlelsit = new ArrayList<String>();
        private final List<Fragment> mfragment = new ArrayList<Fragment>();

        public ScreenSlidePagerAdapter(FragmentManager fm,Bundle data) {
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
                title = "Beli";
            }
            else if (position == 1)
            {
                title = "Jual";
            }

            return title;
        }


        @Override
        public Fragment getItem(int position) {
            Fragment fragment = null;
            if (position == 0)
            {
                fragment =  new fragment_profile();
            }
            else if (position == 1)
            {
                fragment = new fragment_profile_sell();
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
            return NUM_PAGES;
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
