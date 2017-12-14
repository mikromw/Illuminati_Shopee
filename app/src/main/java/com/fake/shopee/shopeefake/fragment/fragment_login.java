package com.fake.shopee.shopeefake.fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.fake.shopee.shopeefake.Main_pages.main_profile;
import com.fake.shopee.shopeefake.R;
import com.fake.shopee.shopeefake.session_class;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static android.content.ContentValues.TAG;

/**
 * Created by Riandy on 12/10/2017.
 */

public class fragment_login extends Fragment{

    EditText email,pass;
    Button btnlogin;
    private FirebaseAuth mAuth;
    ProgressDialog dialog;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mAuth = FirebaseAuth.getInstance();
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_login, container, false);
        email = (EditText) rootView.findViewById(R.id.emaillogin);
        pass = (EditText) rootView.findViewById(R.id.passwordlogin);
        btnlogin = (Button) rootView.findViewById(R.id.btnloginlogin);

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog = new ProgressDialog(getActivity());
                dialog.setMessage("Login...");
                dialog.show();
                DoLogin doLogin = new DoLogin();
                doLogin.execute("");
            }
        });

        return rootView;
    }
    public class DoLogin extends AsyncTask<String, String, String> {
        String z = "";
        Boolean isSuccess = false;
        String userid = email.getText().toString();
        String password = pass.getText().toString();

        @Override
        protected void onPreExecute() {

        }

        @Override
        protected void onPostExecute(String r) {
            dialog.dismiss();
            Toast.makeText(getActivity(), r, Toast.LENGTH_SHORT).show();
            if (isSuccess) {
                Intent i = new Intent(getActivity(), main_profile.class);
                startActivity(i);
            }
        }

        ;

        @Override
        protected String doInBackground(String... params) {
            mAuth.signInWithEmailAndPassword(userid, password)
                    .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d(TAG, "signInWithEmail:success");
                                FirebaseUser user = mAuth.getCurrentUser();
                                session_class session= new session_class(getActivity());
                                session.setusename(user.getDisplayName());
                                z="Signed in As"+user.getDisplayName();
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w(TAG, "signInWithEmail:failure", task.getException());
                                Toast.makeText(getActivity(), "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                                session_class session= new session_class(getActivity());
                                session.setusename("");
                                z="Error Login";
                            }

                            // ...
                        }
                    });
            mAuth.createUserWithEmailAndPassword(userid, password)
                    .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d("firebase", "createUserWithEmail:success");
                                FirebaseUser user = mAuth.getCurrentUser();
                                session_class session= new session_class(getActivity());
                                session.setusename(user.getDisplayName());
                                z="Signed in As"+user.getDisplayName();
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                Toast.makeText(getActivity(), "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                                session_class session= new session_class(getActivity());
                                session.setusename("");
                                z="Login failed";
                            }

                            // ...
                        }
                    });
            return z;
        }
    }
}
