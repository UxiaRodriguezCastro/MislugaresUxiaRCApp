package com.example.dam222.mislugaresuxiarcapp.presentacion;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
}
