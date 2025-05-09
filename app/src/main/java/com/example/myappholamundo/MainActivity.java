package com.example.myappholamundo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicReference;


public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        AtomicReference<String> fechaSeleccionada = new AtomicReference<>("");  // fecha seleccionada

        //declaracion
        Button btn1 = findViewById(R.id.mi_button);
        EditText editText = findViewById(R.id.editTextText);
        TextView textView = findViewById(R.id.textView);
        CheckBox checkBox = findViewById(R.id.checkBox);
        ImageView imageView = findViewById(R.id.imageView);
        CalendarView calendarView = findViewById(R.id.calendarView);

        // cuando el usuario cambia la fecha en el calendario
        calendarView.setOnDateChangeListener((view, year, month, dayOfMonth) -> {
            // omienza en 0 (enero es 0, febrero es 1, etc.), por eso sumamos +1
            fechaSeleccionada.set(String.format(Locale.getDefault(), "%02d/%02d/%04d", dayOfMonth, month + 1, year));
        });

        //button
        btn1.setOnClickListener((v) -> {
            //text
            String textMio = editText.getText().toString();
            textView.setText(textMio); // Mostrar en el TextView

            // Primer Toast: el texto
            Toast.makeText(MainActivity.this, "Texto: " + textMio, Toast.LENGTH_LONG).show();

            // Crear un delay de 2 segundos
            new android.os.Handler().postDelayed(() -> {
                //checkbox
                Toast.makeText(MainActivity.this, "El checkbox está activado", Toast.LENGTH_SHORT).show();

                // Obtener la hora actual
                SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
                String horaActual = formatoHora.format(new Date()); //obtener horaactual

                // Si el user ha seleccionado una fecha
                String mensajeFechaHora;
                if (!fechaSeleccionada.get().isEmpty()) {
                    mensajeFechaHora = "Fecha seleccionada: " + fechaSeleccionada + " Hora actual: " + horaActual;
                } else {
                    // Si no ha seleccionado fecha, ponemos la de hoy
                    SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                    String fechaHoy = formatoFecha.format(new Date());
                    mensajeFechaHora = "Fecha de hoy: " + fechaHoy + " Hora actual: " + horaActual;
                }

                // Mostrar el Toast con la fecha seleccionada y la hora actual
                Toast.makeText(MainActivity.this, mensajeFechaHora, Toast.LENGTH_LONG).show();
            }, 2000); // tiempo en milisegundos
        });

        //informacion del checkbox
        checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                imageView.setVisibility(View.VISIBLE);
            } else {
                imageView.setVisibility(View.GONE);
            }
        });

        //button para el secondActivity
        ImageButton btnIrSegundo = findViewById(R.id.btn_ir_segundoAct);

        btnIrSegundo.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, MainActivity2.class);
            startActivity(intent);
        });

    }
}
