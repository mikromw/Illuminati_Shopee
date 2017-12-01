package com.fake.shopee.shopeefake;

import android.app.Activity;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;

import java.util.ArrayList;

public class main_profile extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_profile);

        ImageButton mainhome,maintimeline,maincamera,mainnotif,mainprofile;

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
                ArrayList<String> item = new ArrayList<String>();
                item.add("Kamera");
                item.add("Foto");
                item.add("Instagram");
                AlertDialog.Builder alert = new AlertDialog.Builder(main_profile.this);
                alert.setTitle("Tambah produk melalui");
                final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(main_profile.this,
                        android.R.layout.activity_list_item,
                        item );
                alert.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                alert.setNegativeButton("No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
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
}
