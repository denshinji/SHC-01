package com.mobile.laporperjadin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.mobile.laporperjadin.admin.HomeActivityAdmin;
import com.mobile.laporperjadin.kepalabidang.HomeActivityKabid;
import com.mobile.laporperjadin.umum.HomeActivityUmum;

public class SuksesActivity extends AppCompatActivity {
    private MaterialButton btnSukses;
    private TextView title,desc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_succes);
        btnSukses = findViewById(R.id.btnSuccess);
        title = findViewById(R.id.title_success);
        desc = findViewById(R.id.desc_sukses);
        String result = getIntent().getStringExtra("result");
        if(result.equals("register")){
            title.setText("Registrasi Berhasil");
            desc.setText(R.string.successRegister);
            btnSukses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
        }
        if(result.equals("ajukan")) {
            title.setText("Pengajuan Berhasil");
            desc.setText(R.string.successPengajuan);
            btnSukses.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(SuksesActivity.this, HomeActivityUmum.class));
                }
            });
        }
        if(result.equals("gantipasswordumum")) {
            title.setText("Ganti Password Berhasil");
            desc.setText("Password berhasil di ganti");
            btnSukses.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(SuksesActivity.this, HomeActivityUmum.class));
                }
            });
        }
        if(result.equals("gantipasswordadmin")) {
            title.setText("Ganti Password Berhasil");
            desc.setText("Password berhasil di ganti");
            btnSukses.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(SuksesActivity.this, HomeActivityAdmin.class));
                }
            });
        }
        if(result.equals("gantipasswordkabid")) {
            title.setText("Ganti Password Berhasil");
            desc.setText("Password berhasil di ganti");
            btnSukses.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(SuksesActivity.this, HomeActivityKabid.class));
                }
            });
        }
        if(result.equals("updatepengajuan")) {
            title.setText("Pengajuan Berhasil Di Update");
            desc.setText("Pengajuan Telah Di Update");
            btnSukses.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(SuksesActivity.this, HomeActivityAdmin.class));
                }
            });
        }
        if(result.equals("updateuser")) {
            title.setText("User Berhasil Di Simpan");
            desc.setText("Semua data user berhasil di simpan");
            btnSukses.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(SuksesActivity.this, HomeActivityAdmin.class));
                }
            });
        }
        if(result.equals("hapususer")) {
            title.setText("User Berhasil Di Hapus");
            desc.setText("User di hapus dari daftar pengguna aplikasi");
            btnSukses.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(SuksesActivity.this, HomeActivityAdmin.class));
                }
            });
        }
    }
}
