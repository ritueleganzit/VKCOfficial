package com.example.vkcofficial;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.vkcofficial.adapter.HourlyPOAdapter;

import java.util.ArrayList;

public class HourlyProductionActivity extends AppCompatActivity {
RecyclerView rc_hourly;
    ArrayList<String> arrayList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hourly_production);
        rc_hourly=findViewById(R.id.rc_hourly);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(HourlyProductionActivity.this,LinearLayoutManager.VERTICAL,false);
        rc_hourly.setLayoutManager(layoutManager);
        rc_hourly.setAdapter(new HourlyPOAdapter(arrayList,HourlyProductionActivity.this));

   findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View v) {
           onBackPressed();
       }
   });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);

    }
}
