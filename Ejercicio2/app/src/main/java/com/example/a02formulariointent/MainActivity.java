package com.example.a02formulariointent;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText nombreCompleto;
    EditText correoElectronico;
    EditText contraseña;
    RadioGroup generos;
    Spinner nacionalidad;
    Button boton;


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

        nombreCompleto = findViewById(R.id.IntroducirNombre);
        correoElectronico = findViewById(R.id.IntroducirCorreoElectronico);
        contraseña = findViewById(R.id.IntroducirContraseña);
        generos = findViewById(R.id.generos);
        nacionalidad = findViewById(R.id.spinner);
        boton=findViewById(R.id.Boton);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.paises,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        nacionalidad.setAdapter(adapter);

        nacionalidad.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Ignoramos la acción si el usuario tiene seleccionado el primer elemento (índice 0)
                if (position > 0) {
                    String paisSeleccionado = parent.getItemAtPosition(position).toString();
                    Toast.makeText(MainActivity.this, "Seleccionaste: " + paisSeleccionado, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Sin selección
            }
        });

        Intent intent=new Intent(MainActivity.this, MainActivity2.class);
        boton.setOnClickListener(e->{
            if (validarFOrmulario()){
                intent.putExtra("nombre", nombreCompleto.getText().toString());
                intent.putExtra("email", correoElectronico.getText().toString());
                intent.putExtra("password", contraseña.getText().toString());
                intent.putExtra("genero", generos.getCheckedRadioButtonId()==R.id.Masculino?"Masculino":"Femenino");
                intent.putExtra("nacionalidad", nacionalidad.getSelectedItem().toString());
                startActivity(intent);
            }
        });

    }

    public boolean validarFOrmulario(){
        boolean esValido=true;

        String nombre=nombreCompleto.getText().toString().trim();
        String email=correoElectronico.getText().toString().trim();
        String password=contraseña.getText().toString().trim();

        if(TextUtils.isEmpty(nombre)){
            nombreCompleto.setError("Por favor ingresa tu nombre completo");
            esValido=false;
        }

        if (TextUtils.isEmpty(email)){
            correoElectronico.setError("Por favor ingresa tu correo electronico");
            esValido=false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            correoElectronico.setError("Por favor introduce un correo electronico valido");
            esValido=false;
        }

        if (TextUtils.isEmpty(password)){
            contraseña.setError("Por favor ingresa tu contraseña");
            esValido=false;
        } else if (password.length()<6) {
            contraseña.setError("Tu contraseña debe tener mas de 6 caracteres");
            esValido=false;
        }

        if (generos.getCheckedRadioButtonId()==-1){
            Toast.makeText(this, "Por favor selecciona tu genero", Toast.LENGTH_SHORT).show();
            esValido=false;
        }

        if (nacionalidad.getSelectedItemPosition()==0){
            Toast.makeText(this, "Por favor selecciona tu nacionalidad", Toast.LENGTH_SHORT).show();
            esValido=false;
        }

        return esValido;
    }
}