package com.example.donatesavelife.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.donatesavelife.DataModels.RequestDataModel;
import com.example.donatesavelife.R;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.widget.Toolbar.OnMenuItemClickListener;
import 	android.webkit.WebView;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.androidnetworking.common.Method;
import com.example.donatesavelife.Adapters.RequestAdapter;
import com.example.donatesavelife.DataModels.RequestDataModel;
import com.example.donatesavelife.R;
import com.example.donatesavelife.Utils.Endpoints;
import java.lang.reflect.Type;

import com.example.donatesavelife.Utils.VolleySingleton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import com.android.volley.Response.ErrorListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import android.webkit.WebView;
import android.webkit.WebViewClient;

//import javax.xml.transform.ErrorListener;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<RequestDataModel> requestDataModels;
    private RequestAdapter requestAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView make_request_button = findViewById(R.id.make_request_button);
        make_request_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, MakeRequestActivity.class));
            }
        });
        requestDataModels = new ArrayList<>();
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setOnMenuItemClickListener(new OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.search_button) {
                    //open search
                    startActivity(new Intent(MainActivity.this, SearchActivity.class));
                }
                return false;
            }
        });

        recyclerView = findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        requestAdapter = new RequestAdapter(requestDataModels, this);
        recyclerView.setAdapter(requestAdapter);
        populateHomePage();
        TextView pick_location = findViewById(R.id.pick_location);
        String location = PreferenceManager.getDefaultSharedPreferences(this)
                .getString("city", "no_city_found");
        if (!location.equals("no_city_found")) {
            pick_location.setText(location);
        }
    }


    private void populateHomePage() {


        /*


        RequestDataModel requestDataModel = new RequestDataModel();
        requestDataModel.setMessage("Hello World");
        requestDataModel.setNumber("8108323892");
        requestDataModel.setUrl("https://images.pexels.com/photos/736230/pexels-photo-736230.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500");
        requestDataModels.add(requestDataModel);
        requestDataModels.add(requestDataModel);
        requestDataModels.add(requestDataModel);
        requestDataModels.add(requestDataModel);
        requestDataModels.add(requestDataModel);
        requestDataModels.add(requestDataModel);

         */

        final String city = PreferenceManager.getDefaultSharedPreferences(getApplicationContext())
                .getString("city", "no_city");
        StringRequest stringRequest = new StringRequest(
                Method.POST, Endpoints.get_requests, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                Type type = new TypeToken<List<RequestDataModel>>() {}.getType();
                List<RequestDataModel> dataModels = gson.fromJson(response, type);
                requestDataModels.addAll(dataModels);
                requestAdapter.notifyDataSetChanged();
            }
        }, new ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Something went wrong:(", Toast.LENGTH_SHORT).show();
                Log.d("VOLLEY", Objects.requireNonNull(error.getMessage()));
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("city", city);
                return params;
            }
        };
        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }


    }


