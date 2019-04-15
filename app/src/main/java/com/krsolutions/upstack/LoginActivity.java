package com.krsolutions.upstack;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    MaterialButton buttonLogin;
    WebView webView;
    String client_id=BuildConfig.client_id;
    SharedPreferences sharedPreferences;
    private static final String TAG = "LoginActivity";
    String url ="https://stackexchange.com/oauth/dialog?client_id="+client_id+"&scope=private_info&redirect_uri=https://stackexchange.com/oauth/login_success";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        buttonLogin = findViewById(R.id.buttonLogin);
        webView = findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        sharedPreferences=getSharedPreferences(BuildConfig.APPLICATION_ID,MODE_PRIVATE);
        if(sharedPreferences.contains("token")){
            Intent intent = new Intent(this,UserInterestAcitivity.class);
            startActivity(intent);
            finish();
        }
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webView.setVisibility(View.VISIBLE);
                buttonLogin.setVisibility(View.GONE);
                getToken();
            }
        });
    }

    public void getToken(){
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                if(url.contains("access_token")){
                    String raw_url=url;
                    String[] str=raw_url.split("access_token=");

                    String[] token=str[1].split("&expires=");
                    Log.d(TAG, "onPageFinished: Token_url= "+raw_url+"\nAfter Split= "+str[1]+"\nraw_token= "+token[0]+"\nexpires= "+token[1]);
                    Toast.makeText(getApplicationContext(),"Logged in",Toast.LENGTH_SHORT).show();
                    sharedPreferences.edit().putString("token",token[0]).commit();
                    sharedPreferences.edit().putString("token_expires_at",token[1]).commit();
                    sharedPreferences.edit().commit();
                    webView.setVisibility(View.GONE);
                    Intent intent = new Intent(getApplication(),UserInterestAcitivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
        webView.loadUrl(url);
    }
}
