package com.example.mybrowser;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.net.URL;

public class MainActivity extends AppCompatActivity {

    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        webView = findViewById(R.id.WV);
        webView.getSettings().setJavaScriptEnabled(true);

        Intent intent = getIntent();
        Uri data = intent.getData();
        URL url = null;
        try{
            //创建一个URL对象，参数分别是协议，主机名，路径
            url = new URL(data.getScheme(), data.getHost(), data.getPath());
        }catch (Exception e){
            e.printStackTrace();
        }

        //WebView加载web资源
        webView.loadUrl(url.toString());
        //覆盖默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view,  String url) {
                view.loadUrl(url);
                return true;
            }
        });
    }
}
