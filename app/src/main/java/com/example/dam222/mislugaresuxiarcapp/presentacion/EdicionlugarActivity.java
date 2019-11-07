package com.example.dam222.mislugaresuxiarcapp.presentacion;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.dam222.mislugaresuxiarcapp.R;
import com.example.dam222.mislugaresuxiarcapp.casouso.CasoUsoLugares;
import com.example.dam222.mislugaresuxiarcapp.modelo.Lugar;
import com.example.dam222.mislugaresuxiarcapp.modelo.TipoLugar;

public class EdicionlugarActivity extends AppCompatActivity {
    /***************************
     * interfaz grafica
     * **************************/
    private EditText nombre;
    private EditText direccion;
    private EditText telefono;
    private EditText url;
    private EditText comentario;
    //1.- declaro spinner
    private Spinner tipo;
    /***************************
     * datos
     * **************************/
    private CasoUsoLugares usoLugar;
    private int pos;
    private Lugar lugar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edicion_lugar);
        Bundle extras = getIntent().getExtras();
        pos = extras.getInt("pos", 0);
        usoLugar = new CasoUsoLugares(this, ((Aplicacion) getApplication()).getLugares());//-------------
    /*Recupero el lugar segun su posicion*/
        lugar = usoLugar.retornar(pos);
        actualizaVistas();

    }//fin onCreate

    public void actualizaVistas() {
        nombre = findViewById(R.id.nombre);
        nombre.setText(lugar.getNombre());
        direccion = findViewById(R.id.direccion);
        direccion.setText(lugar.getDireccion());
        telefono = findViewById(R.id.telefono);
        telefono.setText(Integer.toString(lugar.getTelefono()));
        url = findViewById(R.id.url);
        url.setText(lugar.getUrl());
        comentario = findViewById(R.id.comentario);
        comentario.setText(lugar.getComentario());
        //2. lo asocio a la vista
        tipo=findViewById(R.id.spTipo);
        //desplegables:
        /*
        * Para inicializar los valores que puede tomar un Spinner
        * necesitamos una clase especial conocida como Adapter
        * La clase ArrayAdapter<String> es un tipo de Adapter
        * que permite inicializar sus valores
        * a partir de un ARRAY de String*/

        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(
               this,/*contexto la actividad actual*/
            android.R.layout.simple_spinner_item,/*una vista para mostrar elemento */
              TipoLugar.getNombres()/*ARRAY con todos los valores
              que debemos programar en TipoLugar para
              mantener el aislamiento entre los códigos*/);
              adaptador.setDropDownViewResource(android.R.layout.
            simple_spinner_dropdown_item);
        /*permite indicar una vista alternativa que se usará cuando se despliegue el Spinner*/

       tipo.setAdapter(adaptador);/*vinculamos el adaptador al spinner*/
       tipo.setSelection(lugar.getTipo().ordinal());/*realizamos la selecion*/

    }//fin actualiza vista

    @Override public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_editar_lugar, menu);
        return true;
    }//finoncreate
    @Override public boolean onOptionsItemSelected(MenuItem item) {
        boolean opExito=true;
        switch (item.getItemId()) {
            case R.id.accion_guardar:
                modificarVista();
                usoLugar.guardar(pos,lugar);

                finish();
           break;
            case R.id.accion_cancelar:
                break;
            default:
                opExito=super.onOptionsItemSelected(item);
        }
        return opExito;
    }//fin onoptions

    public void modificarVista(){

        lugar.setNombre(nombre.getText().toString());
        lugar.setTipo(TipoLugar.values()[tipo.getSelectedItemPosition()]);
        lugar.setDireccion(direccion.getText().toString());
        //verifica que no este a 0 lo que significa que se desconoce
        if (telefono.getText().toString().isEmpty())lugar.setTelefono(0);
        else lugar.setTelefono(Integer.parseInt(telefono.getText().toString()));
        lugar.setUrl(url.getText().toString());
        lugar.setComentario(comentario.getText().toString());


    }//fin modificar vista




}
