package com.example.apuntes2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;

public class Pasando_parametros extends AppCompatActivity {
    private TextView textView_edad;
    private String name;
    private String sexo;
    private int edad;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pasando_parametros);

        /////////// poner Flecha Atras (HomeUp) ///////////////////////
        // mostrar en la ActionBar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // Ir al manifests/AndroidManifest.xml y añadir el parent a las activitys
////////////Hasta aqui Poner Flecha Atras (HomeUp) /////////////////////////////////////////////////

//////////////////////////// Recoger parametros  ///////////////////////////////////////////////////
///////   EditText   ///////  nombre
        EditText editText_name = findViewById(R.id.editText_Name);
        name = editText_name.getText().toString();

///////   RadioButtons ///// Sexo

        sexo = "indistinto";
        RadioButton radioButton_hombre = findViewById(R.id.radioButton_hombre);
        RadioButton radioButton_mujer = findViewById(R.id.radioButton_mujer);

        if (radioButton_hombre.isChecked()) sexo = "hombre";
        if (radioButton_mujer.isChecked()) sexo = "mujer";

///////   SeekBar /////// Edad      ///////////////////////////////////////////////////////////////
        // (Hay que poner un listener para obtener el valor)
        textView_edad = findViewById(R.id.textView_edad);
        SeekBar seekBar_edad = findViewById(R.id.seekBar_edad);

        // Valor Inicial del seekBar
        seekBar_edad.setProgress(0);
        // Valo Final
        seekBar_edad.setMax(120);
       // seekBar_edad.setMin(0);
        // Listener del seebar (cuando cambia ChangeListener)
        seekBar_edad.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            //hace un llamado a la perilla cuando se arrastra
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // recoger la edad para pasar como int a la otra activity
                edad=progress;
                // Recoger el valor para mostrarlo (pasar el int a String)
                textView_edad.setText(String.valueOf(progress));
               //tambien vale
                // textView_edad.setText(""+progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

/////////// hasta aqui recogida de dato del seekbar ///////////////////////////////////////////////

////////////////// Pasar a otra Activity los datos recogidos en esta al pulsar siguiente //////////
        Button btn_next2=findViewById(R.id.button_siguiente2);

        btn_next2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Instanciamos en intent
                Intent intent =new Intent(Pasando_parametros.this,Recogiendo_parametros.class);
                // Creamos el bundle
                Bundle bundle= new Bundle();
                bundle.putString("Name",name);
                bundle.putString("Sexo", sexo);
                bundle.putInt("Edad",edad);

                // añadir bundle al intent
                intent.putExtras(bundle);
                // lanzamos la activity
                startActivity(intent);
            }
        });

    }
}