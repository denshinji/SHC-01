package com.mobile.laporperjadin.umum;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
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
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.mobile.laporperjadin.R;
import com.mobile.laporperjadin.SuksesActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Currency;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import me.abhinay.input.CurrencyEditText;
import me.abhinay.input.CurrencySymbols;

public class FormPengajuan extends AppCompatActivity implements View.OnClickListener {
    public static final String PREFS_NAME = "MyPrefsFile";
    public static String URL = "http://muhyudi.my.id/api_android/buat_pengajuan.php";
    MaterialButton btnAjukan;
    CurrencyEditText pesawat,penginapan, taksi_bandara, taksi_daerah, uang_harian;
    EditText nama, kota, berangkat, kembali;
    String s_nama, s_kota, s_berangkat, s_kembali, s_pesawat, s_penginapan, s_taksi_bandara, s_taksi_daerah, s_uang_harian;
    String id_user;
    private ProgressDialog progressDialog;
    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat simpleDateFormat;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_pengajuan);


        SharedPreferences settings = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        id_user = settings.getString("id_user", "default");

        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd",Locale.US);

        btnAjukan = findViewById(R.id.btnAjukanUmum);
        nama = findViewById(R.id.in_namePengajuan);
        kota = findViewById(R.id.in_kotaTujuan);
        berangkat = findViewById(R.id.in_tanggalBerangkat);
        berangkat.setOnClickListener(this);
        kembali = findViewById(R.id.in_tanggalKembali);
        kembali.setOnClickListener(this);
        pesawat = findViewById(R.id.in_biayaPesawat);
        setEdt(pesawat);
        penginapan = findViewById(R.id.in_biayaPenginapan);
        setEdt(penginapan);
        taksi_bandara = findViewById(R.id.in_biayaTaksi);
        setEdt(taksi_bandara);
        taksi_daerah = findViewById(R.id.in_biayaTaksiDaerah);
        setEdt(taksi_daerah);
        uang_harian = findViewById(R.id.in_uangHarian);
        setEdt(uang_harian);


        progressDialog = new ProgressDialog(FormPengajuan.this);

        btnAjukan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                s_nama = nama.getText().toString();
                s_kota = kota.getText().toString();
                s_berangkat = berangkat.getText().toString();
                s_kembali = kembali.getText().toString();
                s_pesawat = ""+pesawat.getCleanIntValue();
                s_penginapan = ""+penginapan.getCleanIntValue();
                s_taksi_bandara = ""+taksi_bandara.getCleanIntValue();
                s_taksi_daerah = ""+taksi_daerah.getCleanIntValue();
                s_uang_harian = ""+uang_harian.getCleanIntValue();




           progressDialog.setMessage("Mohon Tunggu....");
                progressDialog.show();

                if(s_nama.equals("") || s_kota.equals("") || s_berangkat.equals("") || s_kembali.equals("") || s_pesawat.equals("")|| s_penginapan.equals("")||s_taksi_bandara.equals("")
                ||s_taksi_daerah.equals("") || s_uang_harian.equals("")
                ){ progressDialog.dismiss();
                    Toast.makeText(FormPengajuan.this, "Silahkan lengkapi data", Toast.LENGTH_SHORT).show();
                }else{
                    buat_pengaduan(id_user, s_nama, s_kota, s_berangkat, s_kembali, s_pesawat, s_penginapan, s_taksi_bandara, s_taksi_daerah, s_uang_harian);
                }






            }
        });


    }

    private void setEdt(CurrencyEditText target) {
        target.setCurrency(CurrencySymbols.INDONESIA);
        target.setDecimals(true);
        target.setSeparator(".");
    }


    private void showDateDialog(final EditText edt_target){

        Calendar newCalendar = Calendar.getInstance();

        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {


                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);

                edt_target.setText(simpleDateFormat.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        datePickerDialog.show();
    }


    public void buat_pengaduan(final String id_user, final String nl, final String kt, final String tb,
                               final String tk,
                               final String bp,
                               final String bp2,
                               final String bt,
                               final String bt2,
                               final String uh ) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    progressDialog.dismiss();
                    JSONObject jsonObject = new JSONObject(response);
                    String success = jsonObject.getString("status");

//                    JSONArray jsonArray = jsonObject.getJSONArray("response");
                    if (success.equals("1")) {
                        Intent intent = new Intent(getApplicationContext(), SuksesActivity.class);
                        intent.putExtra("result", "ajukan");
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
                Toast.makeText(getApplicationContext(), "" + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id_user", id_user);
                params.put("nama_lengkap", nl);
                params.put("kota_tujuan", kt);
                params.put("tgl_berangkat", tb);
                params.put("tgl_kembali", tk);
                params.put("biaya_pesawat", bp);
                params.put("biaya_penginapan", bp2);
                params.put("biaya_taksi_bandara", bt);
                params.put("biaya_taksi_daerah", bt2);
                params.put("uang_harian", uh);

                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.in_tanggalBerangkat:
                showDateDialog(berangkat);
                break;
            case R.id.in_tanggalKembali:
                showDateDialog(kembali);
                break;
        }
    }
}
