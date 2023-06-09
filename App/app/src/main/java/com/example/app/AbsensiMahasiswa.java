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

public class AbsensiMahasiswa extends AppCompatActivity {

    EditText nim, nama, kelas, matakuliah, status;
    Button back, presensi;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_absensi_mahasiswa);

        nim = (EditText) findViewById(R.id.nim);
        nama = (EditText) findViewById(R.id.nama);
        kelas = (EditText) findViewById(R.id.kelas);
        matakuliah = (EditText) findViewById(R.id.matakuliah);
        status = (EditText) findViewById(R.id.status);
        back = (Button) findViewById(R.id.loginMhsBack);
        presensi = (Button) findViewById(R.id.presensi);
        progressDialog = new ProgressDialog(AbsensiMahasiswa.this);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent backLoginMhs = new Intent(AbsensiMahasiswa.this, LoginMahasiswa.class);
                startActivity(backLoginMhs);
            }
        });

        presensi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sNim = nim.getText().toString();
                String sNama = nama.getText().toString();
                String sKelas = kelas.getText().toString();
                String sMatakuliah = matakuliah.getText().toString();
                String sStatus = status.getText().toString();

                if(!sNim.equals("") && !sNama.equals("") && !sKelas.equals("") && !sMatakuliah.equals("") && !sStatus.equals("")){
                    createDataToServer(sNim, sNama, sKelas, sMatakuliah, sStatus);
                    Intent loginIntent = new Intent(AbsensiMahasiswa.this, PresensiSelesai.class);
                    startActivity(loginIntent);
                } else {
                    Toast.makeText(getApplicationContext(), "Gagal!!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void createDataToServer(final String nim, final String nama, final String kelas, final String matakuliah, final String status){
        if(checkNetworkConnection()){
            progressDialog.show();
            StringRequest stringRequest = new StringRequest(Request.Method.POST, DbContract.SERVER_PRESENSI_MHS_URL,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                String resp = jsonObject.getString("server_response");
                                if(resp.equals("[{\"status\":\"OK\"}]")){
                                    Toast.makeText(getApplicationContext(), "Presensi Berhasil", Toast.LENGTH_SHORT).show();
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
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("nim", nim);
                    params.put("nama", nama);
                    params.put("kelas", kelas);
                    params.put("matakuliah", matakuliah);
                    params.put("status", status);
                    return params;
                }
            };
            VolleyConnection.getInstance(AbsensiMahasiswa.this).addToRequestQueue(stringRequest);
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