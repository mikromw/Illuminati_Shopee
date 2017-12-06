package com.fake.shopee.shopeefake.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.fake.shopee.shopeefake.R;
import com.fake.shopee.shopeefake.camera_test;
import com.fake.shopee.shopeefake.main_notification;

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
        Button a = (Button) rootView.findViewById(R.id.buttonjual);
        a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final CharSequence[] item = {"Kamera","Foto"};
                AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
                dialog.setTitle("Select");
                dialog.setItems(item,new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int position) {
                        Intent a = new Intent(getActivity(), camera_test.class);
                        startActivity(a);
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
        return rootView;
    }
}
