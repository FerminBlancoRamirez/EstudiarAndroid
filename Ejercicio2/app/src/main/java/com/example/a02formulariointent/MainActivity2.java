package com.example.a02formulariointent;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity2 extends AppCompatActivity {

    TextView nombreAMostrar;
    TextView correoAMostrar;
    TextView generoAMostrar;
    TextView nacionalidadAMostrar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent intent= getIntent();

        String nombre= intent.getExtras().getString("nombre");
        String correoElectronico=intent.getExtras().getString("email");
        String genero=intent.getExtras().getString("genero");
        String nacionalidad=intent.getExtras().getString("nacionalidad");

        nombreAMostrar=findViewById(R.id.NombreAMostrar);
        correoAMostrar=findViewById(R.id.CorreoAMostrar);
        generoAMostrar=findViewById(R.id.GeneroAMostrar);
        nacionalidadAMostrar=findViewById(R.id.NacionalidadAMostrar);

        nombreAMostrar.append(" "+nombre);
        correoAMostrar.append(" "+correoElectronico);
        generoAMostrar.append(" "+genero);
        nacionalidadAMostrar.append(" "+nacionalidad);

    }
}