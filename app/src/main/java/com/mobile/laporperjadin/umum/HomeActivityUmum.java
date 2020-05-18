package com.mobile.laporperjadin.umum;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.mobile.laporperjadin.AccountActivity;
import com.mobile.laporperjadin.LoginActivity;
import com.mobile.laporperjadin.MenuLoginActivity;
import com.mobile.laporperjadin.R;

import java.text.DateFormat;
import java.util.Date;

public class HomeActivityUmum extends AppCompatActivity {
    public static final String PREFS_NAME = "MyPrefsFile";
    private TextView username;
    private String mUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_umum);
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        mUsername = settings.getString("username", "default");
        username = findViewById(R.id.userHomeUmum);
        username.setText(mUsername);

        String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
        TextView timeNow = (TextView) findViewById(R.id.timeNowUmum);
        timeNow.setText(currentDateTimeString);


    }

    public void toPengajuanUmum(View view) {
        startActivity(new Intent(HomeActivityUmum.this,FormPengajuan.class));
    }
    public void toRiwayatUmum(View view) {
        startActivity(new Intent(HomeActivityUmum.this, RiwayatPengajuanActivity.class));
    }
    public void toAccountUmum(View view) {
        startActivity(new Intent(HomeActivityUmum.this, AccountActivity.class));
    }
    public void toLogoutUmum(View view) {
        SharedPreferences sharedPrefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.clear();
        editor.commit();
        startActivity(new Intent(HomeActivityUmum.this, MenuLoginActivity.class));
    }
}
