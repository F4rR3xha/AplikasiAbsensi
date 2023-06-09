package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PresensiSelesai extends AppCompatActivity {

    Button selesai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_presensi_selesai);

        selesai = (Button) findViewById(R.id.presensiSelesai);

        selesai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent presensiSelesai = new Intent(PresensiSelesai.this, MainActivity.class);
                startActivity(presensiSelesai);
            }
        });
    }
}