package com.mobile.laporperjadin.admin;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
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
import com.mobile.laporperjadin.PDFViewer;
import com.mobile.laporperjadin.R;
import com.mobile.laporperjadin.SuksesActivity;
import com.mobile.laporperjadin.model.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class DetailUser extends AppCompatActivity {
    private String simpanUser = "simpanuser";
    private String hapusUser = "hapususer";
    private String id_user,nama,jabatan,nip,email,username,status,result_verif,result_update,result_hapus;
    private EditText t_nama,t_jabatan,t_nip,t_email,t_username;
    private TextView t_detail;
    private Spinner t_status;
    private MaterialButton btnSaveUpdate,btnHapusUser;
    private ProgressDialog progressDialog;
    private String URL_UPDATE_USER = "http://muhyudi.my.id/api_android/update_status_user.php";
    private String URL_DELETE_USER = "http://muhyudi.my.id/api_android/hapus_user.php";


    public static String USER_KEY = "user_key";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_user);

        User user = getIntent().getParcelableExtra(USER_KEY);

        id_user = user.getIdUser();
        nama = user.getNama();
        jabatan = user.getJabatan();
        nip = user.getNip();
        email = user.getEmail();
        username = user.getUsername();
        status = user.getStatus();

        progressDialog = new ProgressDialog(DetailUser.this);

        t_nama = findViewById(R.id.namaDetailUser);
        t_jabatan = findViewById(R.id.jabatanDetailUser);
        t_nip = findViewById(R.id.nipDetailUser);
        t_email = findViewById(R.id.emailDetailUser);
        t_username = findViewById(R.id.usernameDetailUser);
        t_detail = findViewById(R.id.tx_detailUser);
        t_status = findViewById(R.id.statusDetailUser);

        t_status.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(t_status.getSelectedItem().toString().equals("Daftar")){
                    result_verif = "0";
                }else if(t_status.getSelectedItem().toString().equals("Aktif")){
                    result_verif = "1";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        String [] items = {"Aktif","Daftar"};
        ArrayAdapter arrayAdapter = new ArrayAdapter(getApplicationContext(),R.layout.support_simple_spinner_dropdown_item,items);
        t_status.setAdapter(arrayAdapter);
        if(status.equals("0")){
            t_status.setSelection(arrayAdapter.getPosition("Daftar"));
        }
        if(status.equals("1")){
            t_status.setSelection(arrayAdapter.getPosition("Aktif"));
        }

        t_nama.setText(nama.toString());
        t_jabatan.setText(jabatan.toString());
        t_nip.setText(nip.toString());
        t_email.setText(email.toString());
        t_username.setText(username.toString());
        t_detail.setText("Detail user : "+nama.toString());

        btnSaveUpdate = findViewById(R.id.btnSaveUser);
        btnSaveUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                result_update = "updateuser";
                progressDialog.setMessage("Mohon Tunggu....");
                progressDialog.show();
                updateStatusUser(id_user, result_verif, result_update);
            }
        });
        btnHapusUser = findViewById(R.id.btnHapusUser);
        btnHapusUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                result_hapus = "hapususer";
                progressDialog.setMessage("Mohon Tunggu....");
                progressDialog.show();
                HapusUser(id_user, result_hapus);
            }
        });



    }

    public void updateStatusUser(final String id_user, final String status, final String result){
        final String uName = t_nama.getText().toString().trim();
        final String uJabatan = t_jabatan.getText().toString().trim();
        final String uNip = t_nip.getText().toString().trim();
        final String uEmail = t_email.getText().toString().trim();
        final String uUsername = t_username.getText().toString().trim();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_UPDATE_USER, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    progressDialog.dismiss();
                    JSONObject jsonObject = new JSONObject(response);
                    String success = jsonObject.getString("status");

//                    JSONArray jsonArray = jsonObject.getJSONArray("response");
                    if (success.equals("1")) {
                        Intent intent = new Intent(DetailUser.this, SuksesActivity.class);
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

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id_user", id_user);
                params.put("nama", uName);
                params.put("jabatan", uJabatan);
                params.put("nip", uNip);
                params.put("email", uEmail);
                params.put("username", uUsername);
                params.put("status", status);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
    public void HapusUser(final String id_user, final String result_hapus){

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_DELETE_USER, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    progressDialog.dismiss();
                    JSONObject jsonObject = new JSONObject(response);
                    String success = jsonObject.getString("status");

//                    JSONArray jsonArray = jsonObject.getJSONArray("response");
                    if (success.equals("1")) {
                        Intent intent = new Intent(DetailUser.this, SuksesActivity.class);
                        intent.putExtra("result",result_hapus);
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

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id_user", id_user);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

}
