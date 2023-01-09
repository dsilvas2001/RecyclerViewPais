package Models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Pais {

    private String txtTituloPais;
    private String txtCapital;
    private String txtPrefijo;
    private String iso2;
    private String imgUrlBandera;
    private String Infocountry;

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

    public String getIso2() {
        return iso2;
    }

    public void setIso2(String iso2) {
        this.iso2 = iso2;
    }

    public String getImgUrlBandera() {
        return imgUrlBandera;
    }

    public void setImgUrlBandera(String imgUrlBandera) {
        this.imgUrlBandera = imgUrlBandera;
    }

    public String getInfocountry() {
        return Infocountry;
    }

    public void setInfocountry(String infocountry) {
        Infocountry = infocountry;
    }

    public Pais(JSONObject a) throws JSONException {
        txtTituloPais = "Pais:\t" +  a.getString("Name").toString();
        JSONObject countryCode = a.getJSONObject("CountryCodes");
        iso2 = countryCode.getString("iso2");
        imgUrlBandera =  "http://www.geognos.com/api/en/countries/flag/"+iso2+".png";
        if (!a.isNull("Capital")) {
            JSONObject countryCapital = a.getJSONObject("Capital");
            txtCapital =   "Capital:\t"+ countryCapital.getString("Name").toString() ;
        }else txtCapital="La capital no se encuentra en el Webservice";
        txtPrefijo =  "TelPref:\t"+ a.getString("TelPref").toString() ;
        Infocountry =  "Más Información:\t"+ a.getString("CountryInfo").toString() ;
    }

    public static ArrayList<Pais> JsonObjectsBuild(JSONArray datos) throws JSONException {
        ArrayList<Pais> paises = new ArrayList<>();

        for (int i = 0; i < datos.length(); i++) {
            paises.add(new Pais(datos.getJSONObject(i)));
        }
        return paises;
    }
}
