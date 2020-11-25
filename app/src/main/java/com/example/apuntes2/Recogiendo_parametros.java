package com.example.apuntes2;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class Recogiendo_parametros extends AppCompatActivity {
    private String nombre;
    private String sexo;
    private int edad;
    // para llamar por telefono versiones nuevas
    private final int PHONE_CALL_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recogiendo_parametros);
        TextView textView7 = findViewById(R.id.textView7);
        TextView textView8 = findViewById(R.id.textView8);
        TextView textView9 = findViewById(R.id.textView9);

//////// recoger datos que vienen en el bundle - intent ////////////////////////////////////////

        // Datos del bundle de activity
        Bundle bundle = this.getIntent().getExtras();

        if (bundle != null) {
            nombre = bundle.getString("Name");
            sexo = bundle.getString("Sexo");
            edad = bundle.getInt("Edad");
        }
/////////////////   hasta aqui recogida de datos que vienen en el bundle - intent /////////////////
        textView7.setText(nombre);
        textView8.setText(sexo);
        textView9.setText("" + edad);

/////////////////      /////// Llamar por telefono
        // Hay que Agregar permiso en el manifests

        ImageButton imgbtn_call = findViewById(R.id.imageButton_call);
    //// listener del boton imagen
        imgbtn_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNumber = "12345678"; // recoger de donde queramos
                if (phoneNumber != null) {
                    //si mi version >= 23
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        // llamada de permisos
                        requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, PHONE_CALL_CODE);
                    } else {
                        OlderVersions(phoneNumber);
                    }
                }
            }

            // Versiones viejas
            private void OlderVersions(String phoneNumber) {
                Intent intent_call = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phoneNumber));
                if (CheckPermission(Manifest.permission.CALL_PHONE)) {
                    startActivity(intent_call);
                } else {
                    Toast.makeText(Recogiendo_parametros.this, "Necesitas permiso", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean CheckPermission(String permission) {
        int result = this.checkCallingOrSelfPermission(permission);
        return result == PackageManager.PERMISSION_GRANTED;

    }
    /// recogida de permisos
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        //requestCode es el codigo que le hemos pasado, al ser un metodo asincrono debemos saber quien lo llamo
        switch (requestCode) {
            case PHONE_CALL_CODE:
                String permission = permissions[0];
                int result = grantResults[0];
                // comprobamos que el permiso sea el del telefono
                if (permission.equals((Manifest.permission.CALL_PHONE))) {
                    //comprobar si ha sido aceptado o denegado el permiso
                    if (result == PackageManager.PERMISSION_GRANTED) {
                        // concedio permiso
                        // volver a recoger el telefono xq estamos en otro metodo
                        String phoneNumber = "de un edittext o de donde venga";
                        Intent intent_call = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phoneNumber));
                        startActivity(intent_call);
                    } else {
                        // no concedio permiso
                        Toast.makeText(Recogiendo_parametros.this, "has denegado el permiso", Toast.LENGTH_SHORT).show();
                    }
                }

                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
                break;
        }

    }

}
