package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button admin, mahasiswa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        admin = (Button) findViewById(R.id.btnLoginAdm);
        mahasiswa = (Button) findViewById(R.id.btnLoginMhs);

        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent adminLoginHome =new Intent(MainActivity.this, LoginAdmin.class);
                startActivity(adminLoginHome);
            }
        });

        mahasiswa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mhsLoginHome =new Intent(MainActivity.this, LoginMahasiswa.class);
                startActivity(mhsLoginHome);
            }
        });

    }
}