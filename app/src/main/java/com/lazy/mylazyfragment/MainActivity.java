package com.lazy.mylazyfragment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.lazy.mylazyfragment.activity.Activity1;
import com.lazy.mylazyfragment.activity.Activity2;
import com.lazy.mylazyfragment.activity.Activity3;
import com.lazy.mylazyfragment.activity.Activity3Lazy;
import com.lazy.mylazyfragment.activity.Activity4;
import com.lazy.mylazyfragment.activity.Activity5;
import com.lazy.mylazyfragment.activity.Activity6;
import com.lazy.mylazyfragment.activity.Activity7;
import com.lazy.mylazyfragment.activity.ActivityVpOldLazy;


/**
 * 参考资料 ：https://www.jianshu.com/p/2201a107d5b5?utm_campaign=hugo
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_1).setOnClickListener(this);
        findViewById(R.id.btn_2).setOnClickListener(this);
        findViewById(R.id.btn_3).setOnClickListener(this);
        findViewById(R.id.btn_4).setOnClickListener(this);
        findViewById(R.id.btn_5).setOnClickListener(this);
        findViewById(R.id.btn_6).setOnClickListener(this);
        findViewById(R.id.btn_7).setOnClickListener(this);
        findViewById(R.id.btn_8).setOnClickListener(this);
        findViewById(R.id.btn_9).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_1:
                startActivity(new Intent(this, Activity1.class));
                break;
            case R.id.btn_2:
                startActivity(new Intent(this, Activity2.class));
                break;
            case R.id.btn_3:
                // add hide  show  普通方式
                startActivity(new Intent(this, Activity3.class));
                break;
            case R.id.btn_4:
                // 嵌套fragment  懒加载
                startActivity(new Intent(this, Activity4.class));
                break;
            case R.id.btn_5:
                // 复杂嵌套 懒加载  首页 是 add + hide + show
                startActivity(new Intent(this, Activity5.class));
                break;
            case R.id.btn_6:
                // 复杂嵌套  首页是  vp+adapter   子page 什么都有
                startActivity(new Intent(this, Activity6.class));
                break;
            case R.id.btn_7:
                // viewpager2
                startActivity(new Intent(this, Activity7.class));
                break;
            case R.id.btn_8:
                startActivity(new Intent(this, Activity3Lazy.class));
                break;
            case R.id.btn_9:
                startActivity(new Intent(this, ActivityVpOldLazy.class));
                break;
            default:
                break;
        }
    }
}