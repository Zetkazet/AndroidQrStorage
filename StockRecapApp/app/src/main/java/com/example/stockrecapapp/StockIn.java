package com.example.stockrecapapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.DecodeCallback;
import com.google.zxing.Result;

public class StockIn extends AppCompatActivity {

    private CodeScanner mCodeScanner;

    private int counter;
    private String productid, productname;
    private String pidk,pnmk;

    public static final String TAG_ID = "id";
    public static final String TAG_USERNAME = "username";
    public static final String TAG_TYPE = "scantype";

    String id, username, scantype;
    SharedPreferences sharedpreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock_in);

        sharedpreferences = getSharedPreferences(LoginPage.my_shared_preferences, Context.MODE_PRIVATE);

        scantype = getIntent().getStringExtra(TAG_TYPE);
        id = getIntent().getStringExtra(TAG_ID);
        username = getIntent().getStringExtra(TAG_USERNAME);

        CodeScannerView scannerView = findViewById(R.id.scanner_view);
        mCodeScanner = new CodeScanner(this, scannerView);
        mCodeScanner.setDecodeCallback(new DecodeCallback() {
            @Override
            public void onDecoded(@NonNull final Result result) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
//                        Toast.makeText(Stockin.this, result.getText(), Toast.LENGTH_SHORT).show();
                        String convert = result.getText();
                        String[] scan = convert.split("\\s");
                        pidk = "pid";
                        pnmk = "pname";


                        for(counter=0; counter<scan.length;counter++){
                            if(pidk.equals(scan[counter])){
                                productid = scan[counter+1];

                            }
                            if(pnmk.equals(scan[counter])){
                                counter+=1;
                                productname = scan[counter] + " ";
                                counter+=1;
                                for(;counter<scan.length;counter++){
                                    productname = productname + scan[counter] + " ";
                                }
                            }
                        }
                        if(scantype.equals("in")) {
                            Intent i = new Intent(StockIn.this, StockInPhp.class);
                            i.putExtra(TAG_TYPE, scantype);
                            i.putExtra("productid", productid);
                            i.putExtra("productname", productname);
                            i.putExtra(TAG_ID, id);
                            i.putExtra(TAG_USERNAME, username);
                            finish();
                            startActivity(i);
                        }

                        else if(scantype.equals("out")){
                            Intent i = new Intent(StockIn.this, StockInPhp.class);
                            i.putExtra(TAG_TYPE, scantype);
                            i.putExtra("productid", productid);
                            i.putExtra("productname", productname);
                            i.putExtra(TAG_ID, id);
                            i.putExtra(TAG_USERNAME, username);
                            finish();
                            startActivity(i);

                        }




                    }
                });
            }
        });
        scannerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCodeScanner.startPreview();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        mCodeScanner.startPreview();
    }

    @Override
    protected void onPause() {
        mCodeScanner.releaseResources();
        super.onPause();
    }


}