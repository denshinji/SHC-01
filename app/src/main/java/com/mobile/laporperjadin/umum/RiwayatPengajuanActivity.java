package com.mobile.laporperjadin.umum;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.button.MaterialButton;
import com.mobile.laporperjadin.R;
import com.mobile.laporperjadin.adapter.TableAdapter;
import com.mobile.laporperjadin.model.Pengajuan;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class RiwayatPengajuanActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    TableAdapter adapter;
    private String URL = "http://muhyudi.my.id/api_android/get_pengajuan.php?id_user=";
    String URL_LENGKAP;
    public static final String PREFS_NAME = "MyPrefsFile";
    String id_user;
    MaterialButton btnExport;

    ArrayList<Pengajuan> listData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_riwayat_pengajuan);

        btnExport = findViewById(R.id.btnIntentPdfPengajuanKabid);


        SharedPreferences settings = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        id_user = settings.getString("id_user", "default");
        URL_LENGKAP = URL + id_user;
        recyclerView = findViewById(R.id.rvRiwayatPengajuanUmum);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);

        listData = new ArrayList<Pengajuan>();
        getRiwayatUmum();


    }

//    private List<Pengajuan> getRiwayat() {
//        final List<Pengajuan> pengajuans = new ArrayList<>();
//
//        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URL_LENGKAP, new Response.Listener<JSONArray>() {
//            @Override
//            public void onResponse(JSONArray response) {
//                if(response.length() > 0){
//                    for (int i = 0; i < response.length(); i++) {
//                        try {
//
//                            JSONObject jsonObject = response.getJSONObject(i);
//
//                            pengajuans.add(new Pengajuan(jsonObject.getString("id_pengajuan"),
//                                    jsonObject.getString("id_user"),
//                                    jsonObject.getString("nama_lengkap"),
//                                    jsonObject.getString("kota_tujuan"),
//                                    jsonObject.getString("tanggal_berangkat"),
//                                    jsonObject.getString("tanggal_kembali"),
//                                    jsonObject.getString("biaya_pesawat"),
//                                    jsonObject.getString("biaya_penginapan"),
//                                    jsonObject.getString("biaya_taksi_bandara"),
//                                    jsonObject.getString("biaya_taksi_daerah"),
//                                    jsonObject.getString("biaya_uang_harian"),
//                                    jsonObject.getString("tanggal_pengajuan"),
//                                    jsonObject.getString("status")
//                                    ));
//
//
//
//
//
//
///*
//                            Intent intent = new Intent(getActivity().getBaseContext(), DetailDisposisiMasuk.class);
//                            intent.putExtra("id_surat", id_surat);
//                            getActivity().startActivity(intent);
//*/
//
//
//
//
//
//
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }
//
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//            }
//        });
//
//        Volley.newRequestQueue(this).add(jsonArrayRequest);
//
////        pengajuans.add(new Pengajuan("JD001","Arifin","Bandung","20 Mei","22 Mei","1000000","500000","100000","40000","1000000","Disetujui","1"));
////        pengajuans.add(new Pengajuan("JD002","Dedek","Bandung","20 Mei","22 Mei","1000000","500000","100000","40000","1000000","Disetujui","2"));
////        pengajuans.add(new Pengajuan("JD002","Dedek","Bandung","20 Mei","22 Mei","1000000","500000","100000","40000","1000000","Disetujui","2"));
////        pengajuans.add(new Pengajuan("JD002","Dedek","Bandung","20 Mei","22 Mei","1000000","500000","100000","40000","1000000","Disetujui","2"));
////        pengajuans.add(new Pengajuan("JD002","Dedek","Bandung","20 Mei","22 Mei","1000000","500000","100000","40000","1000000","Disetujui","2"));
////        pengajuans.add(new Pengajuan("JD002","Dedek","Bandung","20 Mei","22 Mei","1000000","500000","100000","40000","1000000","Disetujui","2"));
////        pengajuans.add(new Pengajuan("JD002","Dedek","Bandung","20 Mei","22 Mei","1000000","500000","100000","40000","1000000","Disetujui","2"));
////        pengajuans.add(new Pengajuan("JD002","Dedek","Bandung","20 Mei","22 Mei","1000000","500000","100000","40000","1000000","Disetujui","2"));
////        pengajuans.add(new Pengajuan("JD002","Dedek","Bandung","20 Mei","22 Mei","1000000","500000","100000","40000","1000000","Disetujui","2"));
//        return pengajuans;
//    }

    private void getRiwayatUmum(){
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URL_LENGKAP, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if (response.length() > 0) {
                    for (int i = 0; i < response.length(); i++) {
                        try {

                            JSONObject jsonObject = response.getJSONObject(i);


                            Pengajuan data = new Pengajuan();
                            data.setNo(jsonObject.getString("no"));
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
                            adapter = new TableAdapter(listData,RiwayatPengajuanActivity.this);
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
