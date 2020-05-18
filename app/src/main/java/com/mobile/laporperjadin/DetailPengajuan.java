package com.mobile.laporperjadin;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.mobile.laporperjadin.model.Pengajuan;

import java.text.NumberFormat;
import java.util.Locale;

public class DetailPengajuan extends AppCompatActivity {
    public static String PENGAJUAN_KEY = "pengajuan_key";
    private String nama, kota, berangkat, kembali, pesawat, penginapan, taksi_bandara, taksi_daerah, uang_harian,status,result_status,code;
    private TextView t_nama, t_kota, t_berangkat, t_kembali, t_pesawat, t_penginapan, t_taksi_bandara, t_taksi_daerah, t_uang_harian,t_status,t_code;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_pengajuan);
        Locale localeId = new Locale("in","id");
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(localeId);

        t_nama = findViewById(R.id.namaDetailUmum);
        t_kota = findViewById(R.id.kotaTujuanDetailUmum);
        t_berangkat = findViewById(R.id.tanggalBerangkatDetailUmum);
        t_kembali = findViewById(R.id.tanggalKembaliDetailUmum);
        t_pesawat = findViewById(R.id.biayaPesawatDetailUmum);
        t_penginapan = findViewById(R.id.biayaPenginapanDetailUmum);
        t_taksi_bandara = findViewById(R.id.biayaTaksiBandaraDetailUmum);
        t_taksi_daerah = findViewById(R.id.biayaTaksiDaerahDetailUmum);
        t_uang_harian = findViewById(R.id.uangHarianDetailUmum);
        t_status = findViewById(R.id.statusPengajuanDetailUmum);
        t_code = findViewById(R.id.txt_code_pengajuan);

        Pengajuan list = getIntent().getParcelableExtra(PENGAJUAN_KEY);
        nama = list.getNama();
        kota = list.getKotaTujuan();
        berangkat = list.getTglBerangkat();
        kembali = list.getTglKembali();
        pesawat = list.getBiayaPesawat();
        penginapan = list.getBiayaPenginapan();
        taksi_bandara = list.getBiayaTaksiBandara();
        taksi_daerah = list.getBiayaTaksiDaerah();
        uang_harian = list.getUangTunai();
        code = list.getId();
        status = list.getStatusPengajuan();

        if(status.equals("0")){
            result_status = "Menunggu Verifikasi";
        }
        if(status.equals("1")){
            result_status = "Ditolak";
        }
        if(status.equals("2")){
            result_status = "Disetujui";
        }


        t_nama.setText(nama.toString());
        t_kota.setText(kota.toString());
        t_berangkat.setText(berangkat.toString());
        t_kembali.setText(kembali.toString());
        t_pesawat.setText(numberFormat.format(Double.parseDouble(pesawat)));
        t_penginapan.setText(numberFormat.format(Double.parseDouble(penginapan)));
        t_taksi_bandara.setText(numberFormat.format(Double.parseDouble(taksi_bandara)));
        t_taksi_daerah.setText(numberFormat.format(Double.parseDouble(taksi_daerah)));
        t_uang_harian.setText(numberFormat.format(Double.parseDouble(uang_harian)));
        t_status.setText(result_status.toString());
        t_code.setText("Detail pengajuan dengan code : "+code.toString());


    }
}
