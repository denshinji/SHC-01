package com.mobile.laporperjadin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.mobile.laporperjadin.admin.HomeActivityAdmin;
import com.mobile.laporperjadin.kepalabidang.HomeActivityKabid;
import com.mobile.laporperjadin.umum.HomeActivityUmum;

public class SplashActivity extends AppCompatActivity {
    public static final String PREFS_NAME = "MyPrefsFile";
    String id_user;
    String level;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        id_user = settings.getString("username", "default");
        level = settings.getString("level", "default");
        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    sleep(4000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {

                    if(!id_user.equals("default")){
                        if(level.equals("1")){
                            startActivity(new Intent(SplashActivity.this, HomeActivityUmum.class));
                        }else if(level.equals("2")){
                            startActivity(new Intent(SplashActivity.this, HomeActivityAdmin.class));
                        }else if(level.equals("3")){
                            startActivity(new Intent(SplashActivity.this, HomeActivityKabid.class));
                        }

                    }else{
                        startActivity(new Intent(SplashActivity.this,MenuLoginActivity.class));
                    }



                }
            }
        };

        thread.start();

    }
}
