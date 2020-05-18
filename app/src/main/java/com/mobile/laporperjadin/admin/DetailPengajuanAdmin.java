package com.mobile.laporperjadin.admin;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.button.MaterialButton;
import com.mobile.laporperjadin.AccountActivity;
import com.mobile.laporperjadin.R;
import com.mobile.laporperjadin.SuksesActivity;
import com.mobile.laporperjadin.model.Pengajuan;
import com.mobile.laporperjadin.model.PengajuanAdmin;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class DetailPengajuanAdmin extends AppCompatActivity {
    public static String PENGAJUAN_ADMIN_KEY = "pengajuan_admin_key";
    private String URL_UPDATE_STATUS = "http://muhyudi.my.id/api_android/update_status_pengajuan.php";
    private String nama, kota, berangkat, kembali, pesawat, penginapan, taksi_bandara, taksi_daerah, uang_harian,status,result,result_verif,code, id;
    private TextView t_nama, t_kota, t_berangkat, t_kembali, t_pesawat, t_penginapan, t_taksi_bandara, t_taksi_daerah, t_uang_harian,t_code;
    private Spinner t_status;
    private ProgressDialog progressDialog;
    private MaterialButton btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_pengajuan_admin);
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
        t_code = findViewById(R.id.txt_code_pengajuan_admin);
        btnSave = findViewById(R.id.btnSaveAdmin);
        t_status.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(t_status.getSelectedItem().toString().equals("Ditolak")){
                    result_verif = "1";
                }else if(t_status.getSelectedItem().toString().equals("Disetujui")){
                    result_verif = "2";
                } else {
                    result_verif = "0";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        result = "updatepengajuan";
        progressDialog = new ProgressDialog(DetailPengajuanAdmin.this);

        PengajuanAdmin list = getIntent().getParcelableExtra(PENGAJUAN_ADMIN_KEY);
        nama = list.getNama();
        id = list.getId_pengajuan();
        kota = list.getKotaTujuan();
        berangkat = list.getTglBerangkat();
        kembali = list.getTglKembali();
        pesawat = list.getBiayaPesawat();
        penginapan = list.getBiayaPenginapan();
        taksi_bandara = list.getBiayaTaksiBandara();
        taksi_daerah = list.getBiayaTaksiDaerah();
        uang_harian = list.getUangTunai();
        status = list.getStatusPengajuan();
        code = list.getId();
        String [] items = {"Menunggu Verifikasi","Ditolak","Disetujui"};
        ArrayAdapter arrayAdapter = new ArrayAdapter(getApplicationContext(),R.layout.support_simple_spinner_dropdown_item,items);
        t_status.setAdapter(arrayAdapter);
        if(status.equals("0")){
          t_status.setSelection(arrayAdapter.getPosition("Menunggu Verifikasi"));
        }
        if(status.equals("1")){
            t_status.setSelection(arrayAdapter.getPosition("Ditolak"));
        }
        if(status.equals("2")){
            t_status.setSelection(arrayAdapter.getPosition("Disetujui"));
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
        t_code.setText("Detail pengajuan dengan code : "+code.toString());

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.setMessage("Mohon Tunggu....");
                progressDialog.show();
                    updateStatusPengajuan(id, result_verif, result);
              //  Toast.makeText(DetailPengajuanAdmin.this, ""+code+"\n"+result_verif+"\n"+result+"\n"+id, Toast.LENGTH_SHORT).show();
            }
        });


    }



    public void updateStatusPengajuan(final String id_pengajuan, final String status, final String result){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_UPDATE_STATUS, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    progressDialog.dismiss();
                    JSONObject jsonObject = new JSONObject(response);
                    String success = jsonObject.getString("status");

//                    JSONArray jsonArray = jsonObject.getJSONArray("response");
                    if (success.equals("1")) {
                        Intent intent = new Intent(DetailPengajuanAdmin.this, SuksesActivity.class);
                        intent.putExtra("result",result);
                        startActivity(intent);
                    } else {
                        Toast.makeText(getApplicationContext(),
                                "Gagal, silahkan coba kembali ",
                                Toast.LENGTH_SHORT)
                                .show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),
                            "Error  : " + e.toString(),
                            Toast.LENGTH_SHORT)
                            .show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("update", error.getMessage());
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id_pengajuan", id_pengajuan);
                params.put("status", status);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }


}
