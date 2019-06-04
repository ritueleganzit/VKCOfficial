package com.example.vkcofficial;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.vkcofficial.adapter.PendingPOAdapter;

import java.util.ArrayList;

public class PendingPODetail extends AppCompatActivity {
RecyclerView rc_production_complete;
    ArrayList<String> arrayList=new ArrayList<>();
    LinearLayout hourly;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pending_podetail);
        rc_production_complete=findViewById(R.id.rc_production_complete);
        hourly=findViewById(R.id.hourly);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(PendingPODetail.this,LinearLayoutManager.VERTICAL,false);
        rc_production_complete.setLayoutManager(layoutManager);
        rc_production_complete.setAdapter(new PendingPODetailAdapter(arrayList,PendingPODetail.this));
        hourly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PendingPODetail.this,HourlyProductionActivity.class));
                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);

            }
        });

        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
    public static class PendingPODetailAdapter extends RecyclerView.Adapter<PendingPODetailAdapter.MyViewHolder> {

        ArrayList<String> campaigns;
        Context context;
        Activity activity;

        public PendingPODetailAdapter(ArrayList<String> campaigns, Context context) {
            this.campaigns = campaigns;
            this.context = context;
            activity = (Activity) context;
        }

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

            View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_production_complete, viewGroup, false);
            MyViewHolder myViewHolder = new MyViewHolder(v);

            return myViewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull final MyViewHolder holder, final int i) {


        }

        @Override
        public int getItemCount() {
            return 7;
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {
            public MyViewHolder(@NonNull View itemView) {
                super(itemView);

            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);

    }
}
