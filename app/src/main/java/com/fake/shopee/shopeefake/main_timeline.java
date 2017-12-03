package com.fake.shopee.shopeefake;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;

public class main_timeline extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_timeline);
        ImageButton mainhome,maintimeline,maincamera,mainnotif,mainprofile;

            mainhome = (ImageButton) findViewById(R.id.timemainmenu);
            maintimeline = (ImageButton) findViewById(R.id.timetimeline);
            maincamera = (ImageButton) findViewById(R.id.timecamera);
            mainnotif = (ImageButton) findViewById(R.id.timenotif);
            mainprofile = (ImageButton) findViewById(R.id.timeprofile);

            mainhome.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(main_timeline.this , MainActivity.class);
                    startActivity(i);
                    finish();
                }
            });

            maintimeline.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(main_timeline.this , main_timeline.class);
                    startActivity(i);
                    finish();
                }
            });
            maincamera.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    final CharSequence[] item = {"Kamera","Foto"};
                    AlertDialog.Builder dialog = new AlertDialog.Builder(main_timeline.this);
                    dialog.setTitle("Select");
                    dialog.setItems(item,new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int position) {
                            // TODO Auto-generated method stub
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
                    Intent i = new Intent(main_timeline.this , main_notification.class);
                    startActivity(i);
                    finish();
                }
            });
            mainprofile.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(main_timeline.this , main_profile.class);
                    startActivity(i);
                    finish();
                }
            });
    }
}
