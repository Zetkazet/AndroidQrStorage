package com.example.stockrecapapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button btn_logout,btn_stock_in,btn_stock_out;
    TextView txt_id, txt_username;
    String id, username , scantype;

    SharedPreferences sharedpreferences;

    public static final String TAG_ID = "id";
    public static final String TAG_USERNAME = "username";
    public static final String TAG_TYPE = "scantype";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt_id = (TextView) findViewById(R.id.txt_id);
        txt_username = (TextView) findViewById(R.id.txt_username);
        btn_logout = (Button) findViewById(R.id.btn_logout);

        btn_stock_in = findViewById(R.id.btn_stock_in);
        btn_stock_out = findViewById(R.id.btn_stock_out);

        sharedpreferences = getSharedPreferences(LoginPage.my_shared_preferences, Context.MODE_PRIVATE);

        id = getIntent().getStringExtra(TAG_ID);
        username = getIntent().getStringExtra(TAG_USERNAME);

        txt_id.setText("ID : " + id);
        txt_username.setText("USERNAME : " + username);

        btn_logout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                // update login session ke FALSE dan mengosongkan nilai id dan username
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putBoolean(LoginPage.session_status, false);
                editor.putString(TAG_ID, null);
                editor.putString(TAG_USERNAME, null);
                editor.commit();

                Intent intent = new Intent(MainActivity.this, LoginPage.class);
                finish();
                startActivity(intent);
            }
        });

        btn_stock_in.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                scantype = "in";

                Intent intent = new Intent(MainActivity.this, StockIn.class);
                intent.putExtra(TAG_TYPE, scantype );
                intent.putExtra(TAG_ID, id);
                intent.putExtra(TAG_USERNAME, username);
                finish();
                startActivity(intent);

            };


        });

        btn_stock_out.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                scantype = "out";

                Intent intent = new Intent(MainActivity.this, StockIn.class);
                intent.putExtra(TAG_TYPE, scantype);
                intent.putExtra(TAG_ID, id);
                intent.putExtra(TAG_USERNAME, username);
                finish();
                startActivity(intent);

            };


        });
    }
}