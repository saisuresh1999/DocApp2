package com.example.sky.docapp2;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.parse.ParseUser;

import static android.support.v4.app.ActivityCompat.invalidateOptionsMenu;

public class SettingsFragment extends Fragment {
private  Button logout;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        ParseUser currentUser = ParseUser.getCurrentUser();
        String strUser = currentUser.getUsername().toString();
        final View view = inflater.inflate(R.layout.fragment_settings,
                container, false);

      logout=(Button) view.findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseUser.getCurrentUser().logOut();//invalidateOptionsMenu();
                Toast.makeText(getActivity(), "Disconnected...", Toast.LENGTH_LONG).show();
                Intent intent=new Intent(getActivity(),LoginSignup.class);
                startActivity(intent);
               getActivity().finish();
            }
        });



        return view;
    }


}
