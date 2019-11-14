package com.example.dam222.mislugaresuxiarcapp.presentacion;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dam222.mislugaresuxiarcapp.R;
import com.example.dam222.mislugaresuxiarcapp.casouso.CasoUsoLugares;
import com.example.dam222.mislugaresuxiarcapp.modelo.Lugar;

import java.text.DateFormat;
import java.util.Date;

public class VistaLugarActivity extends AppCompatActivity {

    /*CONSTANTES*/
    final static int RESULTADO_EDITAR=1;
    final static int RESULTADO_GALERIA=2;
    final static int RESULTADO_FOTO=3;
    /*COMPARTIR DATOS*/
    private CasoUsoLugares usoLugar;
    private int pos;
    private Lugar lugar;

    /*Elementos de la interfaz*/

    ImageView imageView;

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
    }//fin oncreate
    public void actualizaVistas() {
         imageView= (ImageView) findViewById(R.id.foto);
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
    }//fin actualizar vistas
    @Override public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_vista_lugar, menu);
        return true;
    }//fin oncreate
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
                editarLugar(pos,RESULTADO_EDITAR);
                //  usoLugar.editar(pos,RESULTADO_EDITAR);

                break;
            case R.id.accion_borrar:
                eliminarLugar(pos);

                break;
            default:
                opExito=super.onOptionsItemSelected(item);
        }
        return opExito;
    }//fin onoptions
    public void compartirLugar(){};
    public void llegarLugar(){};

    public void editarLugar(int pos, int codigoSolicitud){

        usoLugar.modificar( pos, codigoSolicitud);

    };//fin editarlugar

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


    }//fin eliminarlugar

    @Override protected void onActivityResult(int requestCode, int resultCode,
                                              Intent data) {
        /***************
         * Una vez regresamos de la actividad EdicionLugarActivity
         * lo que hacemos es actualizar los valores de las vistas y
         * forzar al sistema a que repinte la vista con id scrollView1.
         * Esta vista corresponde al ScrollView que contiene todo el LAYOUT
         * */

        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode){

            case RESULTADO_EDITAR:
                actualizaVistas();
                findViewById(R.id.scrollView1).invalidate();
                break;
            case RESULTADO_GALERIA:

                    if (resultCode == Activity.RESULT_OK) {
                        lugar.setFoto(data.getDataString());
                        ponerFoto( lugar.getFoto());
                    } else {
                        Toast.makeText(this, "Foto no cargada", Toast.LENGTH_LONG).show();
                    }

                break;


            case RESULTADO_FOTO:
                break;
            default:  Toast.makeText(this, "Error fatal del menu", Toast.LENGTH_LONG).show();



        }
    }// fin de on activity

    	public void galeria(View view) {

        //Intent intent = new Intent(Intent.ACTION_PICK, /*Acceso denegado*/  //funciona siempre que no se pase de una actividad a otra la imagen, tiempo de accceso muy corto/limitado
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT,//tratandolo como un documento nos permite tratarlo perfectamente sin errores por un tiempo de acceso limitado
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, RESULTADO_GALERIA);

    }// fin de galeria

    protected void ponerFoto(String uriFoto) {
        imageView= (ImageView) findViewById(R.id.foto);
        if (uriFoto != null && !uriFoto.isEmpty() && !uriFoto.equals("null")) {
            imageView.setImageURI(Uri.parse(uriFoto));
        } else{
            imageView.setImageBitmap(null);
        }
    }//fin poner foto


}
