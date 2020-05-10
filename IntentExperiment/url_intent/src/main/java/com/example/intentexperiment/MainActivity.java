package com.example.intentexperiment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText test;
    Button bt;
    String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //获取EditText
        test = findViewById(R.id.et);
        //获取按钮
        bt = findViewById(R.id.bt);
        bt.setOnClickListener((new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        /*获取URL地址并启动隐式Intent的调用
                        //获取网址
                        String url = test.getText().toString();
                        Intent intent = new Intent();
                        //将url字符串解析为uri对象
                        Uri uri = Uri.parse(url);
                        //设置data
                        intent.setData(uri);
                        //设置动作
                        intent.setAction(Intent.ACTION_VIEW);
                        startActivity(intent);
                        */
                       //选择浏览器
                       switch (v.getId()){
                           case R.id.bt:
                               Intent intent = new Intent(Intent.ACTION_VIEW);
                               intent.setData(Uri.parse(test.getText().toString()));
                               Intent choose = Intent.createChooser(intent,"使用一下方式打开");
                               startActivity(choose);
                               break;
                       }
                    }
                })
        );
    }
}
