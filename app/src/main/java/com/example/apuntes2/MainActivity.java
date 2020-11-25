package com.example.apuntes2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btn_next;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

/////////// Poner icono en Action Bar y App///////////////////////

            //  Primero guardar la imagen en New->Imagen Asset (Cambiar nombre ic_miicono; NO PONER MAYUSCULAS EN ARCHIVOS)
            //  El icono aparecerá en app/res/drawable/mipmap

            // para App ir al manifests/AndroidManifest.xml

        // Mostrar ActionBar
       getSupportActionBar().setDisplayShowHomeEnabled(true);
        // Añadir icono a ActionBar
       getSupportActionBar().setIcon(R.mipmap.ic_myicon);
////////////Hasta aqui Poner icono en Action Bar y App//////////////////////////////////////////////

/////////// poner Flecha Atras (HomeUp) ///////////////////////
            // mostrar en la ActionBar  // en el mainactivity no debería ir
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            // Ir al manifests/AndroidManifest.xml y añadir el parent a las activitys
////////////Hasta aqui Poner Flecha Atras (HomeUp) /////////////////////////////////////////////////

//////////// Poder Presionar el boton //////////////////////////////////////////////////////////////
            // Instanciar el button (java -> xml)
        btn_next= findViewById(R.id.button_siguiente);
            // Añadir listener y sobreescribir on click
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Cuando presionemos salta a otra activity
                    // Crear un intent
                Intent  intent =new Intent(MainActivity.this,Pasando_parametros.class);
                    // lanzar activity
                startActivity(intent);
            }
        });
    }
}