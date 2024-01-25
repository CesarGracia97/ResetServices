/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.actions;

/**
 *
 * @author jyacelga
 */
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.json.JSONObject;

public class winServices {

    private static final Logger Applicationstasks = Logger.getLogger("WStasks");
    private String resulComad;

    static {
        PropertyConfigurator.configure("/opt/log4j.properties");
    }

    public String winServices(String server, String action, String servicename, String user) throws InterruptedException, IOException, Exception {
        Applicationstasks.info("datos de llegada server: " + server + " action: " + action + " servicename: " + servicename + " user: " + user);
        JSONObject data = new JSONObject();
        data.put("serviceName", servicename);
        data.put("action", action);
        data.put("user", user);
        return getRestultParameters(jsonClient(data, server));
    }

    public String jsonClient(JSONObject data, String server) {
        String respuesta = "";

        try {
            URL url = new URL("http://" + server + "/winServicesAPI/webresources/Service/guardarResp");
            HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();
            httpConnection.setDoOutput(true);
            httpConnection.setRequestMethod("POST");
            httpConnection.setRequestProperty("Content-Type", "application/json");
            httpConnection.setRequestProperty("Accept", "application/json");

            Applicationstasks.info(winServices.class.getName() + " - REQUEST: " + data.toString());
            DataOutputStream wr = new DataOutputStream(httpConnection.getOutputStream());
            wr.write(data.toString().getBytes());
            Integer responseCode = httpConnection.getResponseCode();

            BufferedReader bufferedReader;
            if (responseCode > 199 && responseCode < 300) {
                bufferedReader = new BufferedReader(new InputStreamReader(httpConnection.getInputStream()));
            } else {
                bufferedReader = new BufferedReader(new InputStreamReader(httpConnection.getErrorStream()));
            }
            StringBuilder content = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                content.append(line).append("\n");
            }
            bufferedReader.close();
            respuesta = content.toString();
            Applicationstasks.info(winServices.class.getName() + " - RESPONSE: " + respuesta);
        } catch (Exception e) {
            Applicationstasks.info(winServices.class.getName() + " - Error: " + data.toString());
        }
        return respuesta;
    }

    public String getRestultParameters(String res) {
        String resutado = "";
        String[] arreglo = res.split(",");
        for (int i = 0; i < arreglo.length; i++) {
            Applicationstasks.info(winServices.class.getName() + " - RESPONSE: "+arreglo[i]);
            if (arreglo[i].contains("cod_respuesta")) {
                resutado = arreglo[i].substring(arreglo[i].length() - 1, arreglo[i].length());
            }
        }
        return resutado;
    }

}
