package com.example.dam222.mislugaresuxiarcapp.presentacion;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

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
    }//fin on create

    @Override protected void onListItemClick(ListView listView, View vista, int
            position, long id) {
        super.onListItemClick(listView, vista, position, id);
        Intent i = new Intent(this, VistaLugarActivity.class);
        i.putExtra("pos",(int) id);
        startActivity(i);
    }//fin onlisitemclick


}
