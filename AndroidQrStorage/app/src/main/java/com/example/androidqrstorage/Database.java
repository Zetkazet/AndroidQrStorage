package com.example.androidqrstorage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {
    private static final String TAG= "DatabaseHelper";

    private static final String CREATE_TABLE = "CREATE TABLE StockTable (id_product varchar(16) NOT NULL, product_name varchar(255),product_stock int,PRIMARY KEY (id_product));";
    private static final String CREATE_TABLE2 = "CREATE TABLE AccountTable (id_account varchar(16) NOT NULL,account_name varchar(255),account_password varchar(255),PRIMARY KEY (id_account));";
    private static final String CREATE_TABLE3 = "CREATE TABLE ReceiptTable (\n" +
            "    id_receipt varchar(255) NOT NULL,\n" +
            "    id_product varchar(16),\n" +
            "    id_account varchar(16),\n" +
            "    receipt_type varchar(16),\n" +
            "    product_sum int,\n" +
            "    PRIMARY KEY (id_receipt),\n" +
            "    FOREIGN KEY (id_product) REFERENCES StockTable(id_product),\n" +
            "    FOREIGN KEY (id_account) REFERENCES AccountTable(id_account)\n" +
            ")";


    private static final String TABLE_NAME = "Contact";
    private static final String COL1 = "Name";
    private static final String COL2 = "Number";

    public Database(@Nullable Context context) {
        super(context, TABLE_NAME, null , 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_TABLE);
        db.execSQL(CREATE_TABLE2);
        db.execSQL(CREATE_TABLE3);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Contact");
        onCreate(db);
    }


    public void addData (String name, String number){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValue = new ContentValues();
        contentValue.put(COL1, name);
        contentValue.put(COL2, number);
        db.insert(TABLE_NAME, null, contentValue);


    }

    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(TABLE_NAME, new String[]{COL1, COL2}, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public void onReset(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS Contact");
        onCreate(db);
    }


}
