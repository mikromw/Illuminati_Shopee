package com.fake.shopee.shopeefake.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.fake.shopee.shopeefake.R;
import com.fake.shopee.shopeefake.Main_pages.main_belanjaanku;
import com.fake.shopee.shopeefake.session_class;

/**
 * Created by Riandy on 12/1/2017.
 */


public class fragment_profile extends Fragment{
    public static int page=1;
    session_class session;
    public void oncreate(Bundle state){
        super.onCreate(state);

        final Bundle args = getArguments();
    }
    LinearLayout belanjaanku,koinshopee,dompetshopee,terakhirdilihat;
    ImageButton belumbayar,dikemas,dikirim,pengembalian,smallbutton;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                        R.layout.row_profile1, container, false);
        belanjaanku = (LinearLayout) rootView.findViewById(R.id.cart);
        belumbayar = (ImageButton) rootView.findViewById(R.id.belumbayar);
        dikemas = (ImageButton) rootView.findViewById(R.id.dikemas);
        dikirim = (ImageButton) rootView.findViewById(R.id.dikirim);
        pengembalian =(ImageButton) rootView.findViewById(R.id.pengembalian);


        belanjaanku.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a = new Intent(getActivity(),main_belanjaanku.class);
                startActivity(a);

            }
        });

        belumbayar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a = new Intent(getActivity(),main_belanjaanku.class);
                session.currentactivity="0";
                startActivity(a);

            }
        });
        dikemas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a = new Intent(getActivity(),main_belanjaanku.class);
                session.currentactivity="1";
                startActivity(a);

            }
        });
        dikirim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a = new Intent(getActivity(),main_belanjaanku.class);
                session.currentactivity="2";
                startActivity(a);
            }
        });
        pengembalian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a = new Intent(getActivity(),main_belanjaanku.class);
                session.currentactivity="5";
                startActivity(a);
            }
        });



        return rootView;
    }
}
