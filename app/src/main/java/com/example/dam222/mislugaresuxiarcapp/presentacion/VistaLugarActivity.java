package com.example.dam222.mislugaresuxiarcapp.presentacion;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.dam222.mislugaresuxiarcapp.R;
import com.example.dam222.mislugaresuxiarcapp.casouso.CasoUsoLugares;
import com.example.dam222.mislugaresuxiarcapp.modelo.Lugar;

import java.text.DateFormat;
import java.util.Date;

public class VistaLugarActivity extends AppCompatActivity {

    /*COMPARTIR DATOS*/
    private CasoUsoLugares usoLugar;
    private int pos;
    private Lugar lugar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vista_lugar);
       //recuperar la posicion a visualizar
        Bundle extras = getIntent().getExtras();
        pos = extras.getInt("pos", 0);
        //Crear y acceder a la lista de lugares
        usoLugar = new CasoUsoLugares(this, ((Aplicacion) getApplication()).getLugares());

        //recuperar la posicion pos
        lugar=usoLugar.retornar(pos);//lugar sobre el que se esta trabajando
        actualizaVistas();
    }
    public void actualizaVistas() {
        ImageView imageView= (ImageView) findViewById(R.id.foto);
        //imageView esta inicializada a la foto de res
        TextView nombre = findViewById(R.id.nombre);
        nombre.setText(lugar.getNombre());
        ImageView logo_tipo = findViewById(R.id.logo_tipo);
        logo_tipo.setImageResource(lugar.getTipo().getRecurso());
        TextView tipo = findViewById(R.id.tipo);
        tipo.setText(lugar.getTipo().getTexto());
        TextView direccion = findViewById(R.id.direccion);
        direccion.setText(lugar.getDireccion());
        TextView telefono = findViewById(R.id.telefono);
        telefono.setText(Integer.toString(lugar.getTelefono()));
        TextView url = findViewById(R.id.url);
        url.setText(lugar.getUrl());
        TextView comentario = findViewById(R.id.comentario);
        comentario.setText(lugar.getComentario());
        TextView fecha = findViewById(R.id.fecha);
        fecha.setText(DateFormat.getDateInstance().format(
                new Date(lugar.getFecha())));
        TextView hora = findViewById(R.id.hora);
        hora.setText(DateFormat.getTimeInstance().format(
                new Date(lugar.getFecha())));
        RatingBar valoracion = findViewById(R.id.valoracion);
        valoracion.setRating(lugar.getValoracion());
        valoracion.setOnRatingBarChangeListener(
                new RatingBar.OnRatingBarChangeListener() {
                    @Override public void onRatingChanged(RatingBar ratingBar,
                                                          float valor, boolean fromUser) {
                        lugar.setValoracion(valor);
                    }
                });
        //imageView.setImageDrawable(lugar.getFoto());

        // ponerFoto(imageView, lugar.getFoto());
        //pongo la foto que realmente tiene el lugar
    }
    @Override public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_vista_lugar, menu);
        return true;
    }
    @Override public boolean onOptionsItemSelected(MenuItem item) {
        boolean opExito=true;
        switch (item.getItemId()) {
            case R.id.accion_compartir:
                compartirLugar();
                break;
            case R.id.accion_llegar:
                llegarLugar();
                break;
            case R.id.accion_editar:
                editarLugar(pos);
                //  usoLugar.editar(pos,RESULTADO_EDITAR);

                break;
            case R.id.accion_borrar:
                eliminarLugar(pos);

                break;
            default:
                opExito=super.onOptionsItemSelected(item);
        }
        return opExito;
    }
    public void compartirLugar(){};
    public void llegarLugar(){};
    public void editarLugar(int pos){};
    public void eliminarLugar(final int pos){
        new AlertDialog.Builder(this)
                .setTitle("Borrado de lugar")
                .setMessage("¿Estás seguro que quieres elminar este lugar?")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        usoLugar.borrar(pos);
                        finish();
                    }})
                .setNegativeButton("Cancelar", null)
                .show();


    }




}
