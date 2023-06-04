package com.rishehdar.rishehdarapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;

import cn.pedant.SweetAlert.SweetAlertDialog;
import pl.droidsonroids.gif.GifImageView;


public class SplashActivity extends AppCompatActivity {


    ProgressBar gifImageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        gifImageView = findViewById(R.id.loading_gif_splash);


        if (isNetworkConnected()) {

            Handler handler = new Handler();
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    if (isNetworkConnected()) {
                        startActivity(new Intent(SplashActivity.this, MainActivity.class));
                        finish();
                    } else {
                        SweetAlertDialog pDialog = new SweetAlertDialog(SplashActivity.this, SweetAlertDialog.ERROR_TYPE);
                        pDialog.setTitleText("خطا");
                        pDialog.setContentText("به اینترنت متصل نیستید");
                        pDialog.setCancelable(false);
                        pDialog.setConfirmText("باشه");
                        pDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                finish();
                            }
                        });
                        pDialog.show();
                        gifImageView.setVisibility(View.GONE);
                    }
                }
            };
            handler.postDelayed(runnable, 2000); // 5000 میلی‌ثانیه = 5 ثانیه

        }else {
            gifImageView.setVisibility(View.GONE);

            SweetAlertDialog pDialog = new SweetAlertDialog(SplashActivity.this, SweetAlertDialog.ERROR_TYPE);
            pDialog.setTitleText("خطا");
            pDialog.setContentText("به اینترنت متصل نیستید");
            pDialog.setCancelable(false);
            pDialog.setConfirmText("باشه");
            pDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                @Override
                public void onClick(SweetAlertDialog sweetAlertDialog) {
                    finish();
                }
            });
            pDialog.show();
        }
    }


    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }
}