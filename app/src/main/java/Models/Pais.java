package Models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Pais {

    private String urlBandera;
    private String titulopais;
    private String capital;
    private String isopais;
    private String prefijo;

    public String getUrlBandera() {
        return urlBandera;
    }

    public void setUrlBandera(String urlBandera) {
        this.urlBandera = urlBandera;
    }

    public String getTitulopais() {
        return titulopais;
    }

    public void setTitulopais(String titulopais) {
        this.titulopais = titulopais;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String getIsopais() {
        return isopais;
    }

    public void setIsopais(String isopais) {
        this.isopais = isopais;
    }

    public String getPrefijo() {
        return prefijo;
    }

    public void setPrefijo(String prefijo) {
        this.prefijo = prefijo;
    }

    public Pais(JSONObject a) throws JSONException {
        titulopais = "Nombre del Pais: " + a.getString("title").toString();
        JSONObject countrycode =  a.getJSONObject("CountryCodes");
        isopais = countrycode.getString("iso2");
        urlBandera =  "http://www.geognos.com/api/en/countries/flag/"+isopais +".png";
        JSONObject countrycapital =  a.getJSONObject("Capital");
        capital = countrycapital.getString("name").toString();
        prefijo = a.getString("TelPref").toString() ;

    }

    public static ArrayList<Pais> JsonObjectsBuild(JSONArray datos) throws JSONException {
        ArrayList<Pais> paislist = new ArrayList<>();

        for (int i = 0; i < datos.length(); i++) {
            paislist.add(new Pais(datos.getJSONObject(i)));
        }
        return paislist;
    }
}
