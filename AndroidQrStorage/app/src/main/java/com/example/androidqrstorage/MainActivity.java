package com.example.androidqrstorage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    private String ServerIP,Username,Password;
    private String Server,User,Pass;

    private Button stockinbutton,stockoutbutton;

    public String ServerId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        stockinbutton =findViewById(R.id.stockin);
        stockoutbutton =findViewById(R.id.stockout);

        Server = getserverip();
        User = getUsername();
        Pass = getPassword();



        stockinbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setaccount(Server,User,Pass);

                Intent myIntent = new Intent(MainActivity.this, Stockin.class);
                MainActivity.this.startActivity(myIntent);
            }
        });

        stockoutbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent myIntent = new Intent(MainActivity.this, QrScanner.class);
                MainActivity.this.startActivity(myIntent);
            }
        });


    }

    public void setaccount(String serverip, String Userid, String Userpw){
        ServerIP = serverip;
        Username = Userid ;
        Password = Userpw ;

    }


    public String getserverip(){
        return ServerIP;
    };

    public String getUsername(){
        return Username;
    };

    public String getPassword(){
        return Password;
    };


}