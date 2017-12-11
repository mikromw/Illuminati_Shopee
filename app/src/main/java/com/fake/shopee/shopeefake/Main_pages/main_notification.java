package com.fake.shopee.shopeefake.Main_pages;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.fake.shopee.shopeefake.R;
import com.fake.shopee.shopeefake.camera_test;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class main_notification extends Activity {

    ImageButton mainhome,maintimeline,maincamera,mainnotif,mainprofile;
    private FirebaseAuth mAuth;

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    private void updateUI(FirebaseUser currentUser) {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_notification);

        mAuth = FirebaseAuth.getInstance();

        mainhome = (ImageButton) findViewById(R.id.notifmainmenu);
        maintimeline = (ImageButton) findViewById(R.id.notiftimeline);
        maincamera = (ImageButton) findViewById(R.id.notifcamera);
        mainnotif = (ImageButton) findViewById(R.id.notifnotif);
        mainprofile = (ImageButton) findViewById(R.id.notifprofile);

        mainhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(main_notification.this , MainActivity.class);
                startActivity(i);
                finish();
            }
        });

        maintimeline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(main_notification.this , main_timeline.class);
                startActivity(i);
                finish();
            }
        });
        maincamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final CharSequence[] item = {"Kamera","Foto"};
                AlertDialog.Builder dialog = new AlertDialog.Builder(main_notification.this);
                dialog.setTitle("Select");
                dialog.setItems(item,new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int position) {
                        Intent a = new Intent(main_notification.this, camera_test.class);
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
                Intent i = new Intent(main_notification.this , main_notification.class);
                startActivity(i);
                finish();
            }
        });
        mainprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(main_notification.this , main_profile.class);
                startActivity(i);
                finish();
            }
        });
    }
}