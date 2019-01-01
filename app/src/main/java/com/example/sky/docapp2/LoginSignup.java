package com.example.sky.docapp2;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class LoginSignup extends AppCompatActivity implements View.OnClickListener, View.OnKeyListener {
    TextView changeSignupmode;
    EditText  pwdET;
    Boolean signUpModeActive = true;
    @Override
    public void onClick(View v) {

        if(v.getId()==R.id.changeSignupmodeTextview){
            Log.i("App info","Change  signup mode");
            Button mainButton=(Button) findViewById(R.id.MainButton);


            if(signUpModeActive){
                signUpModeActive=false;
                mainButton.setText("Login");
                changeSignupmode.setText("Or,Signup");

            }
            else{
                signUpModeActive=true;
                mainButton.setText("Signup");
                changeSignupmode.setText("Or, Login");
            }
        }

        // for touchinng in bg ..keypad should be invisible
        else if(v.getId()==R.id.rl || v.getId()==R.id.logo){
            InputMethodManager inputMethodManager=(InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0);


        }

    }
    public void signUp(View view){

        EditText usernameET=(EditText) findViewById(R.id.un);

        if(usernameET.getText().toString().matches("") || pwdET.getText().toString().matches("")){
            Toast.makeText(this,"Required Username / Password",Toast.LENGTH_LONG).show();
        }
        else{

            if(signUpModeActive){
                ParseUser user=new ParseUser();
                user.setUsername(usernameET.getText().toString());
                user.setPassword(pwdET.getText().toString());
                user.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        if(e==null){
                            Log.i("Signup","Successful");
                            Toast.makeText(LoginSignup.this,"Successful",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(LoginSignup.this,MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                        else{
                            Toast.makeText(LoginSignup.this,e.getMessage(),Toast.LENGTH_SHORT).show();

                        }
                    }
                });
            }

            else {

                ParseUser.logInInBackground(usernameET.getText().toString(), pwdET.getText().toString(), new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException e) {

                        if (user != null) {

                            Log.i("Signup", "Login successful");
                            Toast.makeText(LoginSignup.this,"Successful",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(LoginSignup.this,MainActivity.class);
                            startActivity(intent);
                            finish();

                        } else {

                            Toast.makeText(LoginSignup.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                        }


                    }
                });


            }

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_signup);

        changeSignupmode= (TextView) findViewById(R.id.changeSignupmodeTextview);
        changeSignupmode.setOnClickListener(this);   pwdET=(EditText) findViewById(R.id.pw);

        //for touchinng in bg ..keypad should be invisible
        LinearLayout linearLayout=(LinearLayout) findViewById(R.id.rl);
        ImageView imageView=(ImageView) findViewById(R.id.logo);

        linearLayout.setOnClickListener(this);
        imageView.setOnClickListener(this);


        pwdET.setOnKeyListener( this);
        ParseAnalytics.trackAppOpenedInBackground(getIntent());
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_ENTER && event.getAction()==KeyEvent.ACTION_DOWN){
            signUp(v);

        }
        return false;
    }
}

