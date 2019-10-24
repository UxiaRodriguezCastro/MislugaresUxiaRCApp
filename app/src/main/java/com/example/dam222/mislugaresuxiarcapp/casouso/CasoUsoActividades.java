package com.example.dam222.mislugaresuxiarcapp.casouso;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import com.example.dam222.mislugaresuxiarcapp.presentacion.AcercadeApp;

/**
 * Created by DAM222 on 23/10/2019.
 */

public class CasoUsoActividades {

    private Activity actividad ;
    public CasoUsoActividades(Activity Actividad) {

            this.actividad = Actividad;

        }
        public void lanzarAcercaDe(View view){

            Intent i = new Intent(actividad, AcercadeApp.class);
            actividad.startActivity(i);
        }
        public void lanzarPreferencias(View view){
            Toast mensa = Toast.makeText(actividad,
                    "Uxia Rodriguez Castro: Opción en construccion", Toast.LENGTH_SHORT);
            mensa.show();

            //Intent i = new Intent(actividad, ACercaDeActivity.class);
            //actividad.startActivity(i);
        }
        public void lanzarMapas(View view){

            Toast mensa = Toast.makeText(actividad,
                    "Uxia Rodriguez Castro: Opción en construccion", Toast.LENGTH_SHORT);
            mensa.show();
            //Intent i = new Intent(actividad, ACercaDeActivity.class);
            //actividad.startActivity(i);
        }
    }



