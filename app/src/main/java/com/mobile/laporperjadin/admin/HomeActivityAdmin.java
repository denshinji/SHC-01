package com.mobile.laporperjadin.admin;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.DownloadListener;
import com.androidnetworking.interfaces.DownloadProgressListener;
import com.mobile.laporperjadin.AccountActivity;
import com.mobile.laporperjadin.LoginActivity;
import com.mobile.laporperjadin.MenuLoginActivity;
import com.mobile.laporperjadin.R;

public class HomeActivityAdmin extends AppCompatActivity {
    public static final String PREFS_NAME = "MyPrefsFile";
    private TextView username;
    private String mUsername;
    private String url_export_riwayat = "http://muhyudi.my.id/api_android/export_riwayat_pengajuan.php";
    private String url_export_user = "http://muhyudi.my.id/api_android/export_user.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_admin);
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        mUsername = settings.getString("username", "default");
        username = findViewById(R.id.userHomeAdmin);
        username.setText(mUsername);
    }

    public void toKelolaUser(View view) {
        download_user_pdf(url_export_user);
        startActivity(new Intent(HomeActivityAdmin.this, KelolaUserActivity.class));
    }
    public void toAccountUmum(View view) {
        startActivity(new Intent(HomeActivityAdmin.this, AccountActivity.class));
    }
    public void toLogoutUmum(View view) {
        SharedPreferences sharedPrefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.clear();
        editor.commit();
        startActivity(new Intent(HomeActivityAdmin.this, MenuLoginActivity.class));
    }

    public void toPengajuanPerjadin(View view) {

        download_riwayat_pdf(url_export_riwayat);
        startActivity(new Intent(HomeActivityAdmin.this, PengajuanPerjadiinAdmin.class));

    }

    public void download_riwayat_pdf(String aUrl){
        AndroidNetworking.download(aUrl, "/storage/emulated/0/", "riwayat_perjadin.pdf")
                .setTag("downloadTest")
                .setPriority(Priority.MEDIUM)
                .addHeaders("Authorization", "Basic YnNyZTpzZWN1cmV0cmFuc2FjdGlvbnMhISE=")
                .build()
                .setDownloadProgressListener(new DownloadProgressListener() {
                    @Override
                    public void onProgress(long bytesDownloaded, long totalBytes) {
                        // do anything with progress
                    }
                })
                .startDownload(new DownloadListener() {
                    @Override
                    public void onDownloadComplete() {
                        // do anything after completion
//                        progressDialog.dismiss();
                    }
                    @Override
                    public void onError(ANError error) {
                        // handle error
                    }
                });
    }

    public void download_user_pdf(String aUrl){
        AndroidNetworking.download(aUrl, "/storage/emulated/0/", "user.pdf")
                .setTag("downloadTest")
                .setPriority(Priority.MEDIUM)
                .addHeaders("Authorization", "Basic YnNyZTpzZWN1cmV0cmFuc2FjdGlvbnMhISE=")
                .build()
                .setDownloadProgressListener(new DownloadProgressListener() {
                    @Override
                    public void onProgress(long bytesDownloaded, long totalBytes) {
                        // do anything with progress
                    }
                })
                .startDownload(new DownloadListener() {
                    @Override
                    public void onDownloadComplete() {
                        // do anything after completion
//                        progressDialog.dismiss();
                    }
                    @Override
                    public void onError(ANError error) {
                        // handle error
                    }
                });
    }
}
