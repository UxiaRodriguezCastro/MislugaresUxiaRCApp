package com.example.dam222.mislugaresuxiarcapp.casouso;

import android.app.Activity;
import android.content.Intent;

import com.example.dam222.mislugaresuxiarcapp.modelo.LugaresVector;

/**
 * Created by DAM222 on 23/10/2019.
 */

/*************************************************
 * ACCIONES QUE TIENEN QUE VER CON LOS LUGARES
 * **********************************************/
public class CasoUsoLugares {
    private Activity actividad;
    private LugaresVector misLugares;
    public CasoUsoLugares(Activity actividad,LugaresVector misLugares) {

        this.actividad = actividad;
        this.misLugares = misLugares;
    }
    public void mostrar(int pos) {
       /* Intent i = new Intent(actividad, VistaLugarActivity.class);
        i.putExtra("pos", pos);
        actividad.startActivity(i);*/
    }
}