package com.example.pcmc.tp_connexion.model;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import android.support.v4.app.ActionBarDrawerToggle.Delegate;

import android.util.Log;

import com.example.pcmc.tp_connexion.outils.AccesHTTP;
import com.example.pcmc.tp_connexion.outils.InterAccesDistant;

public class AccesDistant implements InterAccesDistant {

    private  ArrayList<Etudiant> personnes ;



    public AccesDistant(){
        super();
        personnes  = new ArrayList<Etudiant>();
    }


    public ArrayList<Etudiant> getPersonnes() {
        return personnes;
    }


    public void setPersonnes(ArrayList<Etudiant> personnes) {
        this.personnes = personnes;
    }




    @Override
    public void processfinish(String output){

        Log.d("serveur","**********"+output);
        // découpage du message recu
        String[] message = output.split("%");
        //dans message[0] j'aurais soit "erreur","dernier","enreg"
        //dans message[1] j'aurais le reste du message

        if(message.length==2 ){


            if(message[0].equals("update")){
                Log.d("update","**********"+message[1]);

            }else {
                if(message[0].equals("select")){



                    try {
                        JSONArray array = new JSONArray(message[1]);
                        for(int i=0 ; i != array.length(); i++ ){
                            JSONObject jsonobject = new JSONObject(array.getJSONObject(i).toString());
                            String nom = jsonobject.getString("nom");
                            String prenom = jsonobject.getString("prenom");
                            String presence = jsonobject.getString("presence");
                            Integer cb = jsonobject.getInt("cb");
                            personnes.add(new Etudiant(nom,prenom,presence,cb));

                        }



                    } catch (JSONException e) {

                        System.out.println("************ Erreur encodage ************** : "+e.toString());
                    }







                }else{
                    if(message[0].equals("erreur")) {
                        Log.d("erreur","**********"+message[0]);


                    }

                }
            }
        }



    }


    public void Envoie(String operarion , JSONArray donnees ) {
        AccesHTTP acceshttp = new AccesHTTP();
        acceshttp.AjoutPar("operation", operarion);
        acceshttp.AjoutPar("presence", donnees.toString());
        acceshttp.delegate=this;
        acceshttp.execute(null);


    }






}
