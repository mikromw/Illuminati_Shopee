package com.fake.shopee.shopeefake.Admin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.fake.shopee.shopeefake.R;

public class AdminActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        ImageView setDiskon = (ImageView) findViewById(R.id.ivDiskonAdmin);

        setDiskon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent setDiskonIntent = new Intent(AdminActivity.this, DiskonDashboardActivity.class);
                startActivity(setDiskonIntent);
            }
        });

    }
}
