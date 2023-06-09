package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class HomeInputAdmin extends AppCompatActivity {

    Button inputMhs, backInput;
    EditText nama, username, password;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_input_admin);

        nama = (EditText) findViewById(R.id.inputNamaMhs);
        username = (EditText) findViewById(R.id.inputUsernameMhs);
        password = (EditText) findViewById(R.id.inputPasswordMhs);
        inputMhs = (Button) findViewById(R.id.inputMhs);
        backInput = (Button) findViewById(R.id.adminInputBack);
        progressDialog = new ProgressDialog(HomeInputAdmin.this);

        backInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent backLoginAdmiin = new Intent(HomeInputAdmin.this, LoginAdmin.class);
                startActivity(backLoginAdmiin);
            }
        });

        inputMhs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sNama = nama.getText().toString();
                String sUsername = username.getText().toString();
                String sPassword = password.getText().toString();

                if(!sNama.equals("") && !sUsername.equals("") && !sPassword.equals("")){
                    createDataToServer(sNama, sUsername, sPassword);
                    Intent loginIntent = new Intent(HomeInputAdmin.this, InputBerhasil.class);
                    startActivity(loginIntent);
                } else {
                    Toast.makeText(getApplicationContext(), "Gagal!!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void createDataToServer(final String nama, final String username, final String password){
        if(checkNetworkConnection()){
            progressDialog.show();
            StringRequest stringRequest = new StringRequest(Request.Method.POST, DbContract.SERVER_INPUT_MHS_URL,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                String resp = jsonObject.getString("server_response");
                                if(resp.equals("[{\"status\":\"OK\"}]")){
                                    Toast.makeText(getApplicationContext(), "Input Data Berhasil", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(getApplicationContext(), resp, Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError{
                    Map<String, String> params = new HashMap<>();
                    params.put("nama", nama);
                    params.put("username", username);
                    params.put("password", password);
                    return params;
                }
            };
            VolleyConnection.getInstance(HomeInputAdmin.this).addToRequestQueue(stringRequest);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    progressDialog.cancel();
                }
            }, 2000);
        } else {
            Toast.makeText(getApplicationContext(), "Tidak Ada Koneksi Internet", Toast.LENGTH_SHORT).show();
        }
    }

    public boolean checkNetworkConnection(){
        ConnectivityManager connectivityManager = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }
}