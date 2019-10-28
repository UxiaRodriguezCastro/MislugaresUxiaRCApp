package com.example.dam222.mislugaresuxiarcapp.presentacion;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dam222.mislugaresuxiarcapp.R;
import com.example.dam222.mislugaresuxiarcapp.casouso.CasoUsoActividades;
import com.example.dam222.mislugaresuxiarcapp.casouso.CasoUsoLugares;
import com.example.dam222.mislugaresuxiarcapp.presentacion.AcercadeApp;

public class MainActivity extends AppCompatActivity {

    private CasoUsoActividades usoAplicacion;
    private CasoUsoLugares usoLugares;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    /*1.- CREO LA ACTIVIDAD MAIN*/
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //2.- Creamos el caso de uso Aplicacion luego paso lugares a la aplicacion
        usoAplicacion = new CasoUsoActividades(this);
        //3.- estructura de acces lugares
        usoLugares = new CasoUsoLugares(this, ((Aplicacion) getApplication()).getLugares());
    /*toolbar: recordar que es un srol activity*/
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        boolean correcto=true;
        int id = item.getItemId();
        switch(id){
            case R.id.menu_acercade:
                ActividadAcercaDe(null);
                break;
            case R.id.menu_buscar:
                actividadVistaLugar(null );
                break;
            case R.id.menu_preferencias:
                actividadPreferencias(null );
                break;
            default:
                Toast mensa = Toast.makeText(this, "Uxia Rodriguez: Opción en construccion",
                        Toast.LENGTH_SHORT);
                mensa.show();
                correcto=super.onOptionsItemSelected(item);
                break;
        }
        return correcto;
    }
    public void ActividadAcercaDe(View view){
     usoAplicacion.lanzarAcercaDe(null);
    }
    public void pararAplicacion(View view){
        finish();
    }

    public void actividadPreferencias(View view){

        usoAplicacion.lanzarPreferencias(null);
    };
    public void actividadVistaLugar(View view){
        final EditText entrada = new EditText(this);
        //bloqueamos a numeros
        entrada.setInputType(InputType.TYPE_CLASS_NUMBER);
        entrada.setText("0");
        new AlertDialog.Builder(this)
                .setTitle("Selección de lugar")
                .setMessage("indica su id:")
                .setView(entrada)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        int id= Integer.parseInt (entrada.getText().toString());
                        if (id<usoLugares.numeroLugares())
                            usoLugares.mostrar(id);
                            //desde aquí no se puede enviar un toast
                        else{


                        } //smsErrores("Nombre Apellido Alumno: Posicion no existe");
                    }})
                .setNegativeButton("Cancelar", null)
                .show();
    }


}
