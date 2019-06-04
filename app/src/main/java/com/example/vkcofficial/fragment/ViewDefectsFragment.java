package com.example.vkcofficial.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.vkcofficial.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ViewDefectsFragment extends Fragment {


    public ViewDefectsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_view_defects, container, false);
    }

}
