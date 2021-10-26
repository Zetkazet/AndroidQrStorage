package com.example.stockrecapapp;

import static com.example.stockrecapapp.LoginPage.TAG_ID;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.stockrecapapp.App.AppController;
import com.example.stockrecapapp.Util.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class StockInPhp extends AppCompatActivity {

    ProgressDialog pDialog;

    String scantype;
    String id, username;
    int success;
    ConnectivityManager conMgr;

    private String urlin = Server.URL + "StockIn.php";
    private String urlout = Server.URL + "StockOut.php";

    private static final String TAG = StockInPhp.class.getSimpleName();

    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";

    public final static String TAG_USERNAME = "username";
    public final static String TAG_ID = "id";
    public static final String TAG_TYPE = "scantype";

    String tag_json_obj = "json_obj_req";

    SharedPreferences sharedpreferences;


    private TextView ProductIdView,ProductNameView,ProductQuantityText;

    private Button sendbutton;

    int productquantity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock_in_php);

        conMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        {
            if (conMgr.getActiveNetworkInfo() != null
                    && conMgr.getActiveNetworkInfo().isAvailable()
                    && conMgr.getActiveNetworkInfo().isConnected()) {
            } else {
                Toast.makeText(getApplicationContext(), "No Internet Connection",
                        Toast.LENGTH_LONG).show();
            }
        }

        ProductIdView = findViewById(R.id.pidtextview);
        ProductNameView = findViewById(R.id.pnmtextview);
        ProductQuantityText= findViewById(R.id.ProductQuantity);

        sendbutton = findViewById(R.id.sendbutton);

        scantype = getIntent().getStringExtra(TAG_TYPE);
        String productid = getIntent().getStringExtra("productid");
        String productname = getIntent().getStringExtra("productname");
        String accountid = getIntent().getStringExtra(TAG_ID);

        id = getIntent().getStringExtra(TAG_ID);
        username = getIntent().getStringExtra(TAG_USERNAME);

        ProductIdView.setText(productid);
        ProductNameView.setText(productname);




        sendbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                productquantity = Integer.parseInt(ProductQuantityText.getText().toString());

                if (productquantity>0 && scantype.equals("in")){
                    updatestockin(accountid, productid, productquantity);

                }

                else if(productquantity>0 && scantype.equals("out")){
                    updatestockout(accountid, productid, productquantity);
                }
            }
        });

    }

    private void updatestockin (final String username, final String productid, final int productquantity){
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);
        pDialog.setMessage("Sending ...");
        showDialog();

        StringRequest strReq = new StringRequest(Request.Method.POST, urlin, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.e(TAG, "Send Report:  " + response.toString());
                hideDialog();

                try {
                    JSONObject jObj = new JSONObject(response);
                    success = jObj.getInt(TAG_SUCCESS);

                    // Check for error node in json
                    if (success == 1) {


                        //JSONArray result = jsonObject.getJSONArray(TAG_JSON_ARRAY);

                        Log.e("Receipt Added!", jObj.toString());

                        Toast.makeText(getApplicationContext(), jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();



                        // Memanggil main activity
                        Intent intent = new Intent(StockInPhp.this, MainActivity.class);
                        intent.putExtra(TAG_ID, id);
                        intent.putExtra(TAG_USERNAME, username);
                        finish();
                        startActivity(intent);
                    } else {
                        Toast.makeText(getApplicationContext(),
                                jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();

                    }
                } catch (JSONException e) {
                    // JSON error
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Send Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();

                hideDialog();

            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<String, String>();
                params.put("username", username);
                params.put("productid", productid);
                params.put("productquantity", Integer.toString(productquantity));

                return params;
            }

        };

        AppController.getInstance().addToRequestQueue(strReq, tag_json_obj);


    }

    private void updatestockout (final String username, final String productid, final int productquantity){
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);
        pDialog.setMessage("Sending ...");
        showDialog();

        StringRequest strReq = new StringRequest(Request.Method.POST, urlout, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.e(TAG, "Send Report:  " + response.toString());
                hideDialog();

                try {
                    JSONObject jObj = new JSONObject(response);
                    success = jObj.getInt(TAG_SUCCESS);

                    // Check for error node in json
                    if (success == 1) {


                        //JSONArray result = jsonObject.getJSONArray(TAG_JSON_ARRAY);

                        Log.e("Receipt Added!", jObj.toString());

                        Toast.makeText(getApplicationContext(), jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();



                        // Memanggil main activity
                        Intent intent = new Intent(StockInPhp.this, MainActivity.class);
                        intent.putExtra(TAG_ID, id);
                        intent.putExtra(TAG_USERNAME, username);
                        finish();
                        startActivity(intent);
                    } else {
                        Toast.makeText(getApplicationContext(),
                                jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();

                    }
                } catch (JSONException e) {
                    // JSON error
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Send Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();

                hideDialog();

            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<String, String>();
                params.put("username", username);
                params.put("productid", productid);
                params.put("productquantity", Integer.toString(productquantity));


                return params;
            }

        };

        AppController.getInstance().addToRequestQueue(strReq, tag_json_obj);
    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }
}