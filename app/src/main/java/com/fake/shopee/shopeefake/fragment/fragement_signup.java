package com.fake.shopee.shopeefake.fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fake.shopee.shopeefake.R;

/**
 * Created by Riandy on 12/10/2017.
 */

public class fragement_signup extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_signup, container, false);

        return rootView;
    }
}
