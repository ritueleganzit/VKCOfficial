package com.example.vkcofficial;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import me.nereo.multi_image_selector.MultiImageSelector;

public class CompletedPOActivity extends AppCompatActivity {
    RecyclerView rc_production_complete;
    ArrayList<String> arrayList=new ArrayList<>();
    ArrayList<String> imglist=new ArrayList<>();
    LinearLayout hourlyproduction,uploadlinear;
    RecyclerView rc_image;
    private static final int REQUEST_IMAGE = 201;
    protected static final int REQUEST_STORAGE_READ_ACCESS_PERMISSION = 202;
    private ArrayList<String> mSelectPath;


    ArrayList<String> str_photo_array=new ArrayList<>();
    private String mediapath;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_completed_po);
        rc_production_complete=findViewById(R.id.rc_production_complete);
        hourlyproduction=findViewById(R.id.hourlyproduction);
        uploadlinear=findViewById(R.id.uploadlinear);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(CompletedPOActivity.this,LinearLayoutManager.VERTICAL,false);
        rc_production_complete.setLayoutManager(layoutManager);
        rc_production_complete.setAdapter(new PendingPODetail.PendingPODetailAdapter(arrayList,CompletedPOActivity.this));

        hourlyproduction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CompletedPOActivity.this,HourlyCompleteActivity.class));
                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
            }
        });
        uploadlinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog d=new Dialog(CompletedPOActivity.this,
                        R.style.Theme_Dialog);
                d.setContentView(R.layout.upload_defects_dialog);

                TextView ok=d.findViewById(R.id.ok);
                 rc_image=d.findViewById(R.id.rc_image);
                RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(CompletedPOActivity.this,LinearLayoutManager.HORIZONTAL,false);
                rc_image.setLayoutManager(layoutManager);                TextView cancel=d.findViewById(R.id.cancel);
                final EditText ed_email=d.findViewById(R.id.ed_email);

                ed_email.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        pickImage();
                    }
                });

                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        d.dismiss();

                    }
                });

                d.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                if(!isFinishing())
                {
                    d.show();
                }
            }
        });

    }

    private void pickImage() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN // Permission was added in API Level 16
                && ActivityCompat.checkSelfPermission(CompletedPOActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            requestPermission(Manifest.permission.READ_EXTERNAL_STORAGE,
                    getString(R.string.mis_permission_rationale),
                    REQUEST_STORAGE_READ_ACCESS_PERMISSION);
        } else {

            MultiImageSelector selector = MultiImageSelector.create(CompletedPOActivity.this);
            selector.single();
            // selector.count(6);
            selector.showCamera(false);

            selector.origin(mSelectPath);
            selector.start(CompletedPOActivity.this, REQUEST_IMAGE);
        }
    }
    private void requestPermission(final String permission, String rationale, final int requestCode) {
        if (ActivityCompat.shouldShowRequestPermissionRationale(CompletedPOActivity.this, permission)) {
            new AlertDialog.Builder(CompletedPOActivity.this)
                    .setTitle(R.string.mis_permission_dialog_title)
                    .setMessage(rationale)
                    .setPositiveButton(R.string.mis_permission_dialog_ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(CompletedPOActivity.this, new String[]{permission}, requestCode);
                        }
                    })
                    .setNegativeButton(R.string.mis_permission_dialog_cancel, null)
                    .create().show();
        } else {
            ActivityCompat.requestPermissions(CompletedPOActivity.this, new String[]{permission}, requestCode);
        }
    }
    public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder>
    {
        ArrayList<String> img;
        Context context;

        public ImageAdapter(ArrayList<String> img, Context context) {
            this.img = img;
            this.context = context;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_upload_defects, viewGroup, false);
            ViewHolder myViewHolder = new ViewHolder(v);

            return myViewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
            Log.d("ddddddd",""+mediapath);

            Glide.with(getApplicationContext()).load(img.get(i)).into(viewHolder.imageView);

            viewHolder.mRemoveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeAt(i);
            }
        });
        }

        @Override
        public int getItemCount() {
            return img.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder{
            public ImageButton mRemoveButton;
            public ImageView imageView;

            public ViewHolder(View v){
                super(v);
                imageView = (ImageView) v.findViewById(R.id.img);
                mRemoveButton = (ImageButton) v.findViewById(R.id.ib_remove);
            }
        }

        private void removeAt(int position) {

            img.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, img.size());
            notifyItemChanged(position);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE) {
            if (resultCode == Activity.RESULT_OK) {
                mSelectPath = data.getStringArrayListExtra(MultiImageSelector.EXTRA_RESULT);
                StringBuilder sb = new StringBuilder();
                for (String p : mSelectPath) {
                    sb.append(p);
                    sb.append("\n");
                }


                mediapath = sb.toString().trim();
                Log.d("LOG_TAG", "Selected Images 1.5" + mediapath);

                Log.d("mediapathhhhhhhh", "" + mediapath);
                imglist.add(""+mediapath.trim());
                rc_image.setAdapter(new ImageAdapter(imglist,CompletedPOActivity.this));

            }
        }
    }
}
