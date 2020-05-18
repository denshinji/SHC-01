package com.mobile.laporperjadin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.button.MaterialButton;
import com.mobile.laporperjadin.admin.DetailUser;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {
    private String result = "register";
    private MaterialButton btnToVerif;
    private String URL_REGIST = "http://muhyudi.my.id/api_android/model_register.php";
    private String nama,jabatan,nip,email,username,password;
    private EditText e_nama_engkap,e_jabatan,e_nip,e_email,e_username,e_password;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        btnToVerif = findViewById(R.id.btnRegisterToVerif);
        e_nama_engkap = findViewById(R.id.in_name_register);
        e_jabatan = findViewById(R.id.in_jabatan);
        e_nip = findViewById(R.id.in_nip);
        e_email = findViewById(R.id.in_email);
        e_username = findViewById(R.id.in_username_register);
        e_password = findViewById(R.id.in_password_register);



        progressDialog = new ProgressDialog(RegisterActivity.this);

        btnToVerif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nama = e_nama_engkap.getText().toString();
                jabatan = e_jabatan.getText().toString();
                nip = e_nip.getText().toString();
                email = e_email.getText().toString();
                username = e_username.getText().toString();
                password = e_password.getText().toString();

                progressDialog.setMessage("Mohon Tunggu....");
                progressDialog.show();

                if(nama.isEmpty() || jabatan.isEmpty() || nip.isEmpty() || email.isEmpty() || username.isEmpty() || password.isEmpty()){
                    progressDialog.dismiss();
                    Toast.makeText(RegisterActivity.this, "Semua field harus di isi", Toast.LENGTH_SHORT).show();
                } else {
                    registerUser(result);
                }
            }
        });

    }

    public void registerUser(final String result){
        final String uName = e_nama_engkap.getText().toString().trim();
        final String uJabatan = e_jabatan.getText().toString().trim();
        final String uNip = e_nip.getText().toString().trim();
        final String uEmail = e_email.getText().toString().trim();
        final String uUsername = e_username.getText().toString().trim();
        final String uPassword = e_password.getText().toString().trim();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_REGIST, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    progressDialog.dismiss();
                    JSONObject jsonObject = new JSONObject(response);
                    String success = jsonObject.getString("status");

//                    JSONArray jsonArray = jsonObject.getJSONArray("response");
                    if (success.equals("1")) {
                        Intent intent = new Intent(RegisterActivity.this, SuksesActivity.class);
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
                params.put("nama", uName);
                params.put("jabatan", uJabatan);
                params.put("nip", uNip);
                params.put("email", uEmail);
                params.put("username", uUsername);
                params.put("password", uPassword);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
