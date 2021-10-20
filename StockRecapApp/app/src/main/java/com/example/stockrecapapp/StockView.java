package com.example.stockrecapapp;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.stockrecapapp.App.AppController;
import com.example.stockrecapapp.Util.Configuration;
import com.example.stockrecapapp.Util.Server;

public class StockView extends ListActivity {

    ProgressDialog pDialog;

    String scantype;
    int success;
    ConnectivityManager conMgr;

    private String urlread = Server.URL + "StockView.php";

    private static final String TAG = StockInPhp.class.getSimpleName();

    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";
    public static final String TAG_JSON_ARRAY="result";

    public final static String TAG_USERNAME = "username";
    public final static String TAG_PID = "Product Id";
    public static final String TAG_ = "scantype";

    String tag_json_obj = "json_obj_req";

    private TextView ProductIdView,ProductNameView,ProductQuantityText;

    private Button sendbutton;

    int productquantity;

    private ListView listView;

    private String JSON_STRING;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock_view);
        listView = (ListView) findViewById(R.id.listView);
        //listView.setOnItemClickListener(this);
        getJSON();
    }



    private void showEmployee(){
        JSONObject jsonObject = null;
        ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String, String>>();
        try {
            jsonObject = new JSONObject(JSON_STRING);
            JSONArray result = jsonObject.getJSONArray(TAG_JSON_ARRAY);

            for(int i = 0; i<result.length(); i++){
                JSONObject jo = result.getJSONObject(i);
                String nameproduct = jo.getString("nameproduct");
                String productquantity = jo.getString("productquantity");
                HashMap<String,String> employees = new HashMap<>();

                employees.put("nameproduct",nameproduct);
                employees.put("productquantity",productquantity);
                list.add(employees);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        ListAdapter adapter = new SimpleAdapter(
                StockView.this, list, R.layout.stock_list,
                new String[]{"nameproduct","productquantity"},
                new int[]{R.id.txtDescription, R.id.txtAmount});

        listView.setAdapter(adapter);
    }



    private void getJSON(){
        class GetJSON extends AsyncTask<Void,Void,String>{

            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(StockView.this,"Mengambil Data","Mohon Tunggu...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                JSON_STRING = s;
                showEmployee();
            }

            @Override
            protected String doInBackground(Void... params) {
                Configuration rh = new Configuration();
                String s = rh.sendGetRequest(urlread);
                return s;
            }
        }
        GetJSON gj = new GetJSON();
        gj.execute();
    }

}