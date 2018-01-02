package com.fake.shopee.shopeefake;

import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class main_belanjaanku extends FragmentActivity {

    TabLayout tabLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_belanjaanku);

        tabLayout = (TabLayout) findViewById(R.id.tabbelanja);
        tabLayout.addTab(tabLayout.newTab().setText("Belum bayar"));
        tabLayout.addTab(tabLayout.newTab().setText("Dikemas"));
        tabLayout.addTab(tabLayout.newTab().setText("Dikirim"));
        tabLayout.addTab(tabLayout.newTab().setText("Selesai"));
        tabLayout.addTab(tabLayout.newTab().setText("Batal"));
        tabLayout.addTab(tabLayout.newTab().setText("Pengembalian"));

    }
}
