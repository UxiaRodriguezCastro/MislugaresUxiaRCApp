package com.example.dam222.mislugaresuxiarcapp.presentacion;

import android.app.ListActivity;
import android.os.Bundle;

import com.example.dam222.mislugaresuxiarcapp.R;
import com.example.dam222.mislugaresuxiarcapp.casouso.CasoUsoLugares;

/**
 * Created by DAM222 on 07/11/2019.
 */

public class TipoLugarActivity extends ListActivity {

    /*Estado*/

    public AdaptadorLugares adaptador;
    private CasoUsoLugares usoLugares;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_lugar);
        usoLugares=new CasoUsoLugares(this, ((Aplicacion) getApplication()).getLugares());
        adaptador= new AdaptadorLugares(this,usoLugares);
        setListAdapter(adaptador);
    }

}
