package com.example.paises;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.example.paises.WebServices.Asynchtask;
import com.example.paises.WebServices.WebService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements Asynchtask {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Map<String, String> datos = new HashMap<String, String>();
        WebService ws = new WebService("http://www.geognos.com/api/en/countries/info/all.json",
                datos, MainActivity.this, MainActivity.this);
        ws.execute("GET");
    }

    @Override
    public void processFinish(String result) throws JSONException {

        ArrayList<Paises> lstPaises = new ArrayList<Paises>();

        JSONObject lista = new JSONObject(result);
        JSONObject JSONlista = lista.getJSONObject("Results");

        lstPaises = Paises.JsonObjectsBuild(JSONlista);

        AdaptadorPaises adaptadorPaises
                = new AdaptadorPaises(this, lstPaises);
        ListView lstOpciones = (ListView)findViewById(R.id.lstPaises);
        lstOpciones.setAdapter(adaptadorPaises);
    }
}