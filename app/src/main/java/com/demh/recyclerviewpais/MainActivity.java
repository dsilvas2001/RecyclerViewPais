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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

import Adapter.PaisAdaptador;
import Models.Pais;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerPais;
    RequestQueue queue;
    StringRequest stringRequest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerPais = (RecyclerView) findViewById(R.id.recyclerPaisView);
        recyclerPais.setHasFixedSize(true);
        recyclerPais.setLayoutManager(new LinearLayoutManager(this));
        recyclerPais.setItemAnimator(new DefaultItemAnimator());

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
                            JSONObject JSONlistaPais=  JSONOBJECTpais.getJSONObject("Results");
                            Iterator<String> Isopais =  JSONlistaPais.keys();

                            while (Isopais.hasNext()) {
                                String aux;
                                aux=Isopais.next();
                                JSONObject paiscontador = JSONlistaPais.getJSONObject(aux);
                                // System.out.println(paiscontador);
                                Pais ps= new Pais(paiscontador);
                                paises.add(ps);
                                // System.out.println(ps);
                                Log.d("etiqueta",paiscontador.toString());
                                int resId = R.anim.layout_animation_down_to_up;
                                LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(getApplicationContext(),
                                        resId);
                                recyclerPais.setLayoutAnimation(animation);
                                PaisAdaptador adapatorpais = new PaisAdaptador(getApplicationContext(), paises);
                                recyclerPais.setAdapter(adapatorpais);
                            }
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