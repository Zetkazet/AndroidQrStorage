package com.example.androidqrstorage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;



public class Stockinphp extends AppCompatActivity {



    private TextView PIDview,PNMview;

    private Button sendbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stockinphp);

        PIDview = findViewById(R.id.pidtextview);
        PNMview = findViewById(R.id.pnmtextview);

        sendbutton = findViewById(R.id.sendbutton);

        String productid = getIntent().getStringExtra("productid");
        String productname = getIntent().getStringExtra("productname");

        PIDview.setText(productid);
        PNMview.setText(productname);




        sendbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });






    }

}