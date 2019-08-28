package com.example.pcmc.tp_connexion.outils;

import android.os.AsyncTask;

import com.sun.deploy.net.HttpResponse;


import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class AccesHTTP extends AsyncTask<String, Integer, Long> {




    private ArrayList<NameValuePair> parametres;
    private static final String URL="http://192.168.8.100/essai1/server.php";
    private String retour=null ;
    public static InterAccesDistant delegate=null ;
    public AccesHTTP(){
        super();
        parametres = new ArrayList<NameValuePair>();
    }


    public void AjoutPar(String operation, String valeur){
        parametres.add(new BasicNameValuePair(operation, valeur));
    }



    @Override
    protected Long doInBackground(String... arg0) {

        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(URL);


        try {
            httpPost.setEntity(new UrlEncodedFormEntity(parametres));
            System.out.println("Avant");
            HttpResponse reponse = httpclient.execute(httpPost);
            System.out.println("Apres");
            retour = EntityUtils.toString(reponse.getEntity());

        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            System.out.println("Encodage : "+e.toString());
        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
            System.out.println("Protocol : "+e.toString());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            System.out.println("I/O : "+e.toString());
        }


        return null;
    }



    @Override
    protected void onPostExecute(Long result){
        delegate.processfinish(retour.toString());
    }

}
