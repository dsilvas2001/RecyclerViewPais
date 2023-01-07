package Models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Pais {
    String imgUrlBandera;
    String txtTituloPais, prefijo, capital;
    String txtCapital;
    String txtPrefijo;
    String textiso;

    public String getPrefijo() {
        return prefijo;
    }

    public void setPrefijo(String prefijo) {
        this.prefijo = prefijo;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String getTextiso() {
        return textiso;
    }

    public void setTextiso(String textiso) {
        this.textiso = textiso;
    }

    public String getImgUrlBandera() {
        return imgUrlBandera;
    }

    public void setImgUrlBandera(String imgUrlBandera) {
        this.imgUrlBandera = imgUrlBandera;
    }

    public String getTxtTituloPais() {
        return txtTituloPais;
    }

    public void setTxtTituloPais(String txtTituloPais) {
        this.txtTituloPais = txtTituloPais;
    }

    public String getTxtCapital() {
        return txtCapital;
    }

    public void setTxtCapital(String txtCapital) {
        this.txtCapital = txtCapital;
    }

    public String getTxtPrefijo() {
        return txtPrefijo;
    }

    public void setTxtPrefijo(String txtPrefijo) {
        this.txtPrefijo = txtPrefijo;
    }

    public Pais(JSONObject a) throws JSONException {
        JSONObject countryCapital = a.getJSONObject("Capital");
        txtTituloPais = "Pais:\t" + a.getString("Name").toString() + "\n\n" +"TelPref:\t"  + a.getString("TelPref").toString() +"\n\n" + "Capital:\t"+ countryCapital.getString("Name").toString()
                + "\n\n" + "Más Información:\t" + a.getString("CountryInfo").toString();
        JSONObject countryCode = a.getJSONObject("CountryCodes");
        textiso = countryCode.getString("iso2");
        imgUrlBandera =  "http://www.geognos.com/api/en/countries/flag/"+textiso+".png";

    }

    public static ArrayList<Pais> JsonObjectsBuild(JSONArray datos) throws JSONException {
        ArrayList<Pais> listpaises = new ArrayList<>();

        for (int i = 0; i < datos.length(); i++) {
            listpaises.add(new Pais(datos.getJSONObject(i)));
        }
        return listpaises;
    }
}
