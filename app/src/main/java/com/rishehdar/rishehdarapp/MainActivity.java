package com.rishehdar.rishehdarapp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import cn.pedant.SweetAlert.SweetAlertDialog;



public class MainActivity extends AppCompatActivity {


    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        webviewamir();






    }

    private void webviewamir(){
        SweetAlertDialog pDialog = new SweetAlertDialog(MainActivity.this, SweetAlertDialog.PROGRESS_TYPE);
        pDialog.setTitleText("درحال لود اطلاعات از سرور");
        pDialog.setContentText("لطفا منتظر بمانید...");
        pDialog.setCancelable(false);
        pDialog.show();


        webView = findViewById(R.id.webView);
        WebViewClient webViewClient = new WebViewClient();
            webView.requestFocus(View.FOCUS_DOWN);
        webView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                    case MotionEvent.ACTION_UP:
                        if (!v.hasFocus()) {
                            v.requestFocus();
                        }
                        break;
                }
                return false;
            }
        });
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        webView.setWebViewClient(webViewClient);
        String url = "https://rishehdar.com/";
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setFocusableInTouchMode(true);
        webView.setFocusable(true);
        webView.loadUrl(url);
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                pDialog.cancel();
            }

            @Override
            public void onLoadResource(WebView view, String url) {
                super.onLoadResource(view, url);
            }

            @Override
            public void doUpdateVisitedHistory(WebView view, String url, boolean isReload) {
                super.doUpdateVisitedHistory(view, url, isReload);
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
                SweetAlertDialog sweetAlertDialog =new SweetAlertDialog(MainActivity.this,SweetAlertDialog.ERROR_TYPE)
                        .setTitleText("خطا")
                        .setContentText("در دریافت اطلاعات از سرور به مشکل خوردیم دوباره امتحان کنید...")

                        .setConfirmButton("باشه", new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                finish();
                            }
                        });
                sweetAlertDialog.setCancelable(false);
                sweetAlertDialog.show();

            }
        });





    }



    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            switch (keyCode) {
                case KeyEvent.KEYCODE_BACK:
                    if (webView.canGoBack()) {
                        webView.goBack();
                    } else {
                        SweetAlertDialog sweetAlertDialog =new SweetAlertDialog(this,SweetAlertDialog.WARNING_TYPE)
                                .setTitleText("هشدار")
                                        .setContentText("آیا واقعا میخواهید خارج شوید؟")
                                                .setCancelButton("نه", new SweetAlertDialog.OnSweetClickListener() {
                                                    @Override
                                                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                                                        sweetAlertDialog.cancel();
                                                    }
                                                })
                                                        .setConfirmButton("باشه", new SweetAlertDialog.OnSweetClickListener() {
                                                            @Override
                                                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                                                finish();
                                                            }
                                                        });
                        sweetAlertDialog.setCancelable(false);
                        sweetAlertDialog.show();

                    }
                    return true;
            }

        }
        return super.onKeyDown(keyCode, event);
    }
}