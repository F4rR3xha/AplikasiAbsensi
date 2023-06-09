package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class InputBerhasil extends AppCompatActivity {

    Button finish, backInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_berhasil);

        finish = (Button) findViewById(R.id.selesai);
        backInput = (Button) findViewById(R.id.inputAgain);

        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent selesai = new Intent(InputBerhasil.this, MainActivity.class);
                startActivity(selesai);
            }
        });

        backInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent backInput = new Intent(InputBerhasil.this, HomeInputAdmin.class);
                startActivity(backInput);
            }
        });
    }
}