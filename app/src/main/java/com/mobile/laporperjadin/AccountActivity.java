package com.mobile.laporperjadin;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class AccountActivity extends AppCompatActivity {
    private MaterialButton btnGantiPassword;
    private String results ="";
    private EditText edtReset;
    private TextView username;
    EditText password;
    String s_password, id_user, level,s_username;
    private ProgressDialog progressDialog;
    private String URL = "http://muhyudi.my.id/api_android/ganti_password.php";
    public static final String PREFS_NAME = "MyPrefsFile";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        username = findViewById(R.id.akun_username);
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        id_user = settings.getString("id_user", "default");
        level = settings.getString("level", "default");
        s_username = settings.getString("username", "default");
        username.setText(": "+s_username);
        if(level.equals("1")){
            results = "gantipasswordumum";
        }else if(level.equals("2")){
            results = "gantipasswordadmin";
        }else if(level.equals("3")){
            results = "gantipasswordkabid";
        }
        progressDialog = new ProgressDialog(AccountActivity.this);
        btnGantiPassword = findViewById(R.id.btnGantiPassword);
        edtReset = findViewById(R.id.in_password_reset);
        btnGantiPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.setMessage("Mohon Tunggu....");
                progressDialog.show();

                s_password = edtReset.getText().toString();


                if(s_password.equals("")){
                    password.setError("Silahkan masukkan password baru");
                }else{
                    reset_password(id_user, s_password, results);
                }
            }
        });
    }

    public void reset_password(final String id_user, final String password, final String result){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    progressDialog.dismiss();
                    JSONObject jsonObject = new JSONObject(response);
                    String success = jsonObject.getString("status");

//                    JSONArray jsonArray = jsonObject.getJSONArray("response");
                    if (success.equals("1")) {
                        Intent intent = new Intent(AccountActivity.this, SuksesActivity.class);
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
                params.put("password", password);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

}
