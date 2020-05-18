package com.mobile.laporperjadin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
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
import com.mobile.laporperjadin.admin.HomeActivityAdmin;
import com.mobile.laporperjadin.kepalabidang.HomeActivityKabid;
import com.mobile.laporperjadin.umum.HomeActivityUmum;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    private EditText username, password;
    private MaterialButton btnLogin;
    public static String URL = "http://muhyudi.my.id/api_android/model_login.php";

    private ProgressDialog progressDialog;
    public static final String PREFS_NAME = "MyPrefsFile";

    String id_user;
    String username_login;
    String password_login;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        if(Build.VERSION.SDK_INT > Build.VERSION_CODES.M&& checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[] {android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1001);
        }

        progressDialog = new ProgressDialog(LoginActivity.this);
        username = findViewById(R.id.in_username);
        password = findViewById(R.id.in_password);
        btnLogin = findViewById(R.id.btnLoginToHome);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username_login = username.getText().toString();
                password_login = password.getText().toString();

                progressDialog.setMessage("Mohon Tunggu....");
                progressDialog.show();
                if(username_login.isEmpty()){
                    username.setError("Username tidak boleh kosong !");
                }else if(password_login.isEmpty()){
                    password.setError("Password tidak boleh kosong !");
                }else {

                    auth_user(username_login, password_login);

                }

            }
        });

    }


    public void auth_user(final String usernamee, final String passwordd){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    progressDialog.dismiss();
                    JSONObject jsonObject = new JSONObject(response);
                    String success = jsonObject.getString("status");

//                    JSONArray jsonArray = jsonObject.getJSONArray("response");
                    if (success.equals("1")) {
                        for (int i = 0; i < 11; i++) {
//                            JSONObject jsonObject1 = jsonObject.getJSONObject();


                            id_user = jsonObject.getString("id_user").trim();
                            String username = jsonObject.getString("username").trim();
                            String level = jsonObject.getString("level").trim();
                            String status = jsonObject.getString("statusakun").trim();





                            SharedPreferences settings = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = settings.edit();
                            editor.putString("id_user", id_user);
                            editor.putString("username", username);
                            editor.putString("level", level);
                            editor.putString("status", status);



                            editor.commit();


                            if(level.equals("1") && status.equals("1") ){
                                Intent intent = new Intent(getApplicationContext(), HomeActivityUmum.class);
                                intent.putExtra("username", username);
                                intent.putExtra("id_user", id_user);


                                startActivity(intent);
                            } else if(level.equals("1") && status.equals("0") ){
                                Toast.makeText(LoginActivity.this, "Akun anda belum aktif", Toast.LENGTH_SHORT).show();
                            } else if(level.equals("2")){
                                Intent intent = new Intent(getApplicationContext(), HomeActivityAdmin.class);
                                intent.putExtra("username", username);
                                intent.putExtra("id_user", id_user);


                                startActivity(intent);
                            }else if(level.equals("3")){
                                Intent intent = new Intent(getApplicationContext(), HomeActivityKabid.class);
                                intent.putExtra("username", username);
                                intent.putExtra("id_user", id_user);


                                startActivity(intent);
                            }


                        }
                    } else {
                        Toast.makeText(LoginActivity.this,
                                "ID. User dan Password tidak ditemukan! ",
                                Toast.LENGTH_SHORT)
                                .show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(LoginActivity.this,
                            "Error login : " + e.toString(),
                            Toast.LENGTH_SHORT)
                            .show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(LoginActivity.this, ""+error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("username", usernamee);
                params.put("password", passwordd);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) { switch (requestCode){
        case 1001:
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this, "Ijin DIterima!", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "Ijin Ditolak!", Toast.LENGTH_SHORT).show();
                finish();
            }
    }
    }
}

