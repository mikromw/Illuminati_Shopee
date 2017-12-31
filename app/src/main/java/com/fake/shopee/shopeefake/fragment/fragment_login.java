package com.fake.shopee.shopeefake.fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
                if (isvalidemail(email.getText().toString()) && isvalidpassword(pass.getText().toString())){
                    DoLogin doLogin = new DoLogin();
                    doLogin.execute("");
                }
                else if(isvalidemail(email.getText().toString())==false) {
                    Toast.makeText(getActivity(), "Bad Email",
                            Toast.LENGTH_SHORT).show();
                }
                else if(isvalidpassword(pass.getText().toString())==false) {
                    Toast.makeText(getActivity(), "Bad password",
                            Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getActivity(), "Bad password",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        return rootView;
    }
    public class DoLogin extends AsyncTask<String, String, String> {
        String z = "Processing...";
        Boolean isSuccess = false;
        String userid = email.getText().toString();
        String password = pass.getText().toString();
        private ProgressDialog dialog=new ProgressDialog(getActivity());

        @Override
        protected void onPreExecute() {
            this.dialog.setMessage("Logging in");
            this.dialog.show();
        }

        @Override
        protected void onPostExecute(String r) {
            if (dialog.isShowing()) {
                dialog.dismiss();
            }
            Toast.makeText(getActivity(), r, Toast.LENGTH_SHORT).show();
            if (isSuccess) {
                Intent i = new Intent(getActivity(), main_profile.class);
                startActivity(i);
            }
        }


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
                                session.setusename(user.getEmail());
                                z="Signed in As "+user.getEmail();
                                isSuccess=true;
                                onPostExecute(z);
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w(TAG, "signInWithEmail:failure", task.getException());
                                Toast.makeText(getActivity(), "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                                session_class session= new session_class(getActivity());
                                session.setusename("");
                                z="Error Login";
                                onPostExecute(z);
                            }

                            // ...
                        }
                    });
            return z;
        }
    }
    public boolean isvalidpassword (final String password){

            Pattern pattern;
            Matcher matcher;

            final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{4,}$";

            pattern = Pattern.compile(PASSWORD_PATTERN);
            matcher = pattern.matcher(password);

            return matcher.matches();

        }

    public boolean isvalidemail (final String email){

        Pattern pattern;
        Matcher matcher;

        final String PASSWORD_PATTERN = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(email);

        return matcher.matches();

    }
}

