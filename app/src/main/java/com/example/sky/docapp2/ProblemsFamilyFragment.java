package com.example.sky.docapp2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ProblemsFamilyFragment extends Fragment {

    public static final String PAGE_TITLE = "Problems";
    public ProblemsFamilyFragment() {
        // Required empty public constructor
    }

    public static ProblemsFamilyFragment newInstance() {
        ProblemsFamilyFragment fragment = new ProblemsFamilyFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment_problems, container, false);
    }
}
