package com.mobile.laporperjadin.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.mobile.laporperjadin.R;
import com.mobile.laporperjadin.SuksesActivity;

public class SendNotifikasi extends AppCompatActivity {
    private String result = "notifikasi";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_notifikasi);
    }

    public void toNotifikasiSuccess(View view) {
        Intent intent = new Intent(SendNotifikasi.this, SuksesActivity.class);
        intent.putExtra("result",result);
        startActivity(intent);
    }

    public void toNotifToHome(View view) {
        startActivity(new Intent(SendNotifikasi.this,HomeActivityAdmin.class));
    }
}
