package com.example.dam222.mislugaresuxiarcapp.presentacion;

import android.app.Application;
import android.content.SharedPreferences;

import com.example.dam222.mislugaresuxiarcapp.modelo.LugaresVector;

/**
 * Created by DAM222 on 21/10/2019.
 */

public class Aplicacion extends Application {
    private int saldo;
    private LugaresVector misLugares;

    @Override public void onCreate() {
        super.onCreate();
        SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);
        saldo = pref.getInt("saldo_inicial", -1);
    }

    public int getSaldo(){
        return saldo;
    }

    public void setSaldo(int saldo){
        this.saldo=saldo;
    }
    /*codigo que maneja los datos*/
    public LugaresVector getLugares() {
        return misLugares;
    }

}