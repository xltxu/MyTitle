package com.example.xlt.mytitle;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * @author yangyu
 *  功能描述：欢迎界面
 */
public class WelcomeActivity extends Activity implements Runnable {

    //是否是第一次使用
    private boolean isFirstUse;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        /**
         * 启动一个延迟线程
         */
        new Thread(this).start();
    }

    public void run() {
        try {
            /**
             * 延迟两秒时间
             */
            Thread.sleep(2000);

            //读取SharedPreferences中需要的数据
            SharedPreferences preferences = getSharedPreferences("isFirstUse",MODE_PRIVATE);

            isFirstUse = preferences.getBoolean("isFirstUse", true);

            /**
             *如果用户不是第一次使用则直接调转到显示界面,否则调转到引导界面
             */
            if (isFirstUse) {
                startActivity(new Intent(WelcomeActivity.this, GuideActivity.class));
            } else {
                startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
            }
            finish();

            //实例化Editor对象
            Editor editor = preferences.edit();
            //存入数据
            editor.putBoolean("isFirstUse", false);
            //提交修改
            editor.commit();


        } catch (InterruptedException e) {

        }
    }


}
