package com.fake.shopee.shopeefake;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.fake.shopee.shopeefake.Main_pages.loginactivity;
import com.fake.shopee.shopeefake.Main_pages.main_cart;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLClientInfoException;

public class activity_galery extends Activity {

    private FirebaseAuth mAuth;

    SQLclass sqlclass;

    TextView textTargetUri;
    EditText nameproduct;
    ImageView targetImage;
    Uri targetUri=null;
    ImageButton backarrow,done;
    private StorageReference mStorageRef;

    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    private void updateUI(FirebaseUser currentUser) {
        if (currentUser==null){
            Intent i = new Intent(activity_galery.this,loginactivity.class);
            startActivity(i);
            finish();
        }
    }
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galery);
        try {
            Connection con = sqlclass.CONN(SQLclass.ip, SQLclass.db, SQLclass.un, SQLclass.password, SQLclass.port, SQLclass.instance);
        }catch (Exception e){
            Log.e("erro",e.toString());
        }
        sqlclass = new SQLclass();
        mAuth = FirebaseAuth.getInstance();
        nameproduct =(EditText) findViewById(R.id.namaproduk);
        mStorageRef = FirebaseStorage.getInstance().getReference();
        backarrow = (ImageButton) findViewById(R.id.backarrow);
        done = (ImageButton) findViewById(R.id.donegalery);
        targetImage = (ImageView)findViewById(R.id.targetimage);
        if(mAuth.getCurrentUser()==null){

        }
        else{
            Intent intent = new Intent(Intent.ACTION_PICK,
                    android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent, 0);
        }
        targetImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 0);
            }
        });
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                YourAsyncTask b = new YourAsyncTask(activity_galery.this);
                b.execute();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            targetUri = data.getData();
            Toast.makeText(getApplicationContext(),targetUri.toString(), Toast.LENGTH_SHORT).show();
            Bitmap bitmap;
            try {
                bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(targetUri));
                targetImage.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
    private class YourAsyncTask extends AsyncTask<Void, Void, Void> {
        private ProgressDialog dialog;

        public YourAsyncTask(activity_galery a) {
            dialog = new ProgressDialog(a,R.style.AppCompatAlertDialogStyle);
        }

        @Override
        protected void onPreExecute() {
            dialog.setMessage("Processing....");
            dialog.show();
        }

        protected Void doInBackground(Void... args) {
            Log.e("erro",targetUri.getPath());
            String temp=nameproduct.getText().toString();

            String referencename = temp;
            StorageReference riversRef = mStorageRef.child(referencename+".jpg");

            riversRef.putFile(targetUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            // Get a URL to the uploaded content
                            Uri downloadUrl = taskSnapshot.getDownloadUrl();
                            Log.e("picture",downloadUrl.toString());
                            onPostExecute(true,"Success");
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {
                            // Handle unsuccessful uploads
                            onPostExecute(false,"failed");
                        }
                    });
            return null;
        }

        protected void onPostExecute(Boolean b,String a) {
            // do UI work here
            if (dialog.isShowing()) {
                dialog.dismiss();
            }
            Toast.makeText(getApplicationContext(),a, Toast.LENGTH_SHORT).show();
            if(b=true){
                finish();
            }
            else{

            }
        }
    }
}
