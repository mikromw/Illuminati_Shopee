package com.fake.shopee.shopeefake.ProductSearch;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.fake.shopee.shopeefake.R;
import com.fake.shopee.shopeefake.SQLclass;
import com.fake.shopee.shopeefake.upload.camera_test;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.pddstudio.urlshortener.URLShortener;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.xml.transform.Result;

public class searchresult extends AppCompatActivity {

    private RecyclerView recyclerView;
    private String passedkey;
    Button closeback ;
    EditText searchbox;
    ImageButton searchback;
    SQLclass sqlclass;

    private resultadapter mAdapter;
    private List<result> movieList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchresult);

        Bundle extras = getIntent().getExtras();
        if(extras !=null) {
            passedkey = extras.getString("KEY","");
        }


         sqlclass = new SQLclass();

        closeback = (Button) findViewById(R.id.resultbackback);
        searchbox = (EditText) findViewById(R.id.resultsearchbox);
        searchback = (ImageButton) findViewById(R.id.resultback);

        searchbox.setText(passedkey);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        searchbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a = new Intent(searchresult.this ,searching.class);
                a.putExtra("KEY",searchbox.getText().toString());
                startActivity(a);
                finish();
            }
        });
        searchback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        closeback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        mAdapter = new resultadapter(movieList);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        YourAsyncTask a = new YourAsyncTask(this);
        a.execute();

    }
    private void prepareMovieData() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ResultSet result = sqlclass.querydata("select * from stock");
                try {
                    while (result.next()){
                        result movie = new result(result.getInt("likecount"),result.getInt("harga"),result.getString("imagedir"),result.getString("namaproduk"));
                        movieList.add(movie);
                    }
                }catch (Exception e){
                    Log.e("error occured",e.getMessage());
                }
                mAdapter.notifyDataSetChanged();

            }
        });
       // result movie = new result("Mad Max: Fury Road", "Action & Adventure", "2015");
       // movieList.add(movie);

       // mAdapter.notifyDataSetChanged();
    }
    private class YourAsyncTask extends AsyncTask<Void, Void, Void> {
        private ProgressDialog dialog;

        public YourAsyncTask(searchresult a) {
            dialog = new ProgressDialog(a, R.style.AppCompatAlertDialogStyle);
        }

        @Override
        protected void onPreExecute() {
            dialog.setMessage("Loading....");
            dialog.show();
        }

        protected Void doInBackground(Void... args) {
            prepareMovieData();
            onPostExecute(true,"completed");
            return null;
        }

        protected void onPostExecute(Boolean b, String a) {
            if (dialog.isShowing()) {
                dialog.dismiss();
            }
        }
    }
}
