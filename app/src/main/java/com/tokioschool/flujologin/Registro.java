package com.tokioschool.flujologin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.tokioschool.flujologin.databinding.RegistroBinding;

public class Registro extends AppCompatActivity {

    private RegistroBinding binding;
    private Bundle datos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //  Inicializa ViewBinding
        binding = RegistroBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        /*Configuro el btn de "me apunto!" para guardar los datos en un objeto Bundle
        y lanzar una nueva activity */
        binding.registroBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               //Obtengo las palabras ingresadas por el usuario
               String nombre = binding.registroInputTextName.getText().toString();
               String apellido = binding.registroInputTextLastName.getText().toString();

               //Guardo las palabras en un objeto Bundle
                datos = new Bundle();
                datos.putString("nombre",nombre);
                datos.putString("apellido",apellido);
                //Lanzo la activity MainActivity con los datos ingresados
                Intent datosLogin = new Intent(Registro.this,MainActivity.class);
                datosLogin.putExtras(datos);
                startActivity(datosLogin);
            }
        });



    }


}

