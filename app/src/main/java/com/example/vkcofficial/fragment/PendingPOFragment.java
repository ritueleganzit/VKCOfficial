package com.example.vkcofficial.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.vkcofficial.R;
import com.example.vkcofficial.adapter.PendingPOAdapter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class PendingPOFragment extends Fragment {

    ArrayList<String> arrayList=new ArrayList<>();

    RecyclerView rc_plan;
    public PendingPOFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_pending_po, container, false);
        rc_plan=v.findViewById(R.id.rc_pendingpo);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        rc_plan.setLayoutManager(layoutManager);
        rc_plan.setAdapter(new PendingPOAdapter(arrayList,getActivity()));
        return v;
    }

}
