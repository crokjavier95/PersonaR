package com.example.alejandro.personaregis;

import android.util.Log;


import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;

import org.apache.http.client.methods.HttpGet;

import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

import android.os.StrictMode;


import java.io.IOException;
import java.net.HttpURLConnection;


/**
 * Created by marcelosiedler on 09/03/15.
 */
public class AcessoRest {
    private int  TIMEOUT_MILLISEC = 3000;


    // private String[] params;
    //Metodo para enviar params
    public String envioGet(String url)
    {
        HttpClient httpclient = new DefaultHttpClient();

        HttpGet envioget = new HttpGet(url);
        String retorno = "";
        // Instanciar a GET HTTP metodo
        try {

            //Aqui o idea colocar a request sincronozar
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

            StrictMode.setThreadPolicy(policy);

            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            String responseBody = httpclient.execute(envioget,
                    responseHandler);

            retorno = responseBody;

        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Throwable t) {
            Log.i("erro", t.toString());
        }

        return retorno;

    }

}
