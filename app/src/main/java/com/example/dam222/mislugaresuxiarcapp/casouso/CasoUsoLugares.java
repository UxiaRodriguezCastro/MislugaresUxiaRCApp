package com.example.dam222.mislugaresuxiarcapp.casouso;

import android.app.Activity;
import android.content.Intent;

import com.example.dam222.mislugaresuxiarcapp.modelo.Lugar;
import com.example.dam222.mislugaresuxiarcapp.modelo.LugaresVector;
import com.example.dam222.mislugaresuxiarcapp.presentacion.EdicionlugarActivity;
import com.example.dam222.mislugaresuxiarcapp.presentacion.VistaLugarActivity;

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
        Intent i = new Intent(actividad, VistaLugarActivity.class);
        i.putExtra("pos", pos);
        actividad.startActivity(i);
    }

    public Lugar retornar (int pos){

        return misLugares.elemento(pos);
    }

    public int numeroLugares(){
        /*ESTA ES UNA CLASE INTERNA
        * no debe comunicarse con el usuario*/
        return misLugares.tamanyo();
    }

    public void borrar(int pos) {
        misLugares.borrar(pos);
        actividad.finish();
    }
    public void modificar(int pos, int codigoSolicitud) {
        Intent i = new Intent(actividad, EdicionlugarActivity.class);
        i.putExtra("pos", pos);
        actividad.startActivityForResult(i, codigoSolicitud);
    }

    public void guardar(int pos,Lugar lugar){

        misLugares.actualiza(pos,lugar);

    }
}