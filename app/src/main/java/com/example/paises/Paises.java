package com.example.paises;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

public class Paises {
    String Name;
    String iso2;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getIso2() {
        return iso2;
    }

    public void setIso2(String iso2) {
        this.iso2 = iso2;
    }



    public Paises(String countryCode, JSONObject countryData) throws JSONException {
        Name =  countryData.getString("Name");
        iso2 = "http://www.geognos.com/api/en/countries/flag/"+countryCode+".png";
    }

    public static ArrayList<Paises> JsonObjectsBuild(JSONObject datos) throws JSONException {
        ArrayList<Paises> lstPaises = new ArrayList<>();

        Iterator<String> keys = datos.keys();
        while (keys.hasNext()) {
            String countryCode = keys.next();
            JSONObject countryData = datos.getJSONObject(countryCode);
            lstPaises.add(new Paises(countryCode, countryData));
        }
        return lstPaises;
    }

}
