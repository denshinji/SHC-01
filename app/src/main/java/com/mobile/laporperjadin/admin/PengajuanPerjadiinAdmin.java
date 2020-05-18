package com.mobile.laporperjadin.admin;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.mobile.laporperjadin.PDFViewer;
import com.mobile.laporperjadin.R;
import com.mobile.laporperjadin.adapter.TableAdapter;
import com.mobile.laporperjadin.adapter.TableAdapterAdmin;
import com.mobile.laporperjadin.kepalabidang.DaftarPengajuanKabid;
import com.mobile.laporperjadin.model.Pengajuan;
import com.mobile.laporperjadin.model.PengajuanAdmin;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PengajuanPerjadiinAdmin extends AppCompatActivity {
    private String URL_ADMIN = "http://muhyudi.my.id/api_android/get_pengajuan_admin.php";
    RecyclerView recyclerView;
    private TableAdapterAdmin adapter;
    private ArrayList<PengajuanAdmin> listData = new ArrayList<>();
    private String resultPdf = "pengajuan";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pengajuan_perjadiin_admin);

        recyclerView = findViewById(R.id.rvRiwayatPengajuanUmum);
        adapter = new TableAdapterAdmin(listData,getApplicationContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);

        getRiwayatUmum();

    }


    public void toIntentPdfPengajuan(View view) {
        Intent intent = new Intent(PengajuanPerjadiinAdmin.this, PDFViewer.class);
        intent.putExtra("pdfIntentPdf",resultPdf);
        startActivity(intent);
    }

    private void getRiwayatUmum(){
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URL_ADMIN, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if (response.length() > 0) {
                    for (int i = 0; i < response.length(); i++) {
                        try {

                            JSONObject jsonObject = response.getJSONObject(i);


                            PengajuanAdmin data = new PengajuanAdmin();
                            data.setNo(jsonObject.getString("no"));
                            data.setId_pengajuan(jsonObject.getString("id"));
                            data.setId(jsonObject.getString("id_pengajuan"));
                            data.setId_user(jsonObject.getString("id_user"));
                            data.setNama(jsonObject.getString("nama_lengkap"));
                            data.setKotaTujuan(jsonObject.getString("kota_tujuan"));
                            data.setTglBerangkat(jsonObject.getString("tanggal_berangkat"));
                            data.setTglKembali(jsonObject.getString("tanggal_kembali"));
                            data.setBiayaPesawat(jsonObject.getString("biaya_pesawat"));
                            data.setBiayaPenginapan(jsonObject.getString("biaya_penginapan"));
                            data.setBiayaTaksiBandara(jsonObject.getString("biaya_taksi_bandara"));
                            data.setBiayaTaksiDaerah(jsonObject.getString("biaya_taksi_daerah"));
                            data.setUangTunai(jsonObject.getString("uang_harian"));
                            data.setTglPengajuan(jsonObject.getString("tanggal_pengajuan"));
                            data.setStatusPengajuan(jsonObject.getString("status"));


/*
                            Intent intent = new Intent(getActivity().getBaseContext(), DetailDisposisiMasuk.class);
                            intent.putExtra("id_surat", id_surat);
                            getActivity().startActivity(intent);
*/




                            listData.add(data);
                            adapter = new TableAdapterAdmin(listData, PengajuanPerjadiinAdmin.this);
                            recyclerView.setAdapter(adapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }else{

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        Volley.newRequestQueue(this).add(jsonArrayRequest);
    }
}
