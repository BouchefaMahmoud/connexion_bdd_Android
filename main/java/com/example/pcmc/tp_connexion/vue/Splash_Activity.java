package com.example.pcmc.tp_connexion.vue;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.pcmc.tp_connexion.R;

/**
 * Created by PC MC on 22/04/2018.
 */
public class Splash_Activity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    Intent intent = new Intent("android.intent.action.PROF_ACTIVITY");
                    startActivity(intent);
                }
            }
        });
        t.start();

    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
