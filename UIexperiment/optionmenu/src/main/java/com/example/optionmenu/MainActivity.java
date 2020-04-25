package com.example.optionmenu;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //定义“字体大小”菜单项的标识
    private static final int FONT_10 = 0x101;
    private static final int FONT_16 = 0x102;
    private static final int FONT_20 = 0x103;

    //定义“普通菜单项”的标识
    private static final int PLAIN_ITEM = 0x104;

    //定义“字体颜色”菜单项的标识
    private static final int FONT_RED = 0x105;
    private static final int FONT_BLACK = 0x17;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.edittext);
    }

    //当用户点击menu键时触发该方法
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //添加“字体大小”的子菜单
        SubMenu fontMenu = menu.addSubMenu("字体大小");
        fontMenu.setHeaderTitle("选择字体大小");
        fontMenu.add(0, FONT_10, 0, "10号字体");
        fontMenu.add(0, FONT_16, 0, "16号字体");
        fontMenu.add(0, FONT_20, 0, "20号字体");

        //添加“普通菜单项”的子菜单
        menu.add(0,PLAIN_ITEM,0,"普通菜单项");
        //添加“字体颜色”的子菜单
        SubMenu colorMenu = menu.addSubMenu("字体颜色");
        colorMenu.setHeaderTitle("选择字体颜色");
        colorMenu.add(0,FONT_RED,0,"红色");
        colorMenu.add(0,FONT_BLACK,0,"黑色");

        return super.onCreateOptionsMenu(menu);
    }

    //选择菜单的菜单项被单击后的回调方法
    @Override
    public boolean onOptionsItemSelected(MenuItem mi)
    {
        //判断单击的菜单项
        switch (mi.getItemId())
        {
            case FONT_10:textView.setTextSize(10 * 2); break;
            case FONT_16:textView.setTextSize(16 * 2); break;
            case FONT_20:textView.setTextSize(20 * 2); break;
            case FONT_RED:textView.setTextColor(Color.RED); break;
            case FONT_BLACK:textView.setTextColor(Color.BLACK); break;
            case PLAIN_ITEM:
                Toast.makeText(MainActivity.this,"你单击了普通菜单项",Toast.LENGTH_LONG)
                        .show();break;

        }
        return true;
    }

}
