package com.example.a01;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.slider.Slider;

import org.w3c.dom.Text;

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

        Button boton =findViewById(R.id.Boton);
        Button botonReinicio=findViewById(R.id.botonReinicio);
        NumberPicker numberPicker=findViewById(R.id.NumberPicker);
        numberPicker.setMinValue(1);
        numberPicker.setMaxValue(10);
        EditText importe=findViewById(R.id.eTImporte);
        Slider slider=findViewById(R.id.materialSlider);
        TextView propinaPorcentaje=findViewById(R.id.tVPorcentajePropina);
        TextView importeFinalCalculo=findViewById(R.id.tVImporteFinal);

        String textoPorDefectoPropina = propinaPorcentaje.getText().toString();
        String textoPorDefectoImporteFinal = importeFinalCalculo.getText().toString();

        boton.setOnClickListener(e->{
            int numeroComensales=numberPicker.getValue();
            String importeString=importe.getText().toString();
            double importeSinPropina=Double.parseDouble(importeString);
            double propina=(int)slider.getValue();

            double calculoPropina=(importeSinPropina*propina)/100;
            double importeFinal=(importeSinPropina+calculoPropina)/numeroComensales;

            propinaPorcentaje.append(" "+String.valueOf(propina)+"%");
            importeFinalCalculo.append(" "+String.valueOf(importeFinal)+"€");


        });

        botonReinicio.setOnClickListener(e->{
            slider.setValue(0);
            numberPicker.setValue(1);
            importe.setText("");
            propinaPorcentaje.setText(textoPorDefectoPropina);
            importeFinalCalculo.setText(textoPorDefectoImporteFinal);
        });


    }
}