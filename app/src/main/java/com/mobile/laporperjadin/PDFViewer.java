package com.mobile.laporperjadin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;

import com.github.barteksc.pdfviewer.PDFView;

import java.io.File;

public class PDFViewer extends AppCompatActivity {
    PDFView pdfView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p_d_f_viewer);

        File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), "riwayat_perjadin.pdf");
        File file_user = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), "user.pdf");

        pdfView = findViewById(R.id.pdfViewer);
        String resultPDF = getIntent().getStringExtra("pdfIntentPdf");
        if(resultPDF.equals("user")){
//            pdfView.fromAsset("daftaruser.pdf").load();
            pdfView.fromFile(file_user).load();
        }
        if (resultPDF.equals("pengajuan")){
//            pdfView.fromAsset("daftarpengajuan.pdf").load();
            pdfView.fromFile(file).load();

        }

    }
}
