package com.example.myappholamundo;

import android.content.ActivityNotFoundException;
import android.net.Uri;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.text.InputFilter;
import android.text.InputType;
import android.view.View;
import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity2 extends AppCompatActivity {

    TextView textoBienvenida;
    Button botonSalir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        textoBienvenida = findViewById(R.id.texto_bienvenida);
        botonSalir = findViewById(R.id.boton_salir);

        textoBienvenida.setText("Bienvenido al Segundo Activity");

        botonSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Regresar al primer Activity
                Intent intent = new Intent(MainActivity2.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);//ejecuta
                finish(); // Cerramos el Segundo Activity
            }
        });

        //numero telefonico
        EditText campoTelefono = new EditText(this);
        campoTelefono.setHint("Ingrese su número (solo dígitos)");
        campoTelefono.setInputType(InputType.TYPE_CLASS_NUMBER);
        campoTelefono.setFilters(new InputFilter[]{ new InputFilter.LengthFilter(8) });

        // Agregar el campo al layout
        LinearLayout layout = findViewById(R.id.layout_segundo);
        layout.addView(campoTelefono);

        // Botón para WhatsApp
        Button botonWhatsapp = new Button(this);
        botonWhatsapp.setText("Enviar a WhatsApp");
        layout.addView(botonWhatsapp);

        botonWhatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String numero = campoTelefono.getText().toString().trim();

                if (numero.matches("\\d{8}")) {
                    String numeroCompleto = "+507" + numero;
                    String mensaje = "Hola, estoy probando la integración de WhatsApp";
                    String url = "https://wa.me/" + numeroCompleto.replace("+", "") + "?text=" + Uri.encode(mensaje);

                } else {
                    Toast.makeText(MainActivity2.this, "Ingrese un número válido de 8 dígitos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
