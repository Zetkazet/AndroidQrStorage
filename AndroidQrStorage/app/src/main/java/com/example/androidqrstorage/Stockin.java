package com.example.androidqrstorage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.DecodeCallback;
import com.google.zxing.Result;

public class Stockin extends AppCompatActivity {

    private CodeScanner mCodeScanner;

    private int counter;
    private String productid, productname;
    private String pidk,pnmk;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stockin);

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

                        Intent i = new Intent(Stockin.this, Stockinphp.class);
                        i.putExtra("productid",productid);
                        i.putExtra("productname",productname);
                        startActivity(i);




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