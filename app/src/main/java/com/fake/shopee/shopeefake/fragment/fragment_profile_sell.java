package com.fake.shopee.shopeefake.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fake.shopee.shopeefake.R;

/**
 * Created by Riandy on 12/1/2017.
 */


public class fragment_profile_sell extends Fragment{
    public static int page=1;
    public void oncreate(Bundle state){
        super.onCreate(state);

        final Bundle args = getArguments();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView =(ViewGroup) inflater.inflate(
                        R.layout.fragment_beli_jual, container, false);
        return rootView;
    }
}