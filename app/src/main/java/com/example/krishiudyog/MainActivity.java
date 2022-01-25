package com.example.krishiudyog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ImageView adminbut,custombut,producebut;
    TextView tvtitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvtitle=findViewById(R.id.tvtitle);
        custombut=findViewById(R.id.custombut);
        producebut=findViewById(R.id.producebut);
        

        custombut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,com.example.krishiudyog.ConsumerLogin.class));

            }
        });

        producebut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,com.example.krishiudyog.ProducerLogin.class));

            }
        });
    }

}