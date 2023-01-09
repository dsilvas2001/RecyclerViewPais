package com.demh.recyclerviewpais;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.demh.recyclerviewpais.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

import Adapter.PaisAdaptador;
import Models.Pais;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerCountry;
    RequestQueue queue;
    StringRequest stringRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerCountry = (RecyclerView) findViewById(R.id.recyclerPaisView);
        recyclerCountry.setHasFixedSize(true);
        recyclerCountry.setLayoutManager(new LinearLayoutManager(this));
        recyclerCountry.setItemAnimator(new DefaultItemAnimator());

        Bundle bundle = this.getIntent().getExtras();

        // Instantiate the RequestQueue.
         queue = Volley.newRequestQueue(this);
        String url ="http://www.geognos.com/api/en/countries/info/all.json";

        // Request a string response from the provided URL.
         stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        ArrayList<Pais> paises = new ArrayList<Pais> ();

                        try {
                            JSONObject JSONOBJECTpais = new JSONObject(response);
                            JSONObject JSONOBJECTpais2 = JSONOBJECTpais.getJSONObject("Results");
                            Iterator < String > codigosPaises = JSONOBJECTpais2.keys();
                            while (codigosPaises.hasNext())
                                paises.add(new Pais(JSONOBJECTpais2.getJSONObject(codigosPaises.next())));

                            int resId = R.anim.layout_animation_down_to_up;
                            LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(getApplicationContext(),
                                    resId);
                            recyclerCountry.setLayoutAnimation(animation);
                            PaisAdaptador adapatorPais = new PaisAdaptador(getApplicationContext(), paises);
                            recyclerCountry.setAdapter(adapatorPais);

                        }
                        catch (JSONException e)
                        {
                            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getApplicationContext(),  error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        // Add the request to the RequestQueue.
        queue.add(stringRequest);

    }
}