package com.example.sky.docapp2;



import android.app.Application;

import com.parse.Parse;
import com.parse.ParseACL;


public class StarterApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);

        // Add your initialization code here
        Parse.initialize(new Parse.Configuration.Builder(getApplicationContext())
                .applicationId("f9c8c920f8e4e181392a1cde773277a7ff2658fe")
                .clientKey("beeb7a12dbf1f8f53329b71eda2f9e16f65ef2bb")
                .server("http://18.188.71.49:80/parse/")
                .build()
        );




        //ParseUser.enableAutomaticUser();
        ParseACL defaultACL = new ParseACL();
        defaultACL.setPublicReadAccess(true);
        defaultACL.setPublicWriteAccess(true);
        ParseACL.setDefaultACL(defaultACL, true);

    }
}
