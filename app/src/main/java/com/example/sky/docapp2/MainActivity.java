package com.example.sky.docapp2;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.parse.ParseAnalytics;
import com.parse.ParseAnonymousUtils;
import com.parse.ParseUser;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ParseUser currentUser = ParseUser.getCurrentUser();
        if(currentUser == null){
            Intent intent =new Intent(MainActivity.this,LoginSignup.class);
            startActivity(intent);
            finish();
        }

        else{


            if(currentUser!=null){
                BottomNavigationView bottomNav=findViewById(R.id.bottom_navigation);
                bottomNav.setOnNavigationItemSelectedListener(navListener);
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container,new HomeFragment())
                        .commit();
            }
            else{
                Intent intent =new Intent(MainActivity.this,LoginSignup.class);
                startActivity(intent);
                finish();
            }
        }
        ParseAnalytics.trackAppOpenedInBackground(getIntent());

    }
    private BottomNavigationView.OnNavigationItemSelectedListener navListener=
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    Fragment selectedFragement = null;
                    switch(menuItem.getItemId())
                    {
                        case R.id.nav_home:
                            selectedFragement=new HomeFragment();
                            break;
                        case R.id.nav_profile:
                            selectedFragement=new ProfileFragment(); break;
                        case R.id.nav_settings:
                            selectedFragement = new SettingsFragment(); break;
                        case R.id.nav_family:
                            selectedFragement=new FamilyFragment();break;

                    }
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.fragment_container,selectedFragement)
                            .commit();

                    return true;
                }
            };
}
